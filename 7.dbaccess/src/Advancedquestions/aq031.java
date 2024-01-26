package Advancedquestions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class aq031 {
  public static void main(String[] args) {
    String url = "jdbc:postgresql://localhost:5432/student";
    String user = "postgres";
    String password = "postgres";

    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = null;

    try {
      con = DriverManager.getConnection(url, user, password);
      sql = """
          select members.name as member_name, 
                  members.birth_day as member_birthday, 
                  members.gender as member_gender, 
                  colors.name as color_name
          from members
          left outer join colors
          on members.color_id = colors.id;
          """;
      pstmt = con.prepareStatement(sql);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        String name = rs.getString("member_name");
        String birth_day = rs.getString("member_birthday");
        String gender = rs.getString("member_gender");
        String color_name = rs.getString("color_name");

        System.out.println(name + " " + birth_day + " " + gender + " " + color_name);
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
