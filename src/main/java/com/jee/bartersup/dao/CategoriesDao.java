package com.jee.bartersup.dao;

import com.jee.bartersup.entity.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Stateless(name = "CTGRY")
public class CategoriesDao implements CategoryIDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addCat(Category category) {
        entityManager.merge(category);
    }

    @Override
    public boolean categoryExists(String name, String other) {
        return false;
    }

    @Override
    public Category getCategoryById(int catId) {
        return entityManager.find(Category.class,catId);
    }

    @Override
    public void updateCategory(Category category) {
        entityManager.merge(category);
    }

    @Override
    public void deleteCategory(Category catId) {
        entityManager.remove(catId);
    }

    @Override
    public List<Category> findAll() {
        return entityManager.createQuery("select c from Category c ").getResultList();
    }
}
