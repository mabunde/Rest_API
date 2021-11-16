package dao;

import models.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Sql2oNewsDaoTest {
    private static Sql2oDepartmentDao deptDao = new Sql2oDepartmentDao();
    private static Sql2oNewsDao newsDao = new Sql2oNewsDao();
    private static Sql2oUserDao userDao = new Sql2oUserDao();

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void news_saveNewsToDatabase() {
        News news1 = new News("News1", "My description 1","general");
        newsDao.add(news1);
        News news2 = new News("News2", "My Description 2","departmental");
        newsDao.add(news2);
        assertEquals(news1, newsDao.allDepartmentsNews().contains(news1));
    }
}
