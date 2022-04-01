package com.jee.bartersup.controler;

import com.jee.bartersup.dao.UserIDao;
import com.jee.bartersup.entity.User;

import javax.annotation.security.DeclareRoles;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.SecurityContext;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "ServletLogin", value = "log" ,urlPatterns = "*.log")
public class ServletLogin extends HttpServlet {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private UserIDao userIDao;

    /*
    * Process login/logout <Get/Post>
    * */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String userPath = request.getServletPath();
        System.out.println(userPath+" path");


        if (userPath.equals("/in.log")){

            request.getRequestDispatcher("/login.xhtml").forward(request,response);

        } else if (userPath.equals("/out.log")){

            try {
                HttpSession session = request.getSession();
                session.invalidate();
                response.sendRedirect("index.jsp");
            }catch (Exception e){
                e.toString();
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
        HttpSession session = request.getSession();
        String userPath = request.getServletPath();

        if (userPath.equals("/in.log")){

            String authname = request.getParameter("email");
            String pass = request.getParameter("password");
            PrintWriter out = response.getWriter();
            boolean valid = userIDao.authUser(authname,pass);

            if (valid){

                User activeUser = userIDao.getUserByCredential(authname,pass);
                session.setAttribute("user", activeUser);
                session.setAttribute("name",authname);
                System.out.println("good");
                out.println("Welcome "+authname);
                //request.getRequestDispatcher("/index.xhtml").forward(request,response);

               response.sendRedirect("index.xtml");
            }

                out.println("Identifiant invalide");

            System.out.println("echec");



        }

    }


}
