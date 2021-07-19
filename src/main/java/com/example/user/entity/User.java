package com.example.user.entity;


import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
public class User {
    @Column(nullable = false)
    private @Id @GeneratedValue Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    private Date updateDate;

    @Column(nullable = false)
    private Date createDate;

    @Column(nullable = false)
    private Boolean admin;

    @Transient
    private String token;

    public User() {
        this.createDate = new Date();
    }

    public User(String name, String login, String password, String email, Boolean admin) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.createDate = new Date();
        this.admin = admin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        User user = (User) o;
        return Objects.equals(this.id, user.id)
                && Objects.equals(this.email, user.email)
                && Objects.equals(this.name, user.name)  ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.email);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", name='" + this.name + '\''  +", email='" + this.email + '\''  + '}';
    }
}