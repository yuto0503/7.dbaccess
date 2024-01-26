package Dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateSample {
  public static void main(String[] args) {
    String url ="jdbc:postgresql://localhost:5432/student";
    String user = "postgres";
    String password = "postgres";

    Connection con = null;
    PreparedStatement pstmt = null;
    String sql = null;

    try {
      // データベースに接続
      con = DriverManager.getConnection(url, user, password);
      // sql作成
      sql = """
          insert into employees(name, age)
          values ('テスト太郎', 19);
          """;
      // sql実行準備
      pstmt = con.prepareStatement(sql);
      // sql実行
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
