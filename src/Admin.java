import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

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
      System.out.println("All Courses:");
      System.out.println("---------------------------------------------------------------");
      int num = 1;
      for (Object crs : courses) {
        Course course = (Course) crs;
        System.out.printf("%d. %s (%d credits)\n", num, course, course.getCredits());
        num++;
      }
      System.out.println();

    } else if (menu.equals("Course Info")) {
      Scanner scn = new Scanner(System.in);
      System.out.print("Course Code: ");
      String courseCode = scn.nextLine();

      try {
        // find the course
        Course crs = (Course) courses.stream().filter(c -> ((Course) c).getCode().equals(courseCode)).findFirst().get();

        System.out.println(crs + "\n");

        System.out.println("Lecturers:");
        crs.listLecturer();
        System.out.println();

        System.out.println("Students:");
        crs.listStudent();

      } catch (Exception e) {
        System.out.println("Course not found!");
      }

    } else if (menu.equals("List Students")) {
      System.out.println("All Students:");
      System.out.println("---------------------------------------------------------------");
      Stream<Student> studStream = users.stream().filter(u -> ((User) u).isStudent()).map(u -> (Student) u);

      int[] num = { 1 };
      studStream.forEach(stud -> {
        System.out.println(num[0] + ". " + stud.getInfo());
        num[0]++;
      });

      if (num[0] == 1) {
        System.out.println("No students found.");
      }

    } else if (menu.equals("List Lecturer")) {
      System.out.println("All Lecturers:");
      System.out.println("---------------------------------------------------------------");
      Stream<Lecturer> lectStream = users.stream().filter(u -> ((User) u).isLecturer()).map(u -> (Lecturer) u);

      int[] num = { 1 };
      lectStream.forEach(lect -> {
        System.out.println(num[0] + ". " + lect.getInfo());
        num[0]++;
      });

      if (num[0] == 1) {
        System.out.println("No lecturers found.");
      }

    } else if (menu.equals("Assign Course")) { // this assign course only applied to lecturer, may need to add function
                                               // for registering student too
      Scanner scn = new Scanner(System.in);

      System.out.println("Assign Course to Lecturer");
      System.out.println("-------------------------");

      System.out.print("Course Code: ");
      String courseCode = scn.nextLine();

      System.out.print("Lecturer Work ID: ");
      String workID = scn.nextLine();

      System.out.print("Session (e.g., 2025/2026): ");
      String session = scn.nextLine();

      System.out.print("Semester: ");
      int semester = scn.nextInt();

      try {
        // find the course
        Course crs = (Course) courses.stream().filter(c -> ((Course) c).getCode().equals(courseCode)).findFirst().get();

        // find the lecturer
        Stream<Lecturer> lectStream = users.stream().filter(u -> ((User) u).isLecturer()).map(u -> (Lecturer) u);
        Lecturer lect = lectStream
            .filter(u -> u.getWorkID().equals(workID))
            .findFirst()
            .get();

        // assign
        lect.assignCourse(new CourseAssg(crs, session, semester));
        crs.assignLecturer(new LecturerAssg(lect, session, semester));

        System.out.println("\nSuccess! Assigned " + crs + " to " + lect.getInfo());

      } catch (Exception e) {
        System.out.println("Error: Course or Lecturer not found!");
      }
    }

    User.pressEnterContinue();
  }
}