package com.jee.bartersup.controler;

import com.jee.bartersup.dao.CategoryIDao;
import com.jee.bartersup.dao.ImageIDaoLocal;
import com.jee.bartersup.dao.PostIDao;
import com.jee.bartersup.entity.Category;
import com.jee.bartersup.entity.Image;
import com.jee.bartersup.entity.Post;
import com.jee.bartersup.entity.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@MultipartConfig(
        fileSizeThreshold = 1024 * 10,  // 10 KB
        maxFileSize = 1024 * 300,       // 300 KB
        maxRequestSize = 1024 * 1024    // 1 MB
)
@SessionScoped
@WebServlet(name = "ServletPost", value = "post", urlPatterns = "*.post")
public class ServletPost extends HttpServlet {
    @EJB
    private PostIDao postIDao;

    @EJB
    private CategoryIDao categoryIDao;

    @EJB
    private ImageIDaoLocal imageIDao;


    private  final String UPLOAD_DIRECTORY ="C:\\Users\\kam\\Documents\\BarterSup\\src\\main\\webapp\\picture";


    private Category category = new Category();
    private Image image = new Image();
    private Post post = new Post();

    private List<Category> categoryList = new ArrayList<>();

    /*
    * Process request for HTTP <Get/Post>
    * */


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userPath = request.getServletPath();
        System.out.println(userPath+" path");

        // Create get path
        if (userPath.equals("/create.post")){
            categoryList = categoryIDao.findAll();
            System.out.println(categoryList.size()+" number of items");
//            request.setAttribute("postmodel",post);
            request.setAttribute("categorylist",categoryList);
            request.getRequestDispatcher("postRegister.jsp").forward(request,response);

        } else if (userPath.equals("/image.post")){

            request.setAttribute("imagemodel",image);
            request.getRequestDispatcher("image.jsp").forward(request,response);

        } else if (userPath.equals("/category.post")){

            request.setAttribute("catmodel",category);
            request.getRequestDispatcher("cat.jsp").forward(request,response);

        }else if (userPath.equals("/detail.post")){

            request.getRequestDispatcher("detailPost.xhtml").forward(request,response);

        }

    }

    /*
    * Handle the code POST method
    * */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userPath = request.getServletPath();
       HttpSession session = request.getSession();

        // Create POST  process
        if (userPath.equals("/create.post")){
//            post = (Post) request.getAttribute("postmodel");
            /*
            * Get the Parameter
            * */
            String title = request.getParameter("title");
            Category categ = new Category();
            if (request.getParameter("type") != null) {
                Integer type = Integer.valueOf(request.getParameter("type"));
                 categ = categoryIDao.getCategoryById(type);
            }

            Double price = Double.valueOf(request.getParameter("price"));
            String description = request.getParameter("descript");
            Date date = new Date();
            User user = (User) session.getAttribute("user");
            System.out.println("user actif hhhhhhhhhhhhhhhhhhhhhhhhhhhh "+ user.getUsername());

            /*
            * Create the model
            * */
            Post post = new Post();
            post.setCategory(categ);
            post.setDate(date);
            post.setPrice(price);
            post.setTitle(title);
            post.setDescription(description);
            post.setUser(user);
            postIDao.AddPost(post);

            request.setAttribute("postmodel",post);
            session.setAttribute("newpost",post);
            response.sendRedirect("image.post");
//            request.getRequestDispatcher("index.jsp").forward(request,response);

        }
        else if (userPath.equals("/image.post")){
            Post pst = new Post();
            if (!(session.getAttribute("newpost") ==null || session.getAttribute("newpost") =="")){
                 pst = (Post) session.getAttribute("newpost");
                image.setPost(pst);
               // System.out.println("post "+pst.toString());

            }
//            Post post = (Post) request.getAttribute("postmodel");
            /*Get the Parameter and save the file
            * */

            if (ServletFileUpload.isMultipartContent(request)){

                try {
                    List<FileItem> multiparts = new ServletFileUpload(
                            new DiskFileItemFactory()
                    ).parseRequest(request);
                    for (FileItem item : multiparts){
                        if (!item.isFormField()){
                            String name = new File(item.getName()).getName();
                            //System.out.println("file name "+name);
                            String trsf ="";
                            String[] strings = name.split(" ");
                            for (String str: strings
                                 ) {
                                trsf+=str.toLowerCase();
                            }
                            System.out.println("trddd " + trsf);
                            item.write(new File(UPLOAD_DIRECTORY+File.separator+trsf));
                            image.setLink(UPLOAD_DIRECTORY+File.separator+trsf);


                            //
//
                            imageIDao.addPicture(image);
                            System.out.println("directory name "+UPLOAD_DIRECTORY+ FileSystems.getDefault().getSeparator()+trsf);

                        }
                    }
                    //Success
                    request.setAttribute("message","File Upload");
                }  catch (Exception e) {
                     request.setAttribute("message","Failed Upload" +e);
                }

            }

//            request.getRequestDispatcher("image.jsp").forward(request,response);
        response.sendRedirect("index");
        }
        else if (userPath.equals("/category.post")){
            String categ = request.getParameter("cat");
            Category cat = new Category();
            cat.setType(categ);
            categoryIDao.addCat(cat);
            response.sendRedirect("create.post");
        }





    }
}
