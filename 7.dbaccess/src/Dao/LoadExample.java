package Dao;

public class LoadExample {
  public static void main(String[] args) {
    EmployeeDao dao = new EmployeeDao();
    Employee employee = dao.load(1);
    System.out.println(employee.getId());
    System.out.println(employee.getName());
    

  }
}