package Dao;

import java.util.List;

public class FinfByDepartmentIdExample {
  public static void main(String[] args) {
    EmployeeDao dao = new EmployeeDao();
    List<Employee> employeeList = dao.findByDepartmentId(2);

    for(Employee employee : employeeList) {
      System.out.println("id = " + employee.getId());
      System.out.println("name = " + employee.getName());
    }
}
}
