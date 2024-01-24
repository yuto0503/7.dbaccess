import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class selectSample {
  public static void main(String[] args) {
    // 接続情報
    String url ="jdbc:postgresql://localhost:5432/student";
    String user = "postgres";
    String password = "postgres";

    // 接続情報
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = null;

    try {
      // データベースに接続
      con = DriverManager.getConnection(url, user, password);
      // SQL文を作成
      sql = "select id, name, age from employees order by age";
      // SQL実行準備
      pstmt = con.prepareStatement(sql);
      // 結果の出力
      rs = pstmt.executeQuery();

      while(rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int age = rs.getInt("age");

        System.out.print("id = " + id);
        System.out.print(" name = " + name);
        System.out.print(" age = " + age);;
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
