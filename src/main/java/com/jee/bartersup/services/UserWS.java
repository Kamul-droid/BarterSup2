package com.jee.bartersup.services;

import com.jee.bartersup.dao.ImageIDaoLocal;
import com.jee.bartersup.dao.UserIDao;
import com.jee.bartersup.entity.Image;
import com.jee.bartersup.entity.User;
import sun.security.validator.ValidatorException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;


@Stateless
@Named("regUser")
public class UserWS implements Serializable {
    @EJB
    private UserIDao uejbRef;
    @WebMethod
    public List<User> allUser(){
        return uejbRef.findAll();
    }

    @WebMethod(operationName = "addUser")
    public void addNewUser(User user){
        uejbRef.AddUser(user);
    }


    private User user = new User();

    private Image image =  new Image();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Validate Email
    @WebMethod(operationName = "validateEmail")
    public void validateEmail(FacesContext context, UIComponent toValid, Object value) throws ValidatorException {
        String emailStr = (String) value;
        if (-1 == emailStr.indexOf("@")){
            FacesMessage message = new FacesMessage("L'adresse Email est Valid");
            throw new ValidatorException(message);
        }
    }

    @Named("addNewUser")
    public String addNewUser() throws NoSuchAlgorithmException {

        System.out.println(user.getUsername() +" USer name");
        //Long res = Long.valueOf(-1);
        FacesMessage message = null;
        String outcome = null;

        if (!(uejbRef.userExists(user.getUsername(),user.getEmail()))){
            user.setRole("ROLE_EDITOR");
            byte[] pass = hashPassword(user.getPassword());
            user.setHashpassword(pass);
            uejbRef.AddUser(user);
            message = new FacesMessage("Utilisateur enregistré !");
            outcome = "index2";
            HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("user",user);


            System.out.println(user.getId()+" id");

        } else {
            message = new FacesMessage("Utilisateur non enregistré !");
            outcome = "register";

        }

        return outcome;
    }


    public String login(){
        String outcome = null;
        FacesMessage message = null;
       // UserFormBean userForm = new UserFormBean();
        boolean valid = uejbRef.authUser(user.getEmail(),user.getPassword());
        try {
            boolean validH = uejbRef.authUserHash(user.getEmail(), hashPassword(user.getPassword()));
            if (validH){
                System.out.println("it work");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        System.out.println("login here");
        System.out.println("user"+user.getEmail());

        if (valid){
            HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);


            User activeUser = uejbRef.getUserByCredential(user.getEmail(), user.getPassword());
            session.setAttribute("user", activeUser);
            System.out.println("good");
            message = new FacesMessage("Bienvenue");
            outcome = "index2";

        } else {
            message = new FacesMessage("Erreur identification");
            outcome = "login";
        }

        return outcome;
    }

    public byte[] hashPassword(String pass) throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte [] salt = new byte[16];
        random.nextBytes(salt);
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        byte[] hashedpassword = md.digest(pass.getBytes(StandardCharsets.UTF_8));

        return hashedpassword;
    }






}
