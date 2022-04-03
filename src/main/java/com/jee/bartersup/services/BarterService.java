package com.jee.bartersup.services;

import com.jee.bartersup.dao.CategoryIDao;
import com.jee.bartersup.dao.PostIDao;
import com.jee.bartersup.entity.Category;
import com.jee.bartersup.entity.Post;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named("BarterService")
@RequestScoped
public class BarterService {
    private List<Post> postList = new ArrayList<>();
    private List<Post> lastAdd = new ArrayList<>();
    private Post post;
    private Category category;
    private List<Category> categList = new ArrayList<>();
    public BarterService() {
    }

    @EJB
    PostIDao postIDao;

    @EJB
    CategoryIDao categoryIDao;

    public List<Post> getPostList() {
        postList = postIDao.findAll();
        return postList;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Post getPostDetails(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        int id = Integer.parseInt(params.get("id"));
        post = postIDao.findPostById(id);
        return post;
    }

    public Long getUserIdByPostId(Integer id){
        Long idf = postIDao.findUser(id);
        return idf;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategList() {

        return categoryIDao.findAll();
    }

    public void setCategList(List<Category> categList) {
        this.categList = categList;
    }

    public void getCateg(){
        String outcome = null;
        // trier les objets par type dans la category
        //mettre à jour postList
        //renvoyer le nouveau post list

    }

    public List<Post> getLastAdd() {
        //requete pour obtenir les 3 derniers produits enregistrés
        return lastAdd;
    }
}
