package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDao {
  private static final String TABLE_NAME = "departments";

  public Department load(int id) {
    Connection con = DBManager.createConnection();

    String sql = "select id, name from " + TABLE_NAME + " where id = ?";

    try {
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, id);
      ResultSet rs = pstmt.executeQuery();

      if(rs.next()) {
        Department department = new Department();
        department.setId(rs.getInt("id"));
        department.setName(rs.getString("name"));
        return department;
      }
      return null;
    } catch (SQLException ex) {
      throw new RuntimeException("loadに失敗しました。", ex);
    } finally {
      DBManager.closeConnection(con);
    }
  }
  public int insert(Department department) {
    Connection con = DBManager.createConnection();
    String sql = "insert into " + TABLE_NAME + " (id,name)" + "values(?, ?)";

    try {
      PreparedStatement pstmt = con.prepareStatement(sql);

      pstmt.setInt(1, department.getId());
      pstmt.setString(2, department.getName());

      int affected = pstmt.executeUpdate();
      return affected;
    } catch (SQLException ex) {
      System.out.println("SQL = " + sql);
      throw new RuntimeException("insert処理に失敗しました", ex);
    } finally {
      DBManager.closeConnection(con);
    }
}

public int update(Department department) {
  Connection con = DBManager.createConnection();

  String sql = "update" + TABLE_NAME + " set name = ? where id = ?";  

  try {
    PreparedStatement pstmt = con.prepareStatement(sql);
    pstmt.setString(1, department.getName());

    int affected = pstmt.executeUpdate();
    return affected;
  } catch (SQLException ex) {
    throw new RuntimeException("updateに失敗しました。", ex);
  } finally {
    DBManager.closeConnection(con);
  }
}

public int deleteById(int id) {
  Connection con = DBManager.createConnection();
  String sql = "delete from " + TABLE_NAME + " where id = ?";

  try {
    PreparedStatement pstmt = con.prepareStatement(sql);
    pstmt.setInt(1, id);
    int affected =pstmt.executeUpdate();
    return affected;
  } catch (SQLException ex) {
    throw new RuntimeException();
  } finally {
    DBManager.closeConnection(con);
  }
}

}
