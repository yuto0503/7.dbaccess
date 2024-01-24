package Advancedquestions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class aq03 {
  public static void main(String[] args) {
    String url = "jdbc:postgresql://localhost:5432/student";
    String user = "postgres";
    String password = "postgres";

    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = null;

    try {
      // データベースに接続する
      con = DriverManager.getConnection(url, user, password);
      // SQlを作成する
      sql = """
          select members.name as member_name, members.birth_day as member_birthday, members.gender as member_gender, colors.name as color_name
          from members
          left outer join colors
          on members.color_id = colors.id
          """;
      // SQLの実行準備をする
      pstmt = con.prepareStatement(sql);
      // SQLを実行する
      rs = pstmt.executeQuery();
      // 結果を表示する
      while (rs.next()) {
        String name = rs.getString("member_name");
        String birth_day = rs.getString("member_birthday");
        String gender = rs.getString("member_gender");
        String color_name = rs.getString("color_name");

        System.out.print(name);
        System.out.print(birth_day);
        System.out.print(gender);
        System.out.print(color_name);
        System.out.println();
      }
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
        if(rs != null) {
          rs.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
