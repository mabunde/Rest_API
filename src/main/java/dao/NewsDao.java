package dao;

import models.*;

import java.util.List;

public interface NewsDao {
    // CREATE
    void add(News news);
    void addNewsToDepartment(int deptId, int newsId, int userId);

    // READ
    List<News> AllNews();
    List<News> allDepartmentsNews();

    // UPDATE

    // DELETE
    void deleteById(int id);
    void clearAll();
}
