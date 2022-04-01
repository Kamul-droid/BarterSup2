package com.jee.bartersup.dao;


import com.jee.bartersup.entity.Image;

import javax.ejb.Remote;

@Remote
public interface ImageIDaoRemote {
    public Long addPicture(Image image);
    public void deletePicture(Object id);
    public void updatePicture(Image image);

}
