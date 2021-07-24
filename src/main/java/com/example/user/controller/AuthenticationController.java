package com.example.user.controller;

import com.example.user.authentication.JwtTokenUtil;
import com.example.user.entity.*;
import com.example.user.service.UserService;
import com.example.user.util.ObjectUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ApiResponse<AuthToken> register(@RequestBody LoginUser loginUser, Model model, HttpSession session) throws AuthenticationException, JsonProcessingException {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        final User user = userService.findOne(loginUser.getUsername());
        final String token = jwtTokenUtil.generateToken(user);

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);

        SessionUserToken sessionUserToken = new SessionUserToken(user.getLogin(), token);

        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

        if (messages == null) {
            messages = new ArrayList<>();
        }
        messages.add(ObjectUtil.getJsonFromObject(sessionUserToken));
        model.addAttribute("sessionMessages", messages);
        model.addAttribute("sessionId", session.getId());

        return new ApiResponse<>(200, "success",new AuthToken(token, user.getLogin()));
    }

}
