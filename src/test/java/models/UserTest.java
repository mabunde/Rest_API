package models;

import models.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void user_instantiatesCorrectly_true() {
        User mike = new User("mike", "Senior","IT");
        assertTrue(mike instanceof User);
    }
    @Test
    public void username_instantiatesWithName_name() {
        User mike = createUser();
        assertEquals("Mike", mike.getName());
    }
    @Test
    public void position_instantiatesWithPosition_position() {
        User mike = createUser();
        assertEquals("Senior", mike.getPosition());
    }
    @Test
    public void departmentName_setsEmployeeName_department() {
        User mike = createUser();
        mike.setName("Mike");
        assertEquals("Mike", mike.getName());
    }

    // HELPERS
    public User createUser(){
        return new User("Mike", "Senior", "IT");
    }
    public User createUser2(){
        return new User("Charles", "junior", "finance");
    }
}