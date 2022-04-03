package com.jee.bartersup.controler;

import com.jee.bartersup.dao.PostIDao;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletPostDetails", value = "/ServletPostDetails",urlPatterns = "detailpost/*")
public class ServletPostDetails extends HttpServlet {
    @EJB
    PostIDao postIDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("detailPost.xhtml").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
