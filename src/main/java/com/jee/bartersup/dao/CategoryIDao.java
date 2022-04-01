package com.jee.bartersup.dao;

import com.jee.bartersup.entity.Category;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CategoryIDao {

    void addCat(Category category);
    boolean categoryExists(String name, String other);
    Category getCategoryById(int catId);
    void updateCategory(Category category);
    void deleteCategory(Category catId);
    List<Category> findAll();
}
