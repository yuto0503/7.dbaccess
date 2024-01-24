package ex_popular_group_story;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex05 {
  public static void main(String[] args) {
    String url = "jdbc:postgresql://localhost:5432/student";
    String name = "postgres";
    String password = "postgres";

    Connection con = null;
    PreparedStatement pstmt = null;
    String sql = null;

    try {
      con = DriverManager.getConnection(url, name, password);
      sql = """
        delete from members
        where id = 2;
          """;
          pstmt = con.prepareStatement(sql);
          int numOfUpdate = pstmt.executeUpdate();
          System.out.println(numOfUpdate + "件のデータを操作しました");
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      try {
        if(con != null) {
          con.close();
        }
        if(pstmt != null) {
          pstmt.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
