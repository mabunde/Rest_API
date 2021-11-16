import com.google.gson.Gson;
import static spark.Spark.*;

import dao.*;
import exceptions.ApiException;
import models.*;
public class App {
    public static void main(String[] args) {
        Sql2oDepartmentDao departmentDao = new Sql2oDepartmentDao();
        Sql2oUserDao employeeDao = new Sql2oUserDao();
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
    }
}
