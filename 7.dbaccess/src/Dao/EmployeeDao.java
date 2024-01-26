package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

  private static final String TABLE_NAME = "employees";

  public Employee load(int id) {
    Connection con = DBManager.createConnection();
    String sql = "select id, name, age, gender, department_id from " + TABLE_NAME + " where id = ?";

    try {
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, id);
      ResultSet rs = pstmt.executeQuery();

      if(rs.next()) {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        return employee;
      }
      return null;
    } catch (SQLException ex) {
      throw new RuntimeException("loadの処理を失敗しました。, ex");
    } finally {
      DBManager.closeConnection(con);
    }
  }

  public List<Employee> findByDepartmentId(int Departmentid) {
    Connection con = DBManager.createConnection();
    String sql = "select id, name, age, gender, department_id from " + TABLE_NAME + " where department_id = ?";

    try {
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, Departmentid);
      ResultSet rs = pstmt.executeQuery();

      List<Employee> employeeList = new ArrayList<>();

      while (rs.next()) {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employeeList.add(employee);
      }
      return employeeList;
    } catch (SQLException ex) {
      throw new RuntimeException("検索処理に失敗しました。, ex");
    } finally {
      DBManager.closeConnection(con);
    }
  }

}