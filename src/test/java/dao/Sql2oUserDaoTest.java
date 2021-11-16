package dao;

import org.junit.Rule;

import static org.junit.Assert.*;

public class Sql2oUserDaoTest {
    private static Sql2oUserDao employeeDao = new Sql2oUserDao();

    @Rule
    public DatabaseRule database = new DatabaseRule();



}