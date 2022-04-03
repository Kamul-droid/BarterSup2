package com.jee.bartersup.entity;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "User")
public class User implements Serializable {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "user_name",unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = "first_name",nullable = false,length = 150)
    private String firstname;

    @Column(name = "last_name", nullable = false, length = 150)
    private String lastname;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "hash", nullable = true)
    private byte[] hashpassword;


    @Column(name = "zip_code",nullable = false)
    private String zipcode;

    @Column(name = "address",nullable = false,length = 2000)
    private String address;

    @Column(name = "rgpd",nullable = false)
    private boolean rgpd;

    @Column(name = "role",nullable = false)
    private String role;

    @Email
    @Column(name = "email",nullable = false)
    private String email;

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Post> posts = new ArrayList<>();

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }





    public User(String username, String firstname, String lastname, String password, String zipcode, String address, String email, String role, boolean rgpd) {

        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.zipcode = zipcode;
        this.address = address;
        this.email =email;
        this.role = role;
        this.rgpd = rgpd;
    }

    public User(){

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public byte[] getHashpassword() {
        return hashpassword;
    }

    public void setHashpassword(byte[] hashpassword) {
        this.hashpassword = hashpassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                ", zipcode=" + zipcode +
                ", address='" + address + '\'' +
                ", rgpd=" + rgpd +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
