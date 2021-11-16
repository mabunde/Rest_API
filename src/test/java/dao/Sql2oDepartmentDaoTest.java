package dao;

import models.Department;
import models.User;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class Sql2oDepartmentDaoTest {
    private static Sql2oDepartmentDao departmentDao = new Sql2oDepartmentDao();
    private static Sql2oUserDao userDao = new Sql2oUserDao();

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void department_addDepartmentToDatabase() {
        Department department = createDepartment1();
        assertNotEquals(0, department.getId());
    }
    @Test
    public void delete_deletingAllDepartments() {
        Department department1 = createDepartment1();
        Department department2 = createDepartment2();
        departmentDao.clearAll();
        assertEquals(0, departmentDao.allDepartments().size());
    }

    @Test
    public void user_addUserToDepartment() {
        Department department = createDepartment1();
        User user = newUser2();
        departmentDao.addEmployeeToDepartment(department, user);
        assertEquals("IT", user.getDepartment());
    }


    private Department createDepartment1(){
        Department newDepartment = new Department("finance", "creating new payroll");
        departmentDao.add(newDepartment);
        return newDepartment;
    }
    private Department createDepartment2(){
        Department newDepartment = new Department("IT", "networking");
        departmentDao.add(newDepartment);
        return newDepartment;
    }

    private User newUser2(){
        User user = new User("John","senior dev","IT");
        userDao.add(user);
        return user;
    }
}