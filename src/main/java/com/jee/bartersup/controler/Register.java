package com.jee.bartersup.controler;

import com.jee.bartersup.dao.ImageIDaoLocal;
import com.jee.bartersup.dao.UserDao;
import com.jee.bartersup.dao.UserIDao;
import com.jee.bartersup.entity.Image;
import com.jee.bartersup.entity.User;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@Named
@SessionScoped
@WebServlet(name = "registeruser",value ="reg" ,urlPatterns =  "*.register")
public class Register extends HttpServlet {


    public Register() {
        super();
    }

    @EJB
    private UserIDao uejbRef;
    @EJB
    private ImageIDaoLocal imgRef;
    User user = new User();
    Image image= new Image();


    @Override
    public void init() throws ServletException {
        super.init();


    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("usermodel",new User());
        request.getRequestDispatcher("register.xhtml").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        * Lire les données de la requête
        * */

        String usrnme = request.getParameter ("usrnme");
        String ftname = request.getParameter("ftname");
        String ltname = request.getParameter("ltname");
        String eml = request.getParameter("eml");
        String dress = request.getParameter("dress");
        String pword = request.getParameter("pword");
        String zip = request.getParameter("cdpst");
        String rd =  request.getParameter("rd");
        boolean rg =false;
        if (rd != null){
            if (rd.length()>0) {
                rg = true;
            } else  rg = false;
        }

        /*
        * Mise à jour du model
        * */
        user.setUsername (usrnme);
        user.setFirstname(ftname);
        user.setLastname( ltname);
        user.setEmail( eml);
        user.setAddress( dress);
        user.setPassword( pword);
        user.setZipcode(zip);
        user.setRgpd(rg);

        System.out.println(" user "+user);

        /*
        * Valider les données et insertion
        * */

            this.addNewUser(response,request);

        /*
        * faire un forward vers le profil utilisateur et envoi du modèle
        *
        * */
        request.setAttribute("user",user);
       request.getRequestDispatcher("index.xhtml").forward(request, response);

    }


    public void addNewUser( HttpServletResponse response, HttpServletRequest request) throws IOException {
        if (!(uejbRef.userExists(user.getUsername(),user.getEmail()))){
            user.setRole("ROLE_EDITOR");
            uejbRef.AddUser(user);
            System.out.println(user.getId()+" id");

            HttpSession session = request.getSession(true);
            if (session == null) {
                // Not created yet. Now do so yourself.
                session = request.getSession();
            } else {
                // Already created.
                session.setAttribute("user",user);
            }

        } else {
            PrintWriter out = response.getWriter();
            out.println("User already exists");
            System.out.println("Same username or email  already exists");}


    }




}
