package com.jee.bartersup.dao;

import com.jee.bartersup.entity.Image;
import com.jee.bartersup.entity.Post;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Stateless(name = "IMG")
public class ImageEJBImpl implements  ImageIDaoLocal, ImageIDaoRemote{

    @PersistenceContext()
    private EntityManager entityManager;

    @Override
    public Long addPicture(Image image) {
        entityManager.persist(image);
        return image.getId();
    }

    @Override
    public void deletePicture(Object id) {
        entityManager.remove(id);
    }

    @Override
    public void updatePicture(Image image) {

    }

    @Override
    public String findImageByPost(Post post) {
        Image img = (Image) entityManager.createQuery("select img from Image img where img.post.id =?1").setParameter(1,post.getId()).getSingleResult();
        return img.getLink();
    }
}
