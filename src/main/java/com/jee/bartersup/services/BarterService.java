package com.jee.bartersup.services;

import com.jee.bartersup.dao.PostIDao;
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
    private Post post;
    public BarterService() {
    }

    @EJB
    PostIDao postIDao;

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
}
