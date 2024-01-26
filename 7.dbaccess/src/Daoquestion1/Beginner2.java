package Daoquestion1;

import Dao.Employee;
import Dao.EmployeeDao;

public class Beginner2 {
  public static void main(String[] args) {
    EmployeeDao dao = new EmployeeDao();
    Employee employee = dao.load(1);

    System.out.println(employee.getId());
    }
  }
