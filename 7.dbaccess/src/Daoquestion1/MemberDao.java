package Daoquestion1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.DBManager;

public class MemberDao {
  private static final String TABLE_NAME = "member";

  public Member load(int id) {
    Connection con = DBManager.createConnection();
    String sql = "select id, name, birth_day, gender, color_Id from " + TABLE_NAME + " where id = ?"; 

    try {
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, id);
      ResultSet rs =pstmt.executeQuery();

      if(rs.next()) {
        Member member =new Member();
        member.setId(rs.getInt("id"));
        member.setName(rs.getString("name"));
        return member;
      }
      return null;
    } catch (SQLException ex) {
      throw new RuntimeException("load処理を失敗しました。", ex);
    } finally {
      DBManager.closeConnection(con);                                                                                                                                                                                                                                                                                                                                                                                                                                                       
    }
  }
  
  public List<Member> findByName(String name) {
    Connection con = DBManager.createConnection();
    String sql = "select id, name, birth_day, gender, color_id from " + TABLE_NAME + " where name like = ?";

    try {
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setString(1, "%" + name + "%" );
      ResultSet rs = pstmt.executeQuery();

      List<Member> memberList = new ArrayList<>();

      while (rs.next()) {
        Member member = new Member();
        member.setId(rs.getInt("id"));
        member.setName(rs.getString("name"));
        memberList.add(member);
      }
      return memberList;
    } catch (SQLException ex) {
      throw new RuntimeException("曖昧検索失敗しました。");
    } finally {
      DBManager.closeConnection(con);
    }
  }

  public int insert(Member member) {
    Connection con = DBManager.createConnection();
    String sql = "insert into " + TABLE_NAME + "(id, name, birth_day, gender, color_id)" + "values(?, ?, ?, ?, ?)";

    try {
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, member.getId());
      pstmt.setString(2, member.getName());

      int affected = pstmt.executeUpdate();
      return affected;
    } catch (SQLException ex) {
      throw new RuntimeException("insert書理に失敗しました。", ex);
    } finally {
      DBManager.closeConnection(con);
    }
  }

  public int update(Member member) {
    Connection con = DBManager.createConnection();
    String sql = "update " + TABLE_NAME + "set id = ?, name = ?, birth_day = ?, gender = ?, color_id = ?";
    
    try {
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, member.getId());
      pstmt.setString(2, member.getName());

      int affected = pstmt.executeUpdate();
      return affected;
    } catch (SQLException ex) {
      throw new RuntimeException("update処理に失敗しました。", ex);
    } finally {
      DBManager.closeConnection(con);
    }
  }

  public int deleteById(int id) {
    Connection con = DBManager.createConnection();
    String sql = "delete from " + TABLE_NAME + " where idn= ?";

    try {
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, id);

      int affected = pstmt.executeUpdate();
      return affected;
    } catch (SQLException ex) {
      throw new RuntimeException("delete処理を失敗しました。", ex);
    } finally {
      DBManager.closeConnection(con);
    }
  }

}
