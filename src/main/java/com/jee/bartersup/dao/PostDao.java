package com.jee.bartersup.dao;

import com.jee.bartersup.entity.Post;
import com.jee.bartersup.entity.User;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Stateless(name = "PST")
public class PostDao implements PostIDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Integer AddPost(Post post) {
        entityManager.persist(post);
        return post.getId();
    }

    @Override
    public boolean postExists(String name, String other) {
        return false;
    }

    @Override
    public Post findPostById(Integer postId) {
        Post post = new Post();
        List<Post> lp = entityManager.createQuery("select  p from Post p ").getResultList();
        if (lp.isEmpty()){
            System.out.println("no result in list");
        } else {
            System.out.println("Data " +lp.toString());}
        post = entityManager.find(Post.class,postId);
        if (post == null){
            System.out.println("no result from request");
        }
        return post;
    }

    @Override
    public void updatePost(Post post) {
        entityManager.merge(post);
    }

    @Override
    public void deletePost(Post post) {
        entityManager.remove(post);
    }

    @Override
    public List<Post> findAll() {
        return entityManager.createQuery("select p from Post p").getResultList();

    }

    @Override
    public String getPostByImage(Post post) {
        return null;
    }

    @Override
    public Long findUser(Integer id) {
        Post p = (Post) entityManager.createQuery("select p from  Post p where p.id =?1").setParameter(1,id).getSingleResult();
        return p.getUser().getId();
    }
}
