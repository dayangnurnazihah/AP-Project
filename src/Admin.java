import java.util.ArrayList;
import java.util.Scanner;

class Admin extends User {
  public Admin(String username, String password, String role) {
    super(username, password, role);
  }

  public String getAcctInfo() {
    return "Admin (System Administrator)";
  }

  public void runTask(String menu, ArrayList users, ArrayList courses) {
    System.out.println("Run " + this.getRole() + " task for '" + menu + "' operation\n");

    if (menu.equals("List Courses")) {
      for (Object crs : courses) {
        System.out.println((Course) crs);
      }
    } else if (menu.equals("Course Info")) {
      Scanner scn = new Scanner(System.in);
      System.out.print("Course Code: ");
      String courseCode = scn.nextLine();

      // find the course
      Course crs = (Course) courses.stream().filter(c -> ((Course) c).getCode().equals(courseCode)).findFirst().get();
      System.out.println(crs + "\n");

      System.out.println("Lecturers:");
      crs.listLecturer();
      System.out.println();

      System.out.println("Students:");
      // ???

      scn.close();

    }

    User.pressEnterContinue();
  }
}
