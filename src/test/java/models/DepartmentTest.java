package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentTest {
    @Test
    public void department_instantiatesCorrectly_true() {
        Department department1 = new Department("IT", "networking");
        assertTrue(department1 instanceof Department);
    }

    @Test
    public void description_instantiatesWithDescription_description() {
        Department department = createDepartment();
        assertEquals("Generate payroll", department.getDescription());
    }
    @Test
    public void departmentName_setsName_name() {
        Department department = createDepartment();
        department.setName("Social");
        assertEquals("Social", department.getName());
    }
    @Test
    public void description_setsDescription_description() {
        Department department = createDepartment();
        department.setDescription("networking");
        assertEquals("networking", department.getDescription());
    }
    @Test
    public void name_instantiatesWithName_name() {
        Department department1 = createDepartment();
        assertEquals("finance", department1.getName());
    }

    // HELPERS
    public Department createDepartment(){
        return new Department("finance", "Generate payroll");
    }
}