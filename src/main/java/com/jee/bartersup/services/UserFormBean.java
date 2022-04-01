package com.jee.bartersup.services;

import com.jee.bartersup.entity.Post;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
@RequestScoped
@Named("userForm")
public class UserFormBean {

    private Long id;

    private String username;


    private String firstname;


    private String lastname;


    private String password;

    private String zipcode;

    private String address;

    private boolean rgpd;

    private String role;

    private String email;

    private List<Post> posts = new ArrayList<>();

    public List<Post> getPosts() {
        return posts;
    }

    public UserFormBean(String username, String firstname, String lastname, String password, String zipcode, String address, boolean rgpd, String role, String email) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.zipcode = zipcode;
        this.address = address;
        this.rgpd = rgpd;
        this.role = role;
        this.email = email;
    }

    public UserFormBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isRgpd() {
        return rgpd;
    }

    public void setRgpd(boolean rgpd) {
        this.rgpd = rgpd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
