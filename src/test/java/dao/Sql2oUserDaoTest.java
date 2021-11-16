package dao;

import models.*;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

public class Sql2oUserDaoTest {
    private static Sql2oUserDao userDao = new Sql2oUserDao();

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void save_employeeSavesToDatabase() {
        User user = newUser();
        assertNotEquals(0, user.getId());
    }

    @Test
    public void find_findUserById_(){
        User user1 = newUser();
        User user2 = newUser2();
        User foundUser = userDao.findById(user2.getId());
        assertEquals(true, user1.equals(foundUser));
    }

    @Test
    public void employee_getsAllUser() {
        User user1 = newUser();
        User user2 = newUser2();
        assertEquals(true, userDao.getAllEmployees().contains(user1));
        assertEquals(true, userDao.getAllEmployees().contains(user2));
    }
    @Test
    public void delete_deleteAllEmployees() {
        User user1 = newUser();
        User user2 = newUser2();
        userDao.clearAll();
        assertEquals(0, userDao.getAllEmployees().size());
    }




    private User newUser(){
        User user = new User("Mike","junior dev","IT");
        userDao.add(user);
        return user;
    }
    private User newUser2(){
        User user = new User("John","senior dev","IT");
        userDao.add(user);
        return user;
    }
}