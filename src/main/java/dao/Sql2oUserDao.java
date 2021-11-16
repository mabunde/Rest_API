package dao;

import db.DB;
import models.User;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oUserDao implements UserDao{

    @Override
    public void add(User user) {
        try(Connection conn = DB.sql2o.open()){
            String sql = "INSERT INTO users (name, position,department) VALUES (:name, :position,:department)";
            int id = (int) conn.createQuery(sql, true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        } catch (Sql2oException ex){
            System.out.println("User not added " + ex);
        }
    }

    @Override
    public List<User> getAllEmployees() {
        try(Connection conn = DB.sql2o.open()){
            String sql = "SELECT * FROM users";
            return conn.createQuery(sql)
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public User findById(int id) {
        try(Connection conn = DB.sql2o.open()){
            String sql = "SELECT * FROM users WHERE id=:id;";
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        }
    }

    @Override
    public List<News> userNews(int userId) {
        List<News> news = new ArrayList<>();
        String joinQuery = "SELECT news_id from department_news WHERE user_id=:userId;";
        try(Connection conn = DB.sql2o.open()){
            List<Integer> allUserIds = conn.createQuery(joinQuery)
                    .addParameter("userId", userId)
                    .executeAndFetch(Integer.class);

            for (Integer singleUserId: allUserIds){
                String userQuery = "SELECT FROM news WHERE id=:id;";
                news.add(
                        conn.createQuery(userQuery)
                                .addParameter("id", singleUserId)
                                .executeAndFetchFirst(News.class)
                );
            }
        }
        return news;
    }

    @Override
    public void deleteById(int id) {
        try(Connection conn = DB.sql2o.open()){
            String sql = "DELETE FROM users WHERE id=:id;";
            conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    @Override
    public void clearAll() {
        try(Connection conn = DB.sql2o.open()){
            String sql = "DELETE FROM users;";
            conn.createQuery(sql).executeUpdate();
        }
    }
}
