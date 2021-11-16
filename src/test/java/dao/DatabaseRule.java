package dao;

import db.DB;
import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource{
    @Override
    public void before(){
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/news_portal_test", "sitati", "Access");
    }

    @Override
    public void after(){
        try(Connection conn = DB.sql2o.open()){
            String deleteUserQuery = "DELETE FROM users";
            String deleteDepartmentsQuery = "DELETE FROM departments";
            String deleteNewsQuery = "DELETE FROM news";
            conn.createQuery(deleteUserQuery).executeUpdate();
            conn.createQuery(deleteDepartmentsQuery).executeUpdate();
            conn.createQuery(deleteNewsQuery).executeUpdate();
        }
    }
}
