package com.jee.bartersup.dao;





import com.jee.bartersup.entity.User;

import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Stateless(name = "USR")
public class UserDao implements UserIDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void AddUser(User user) {
        entityManager.persist(user);
        ;
    }

    @Override
    public boolean userExists(String email, String username) {
        String jpql;
        jpql = "SELECT u From User u WHERE u.username = ?1 and u.email =?2";
        int count = entityManager.createQuery(jpql,User.class).setParameter(1,username).setParameter(2,email).getResultList().size();
        return count>0;
    }

    @Override
    public User getUserById(Object userId) {
        return entityManager.find(User.class,userId);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
        entityManager.flush();

    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(entityManager.merge(user));

    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select object (u) from User as u").getResultList();
    }
    @Override
    public boolean authUser(String email, String password){
        int count = 0, count1 = 0;
         count =  entityManager.createQuery("select u from User u where u.email =?1 ").setParameter(1,email).getResultList().size();
         count1 =  entityManager.createQuery("select u from User u where  u.password = ?1").setParameter(1, password).getResultList().size();

        if (count * count1 != 0){
            return true;
        }
        return false;

    }

    @Override
    public User getUserByCredential(String email, String password) {
        User data = (User) entityManager.createQuery("select u from User u where u.email =?1  and u.password = ?2").setParameter(1,email).setParameter(2, password).getSingleResult();

        return data;
    }
    @Override
    public boolean authUserHash(String email, byte[] password){
        int count = 0, count1 = 0;
        count =  entityManager.createQuery("select u from User u where u.email =?1 and u.hashpassword =?2").setParameter(1,email).setParameter(2,password).getResultList().size();
//        count1 =  entityManager.createQuery("select u from User u where  u.hashpassword = ?1").setParameter(1, password).getResultList().size();

        if (count != 0){
            return true;
        }
        return false;
    }
}
