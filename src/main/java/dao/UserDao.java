package dao;

import models.User;
import models.News;

import java.util.List;

public interface UserDao {
    // create
    void add(User employee);

    // read
    List<User> getAllEmployees();
    User findById(int id);
    List<News> userNews(int userId);

    // update


    // delete
    void deleteById(int id);
    void clearAll();
}
