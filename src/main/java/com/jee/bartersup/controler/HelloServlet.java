package com.jee.bartersup.controler;

import com.jee.bartersup.dao.ImageIDaoLocal;
import com.jee.bartersup.dao.PostIDao;
import com.jee.bartersup.entity.Image;
import com.jee.bartersup.entity.Post;

import java.io.*;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
@SessionScoped
@WebServlet(name = "helloServlet", value = "",urlPatterns = "index")
public class HelloServlet extends HttpServlet {
    private String message;
    @EJB
    private PostIDao postIDao;
    @EJB
    private ImageIDaoLocal iDaoLocal;

    private Post post = new Post();


    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       // HttpSession session = request.getSession(); ;
//        List<Post> pst = postIDao.findAll();
//        Post post =  postIDao.findPostById(4);
//        System.out.println("post "+ post.toString());
//        String link = iDaoLocal.findImageByPost(post);
//        System.out.println("the link "+link);
////        session.setAttribute("imgpst",post);
////        session.setAttribute("imgl",link);
//        request.setAttribute("img",link);
//        request.setAttribute("pst",post);
        request.getRequestDispatcher("index2.xhtml").forward(request,response);

    }

    public void destroy() {
    }
}