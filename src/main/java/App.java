import com.google.gson.Gson;
import static spark.Spark.*;

import dao.*;
import exceptions.ApiException;
import models.*;
public class App {
    public static void main(String[] args) {
        Sql2oDepartmentDao departmentDao = new Sql2oDepartmentDao();
        Sql2oUserDao userDao = new Sql2oUserDao();
        Sql2oNewsDao newsDao = new Sql2oNewsDao();

        Gson gson = new Gson();
        // get all departments
        get("/departments", "application/json", (request, response) -> {
            return gson.toJson(departmentDao.allDepartments());
        });
        // get department by id
        get("/departments/:departmentId", "application/json", (request, response) -> {
            int departmentId = Integer.parseInt(request.params("departmentId"));
            return gson.toJson(departmentDao.findById(departmentId));
        });
        // get all users in a  department
        get("/departments/:departmentId/users", "application/json", (request, response) -> {
            int departmentId = Integer.parseInt(request.params("departmentId"));
            return gson.toJson(departmentDao.allDepartmentEmployees(departmentId));
        });
        // get a specific user in a department
        get("/departments/:departmentId/users/:userId/details", "application/json", (request, response) -> {
            int userId = Integer.parseInt(request.queryParams("userId"));

            User userToFind = userDao.findById(userId);

            if (userToFind == null){
                throw new ApiException(404, String.format("No user with id: \"%s\" exists", request.params("id")));
            }

            return gson.toJson(userToFind);
        });
        // get all users
        get("/users", "application/json", (request, response) -> {
            return gson.toJson(userDao.getAllEmployees());
        });
        // get specific user details
        get("/employees/:employeeId/details", "appliaction/json", (request, response) -> {
            int userId = Integer.parseInt(request.params("employeeId"));
            User userToFind = userDao.findById(userId);
            if (userToFind == null){
                throw new ApiException(404, String.format("No employee with id: \"%s\" exists", request.params("id")));
            }
            return gson.toJson(userDao.findById(userId));
        });
        // create new department
        post("/departments/new", "application/json", (request, response) -> {
            Department department = gson.fromJson(request.body(), Department.class);
            departmentDao.add(department);
            response.status(201);
            response.type("application/json");
            return gson.toJson(department);
        });
        //Add user to department
        post("/departments/:departmentId/employees/new", "application/json", (request, response) -> {
            int departmentId = Integer.parseInt(request.params("departmentId"));
            Department newDepartment = departmentDao.findById(departmentId);
            User newUser = gson.fromJson(request.body(), User.class);
            userDao.add(newUser);
            newUser.setDepartment(newDepartment.getName());
            departmentDao.addEmployeeToDepartment(newDepartment, newUser);
            response.status(201);
            response.type("application/json");
            return gson.toJson(newUser);
        });
    }
}
