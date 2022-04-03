package com.jee.bartersup.dao;

import com.jee.bartersup.entity.Post;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PostIDao {

    Integer AddPost(Post post);
    boolean postExists(String name, String other);
    Post findPostById(Integer postId);
    void updatePost(Post post);
    void deletePost(Post post);
    List<Post> findAll();
    String getPostByImage(Post post);
    Long findUser(Integer id);

}
