package Advancedquestions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class aq04 {
  public static void main(String[] args) {
    String url = "jdbc:postgresql://localhost:5432/student";
    String user = "postgres";
    String password = "postgres";

    Connection con = null;
    PreparedStatement pstmt = null;
    String sql = null;

    try {
      // SQLデータベースに接続
      con = DriverManager.getConnection(url, user, password);
      // SQLを作成
      sql = """
        update members
        set name = '山崎優斗', birth_day = '1997-5-3', gender = '男', color_id = 6
        where id = 1;
          """;
      // SQL実行準備
      pstmt = con.prepareStatement(sql);
      // SQL実行
      int numOfUpdate = pstmt.executeUpdate();
      System.out.println(numOfUpdate + "件のデータを操作しました。");
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
