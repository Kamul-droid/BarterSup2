package com.jee.bartersup.dao;


import com.jee.bartersup.entity.Image;
import com.jee.bartersup.entity.Post;

import javax.ejb.Local;

@Local
public interface ImageIDaoLocal {
    public Long addPicture(Image image);
    public void deletePicture(Object id);
    public void updatePicture(Image image);
    public String findImageByPost(Post post);
}
