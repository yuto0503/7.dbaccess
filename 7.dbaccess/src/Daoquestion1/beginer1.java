package Daoquestion1;

public class beginer1 {
  public static void main(String[] args) {
    MemberDao dao = new MemberDao();
    Member member = new Member();

    member.setId(1);
    member.setName("大野智");
    
    member = dao.load(1);
    System.out.println("id = " +member.getId());
    System.out.println("name = " + member.getName());
  }
}
