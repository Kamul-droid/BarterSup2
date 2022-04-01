package com.jee.bartersup.services;

import com.jee.bartersup.dao.ImageIDaoLocal;
import com.jee.bartersup.dao.UserIDao;
import com.jee.bartersup.entity.Image;
import com.jee.bartersup.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.servlet.http.HttpServlet;
import java.io.Serializable;
import java.util.List;


@Stateless
public class UserWS {
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

    @SessionScoped
    @Named
    public static class registerControler implements Serializable {
        @EJB
        private UserIDao uejbRef;
        @EJB
        private ImageIDaoLocal imgRef;

        private User user = new User();

        private Image image =  new Image();

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
        public Image getImage() {
            return image;
        }

        public void setImage(Image image) {
            this.image = image;
        }


        public List<User> allUser(){
            return uejbRef.findAll();
        }

        public class sendTo extends HttpServlet {

        }
        public void addNewUser(){

            System.out.println(user.getUsername() +" USer name");
            //Long res = Long.valueOf(-1);

            if (!(uejbRef.userExists(user.getUsername(),user.getEmail()))){
                user.setRole("ROLE_EDITOR");
                uejbRef.AddUser(user);
                System.out.println(user.getId()+" id");

            } else System.out.println("Same username or email  already exists");


        }



    }
}
