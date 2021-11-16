package dao;

import db.DB;
import models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oNewsDao implements NewsDao{


    @Override
    public void add(News news) {
        try(Connection conn = DB.sql2o.open()){
            String sql = "INSERT INTO news (name, description,type) VALUES (:name, :description,:type)";
            int id = (int) conn.createQuery(sql, true)
                    .bind(news)
                    .executeUpdate()
                    .getKey();
            news.setId(id);
        } catch (Exception ex){
            System.out.println("Oooops! News can't be added: " + ex);
        }
    }



    @Override
    public void addNewsToDepartment(int departmentId, int newsId, int userId) {
        try(Connection conn = DB.sql2o.open()){
            String sql = "INSERT INTO departments_news (department_id, news_id, user_id) VALUES (:departmentId, :newsId, :userId)";
            conn.createQuery(sql)
                    .addParameter("departmentId", departmentId)
                    .addParameter("newsId", newsId)
                    .addParameter("userId", userId)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println("Oooops! news wasn't added to department: " + ex);
        }
    }

    @Override
    public List<News> AllNews() {
        try(Connection conn = DB.sql2o.open()){
            String sql = "SELECT * FROM news";
            return conn.createQuery(sql).executeAndFetch(News.class);
        }
    }

    @Override
    public List<News> allDepartmentsNews() {
        try(Connection conn = DB.sql2o.open()){
            String sql = "SELECT * FROM news";
            return conn.createQuery(sql)
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public void deleteById(int id) {
        try(Connection conn = DB.sql2o.open()){
            String sql = "DELETE from news WHERE id=:id";
            conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println("Delete news by id: " + id);
        }
    }

    @Override
    public void clearAll() {
        try(Connection conn = DB.sql2o.open()){
            String sql = "DELETE FROM news";
            conn.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex){
            System.out.println("Ooops! Unable to delete all: " + ex);
        }
    }
}
