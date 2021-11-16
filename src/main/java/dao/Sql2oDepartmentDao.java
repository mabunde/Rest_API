package dao;

import db.DB;
import models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oDepartmentDao implements DepartmentDao {
    @Override
    public void add(Department department) {
        try(Connection conn = DB.sql2o.open()){
            String sql = "INSERT INTO departments (name, description, employeesCount) VALUES (:name, :description, :employeeCount)";
            int id = (int) conn.createQuery(sql, true)
                    .bind(department)
                    .addParameter("employeeCount", department.getEmployeeCount())
                    .executeUpdate()
                    .getKey();
            department.setId(id);
        } catch (Sql2oException ex){
            System.out.println("Unable to add department" + ex);
        }
    }

    @Override
    public void addEmployeeToDepartment(Department department, User user) {
        try(Connection conn = DB.sql2o.open()){
            String sql = "INSERT INTO departments_users(dept_id, user_id) VALUES (:departmentId, :userId)";
            conn.createQuery(sql)
                    .addParameter("departmentId", department.getId())
                    .addParameter("userId", user.getId())
                    .executeUpdate();
            user.setDepartment(department.getName());
            updateEmployeeCount(department);
        } catch (Sql2oException ex){
            System.out.println("Unable to add into department " + ex);
        }
    }

    @Override
    public Department findById(int id) {
        try(Connection conn = DB.sql2o.open()){
            String sql = "SELECT FROM departments WHERE id=:id";
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Department.class);
        }
    }

    @Override
    public List<Department> allDepartments() {
        try(Connection conn = DB.sql2o.open()){
            String sql = "SELECT * FROM departments";
            return conn.createQuery(sql)
                    .executeAndFetch(Department.class);
        }
    }

    @Override
    public List<User> allDepartmentEmployees(int departmentId) {
        List<User> employees = new ArrayList<>();
        String joinQuery = "SELECT userid FROM departments_users WHERE deptid=:departmentId";
        try(Connection conn = DB.sql2o.open()){
            List<Integer> employeeIds = conn.createQuery(joinQuery)
                    .addParameter("departmentId", departmentId)
                    .executeAndFetch(Integer.class);
            for(Integer employeeId:employeeIds){
                String sql = "SELECT FROM users WHERE id=:id";
                employees.add(
                        conn.createQuery(sql)
                                .addParameter("id", employeeId)
                                .executeAndFetchFirst(User.class)
                );
            }
        } catch (Sql2oException ex){
            System.out.println("Unable to get users from ids: " + ex);
        }

        return employees;
    }

    @Override
    public List<News> allDepartmentNews(int departmentId) {
        List<News> news = new ArrayList<>();
        String joinQuery = "SELECT newsid FROM departments_news WHERE deptid=:departmentId";
        try(Connection conn = DB.sql2o.open()){
            List<Integer> newsIds = conn.createQuery(joinQuery)
                    .addParameter("departmentId", departmentId)
                    .executeAndFetch(Integer.class);

            for(Integer newsId:newsIds){
                String sql = "SELECT * FROM news WHERE id=:id";
                news.add(
                        conn.createQuery(sql)
                                .executeAndFetchFirst(News.class)
                );
            }
        } catch (Sql2oException ex){
            System.out.println("Unable to recover news articles" + ex);
        }

        return news;
    }

    @Override
    public void updateEmployeeCount(Department department) {
        try(Connection conn = DB.sql2o.open()){
            String sql = "UPDATE departments SET employee_count= :employeeCount WHERE id=:id";
            conn.createQuery(sql)
                    .addParameter("employeeCount", department.getEmployeeCount())
                    .addParameter("id", department.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println("Unable to update departments: " + ex);
        }
    }

    @Override
    public void deleteDepartmentById(int id) {
        try(Connection conn = DB.sql2o.open()){
            String sql = "DELETE FROM departments WHERE id=:id";
            conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println("Unable to delete department by ID: " + ex);
        }
    }

    @Override
    public void deleteEmployeeFromDepartment(Department department, User user) {
        try(Connection conn = DB.sql2o.open()){
            String sql = "DELETE from departments_users WHERE deptid = :departmentId AND userId = :employeeId";
            conn.createQuery(sql)
                    .addParameter("departmentId", department.getId())
                    .addParameter("employeeId", user.getId())
                    .executeUpdate();
            user.setDepartment("None");
            updateEmployeeCount(department);
        } catch (Sql2oException ex){
            System.out.println("Unable to delete from dept_users: " + ex);
        }
    }

    @Override
    public void deleteDepartmentNewsById(int departmentId, int newsId) {
        try(Connection conn = DB.sql2o.open()){
            String sql = "DELETE from departments_news WHERE deptid = :deptId AND newsid = :newsId";
            conn.createQuery(sql)
                    .addParameter("deptId", departmentId)
                    .addParameter("newsId", newsId)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println("Unable to delete department news by id: " + ex);
        }
    }

    @Override
    public void clearAll() {
        try(Connection conn = DB.sql2o.open()){
            String sql = "DELETE FROM departments";
            conn.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex){
            System.out.println("Unable to delete all departments: " + ex);
        }
    }
}
