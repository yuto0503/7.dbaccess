package Advancedquestions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class aq011 {

  public static void main(String[] args) {
    String url = "jdbc:postgresql://localhost:5432/student";
    String user = "postgres";
    String password = "postgres";

    Connection con = null;
    PreparedStatement pstmt = null;
    String sql = null;

    try {
      con = DriverManager.getConnection(url, user, password);
      sql = """
        drop table if exists members;
        create table colors(
          id integer primary key,
          name text
        );
        drop table if exists members;
        create table members(
          id serial primary key,
          name text not null,
          birth_day date,
          gender varchar(1),
          color_id integer references colors(id)
        );
          """;
      pstmt = con.prepareStatement(sql);
      int numOfUpdate = pstmt.executeUpdate();

      System.out.println(numOfUpdate + "件の操作を実行しました。");
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
