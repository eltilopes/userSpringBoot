package com.example.user.controller;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.user.entity.Email;
import com.example.user.entity.User;
import com.example.user.exception.UserEmptyException;
import com.example.user.exception.UserNotFoundException;
import com.example.user.rabbitmq.service.EmailService;
import com.example.user.repository.UserRepository;
import com.example.user.util.ObjectUtil;
import com.sun.xml.bind.v2.schemagen.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserRepository repository;

    @Autowired
    EmailService emailService;

    UserController(UserRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/users")
    List<User> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping(value = "/users")
    User newUser(@RequestBody User newUser) {
        User user = newUser;
        if(ObjectUtil.isNullOrEmty(newUser.getName())
                || ObjectUtil.isNullOrEmty(newUser.getPassword())
                || ObjectUtil.isNullOrEmty(newUser.getEmail())
                || ObjectUtil.isNullOrEmty(newUser.getLogin())){
            throw new UserEmptyException();
        }
        try {
            user = repository.save(newUser);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return user;
    }
    @PostMapping(value = "/token")
    User getToken(@RequestBody User userLogin) {
        userLogin = repository.findByLoginAndPassword(userLogin.getLogin(), userLogin.getPassword());
        if(ObjectUtil.isNullOrEmty(userLogin)){
            userLogin.setToken("token");
        }
        return userLogin;
    }

    @PostMapping("/email")
    Email sendEmail(@RequestBody Email email) throws Exception {
        log.info("Enviar Email - Mensagem: '" + email.getMessage() + "'");
        emailService.sendEmail(email);
        return email;
    }

    // Single item

    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        Boolean usuarioAdmin = verificarUsuraioAdmin();
        return repository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    user.setLogin(newUser.getLogin());
                    user.setPassword(newUser.getPassword());
                    user.setUpdateDate(new Date());
                    if(usuarioAdmin){
                        user.setAdmin(newUser.getAdmin());
                    }
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    private Boolean verificarUsuraioAdmin() {
        return Boolean.TRUE;
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id, HttpServletResponse response) {
        try {
            repository.deleteById(id);
        }catch (Exception e){
            log.error(e.getMessage());
            response.setStatus( HttpStatus.NO_CONTENT.value());
        }


    }
}