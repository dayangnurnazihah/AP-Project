import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
  public static void main(String[] args) {
    User currentUser = null;
    ArrayList users = loadUsers();
    ArrayList courses = loadCourses();

    assignCourse(users, courses);
    registerCourse(users, courses);

    System.out.println();

    String[] startMenus = { "Login", "Exit" };
    String[] adminMenus = {
        "List Courses",
        "Course Info",
        "List Students",
        "List Lecturer",
        "Assign Course",
        "Logout"
    };

    String[] lectMenus = {
        "List Assigned Courses",
        "List Students",
        "Update Marks",
        "Logout"
    };

    String[] studMenus = {
        "Register Course",
        "List Registered Courses",
        "View Grades & CGPA",
        "Logout"
    };

    boolean exit = false;
    while (!exit) {
      if (currentUser == null) {
        System.out.println("Course Mark & Grade Management System");
        System.out.println("-------------------------------------");
        if (chooseMenu(startMenus) == "Login") {
          currentUser = User.login(users);
        } else {
          exit = true;
        }
      } else {
        if (currentUser.isAdmin()) {
          String menu = chooseMenu(adminMenus);

          if (menu == "Logout") {
            currentUser = null;
          } else {
            ((Admin) currentUser).runTask(menu, users, courses);
          }

        } else if (currentUser.isLecturer()) {
          String menu = chooseMenu(lectMenus);

          if (menu == "Logout") {
            currentUser = null;
          } else {
            ((Lecturer) currentUser).runTask(menu);
          }

        } else if (currentUser.isStudent()) {
          String menu = chooseMenu(studMenus);

          if (menu == "Logout") {
            currentUser = null;
          } else {
            ((Student) currentUser).runTask(menu, courses);
          }
        }
      }
    }
  }

  /////////////////////////////////////////////////////////////////////////////

  public static String chooseMenu(String[] menus) {
    for (int i = 0; i < menus.length; i++) {
      System.out.println((i + 1) + ". " + menus[i]);
    }

    int choice = 0;

    while (choice < 1 || choice > menus.length) {
      Scanner scn = new Scanner(System.in);
      System.out.printf("Enter your choice (1-%d): ", menus.length);
      choice = scn.nextInt();
    }

    System.out.println();

    return menus[choice - 1];
  }

  /////////////////////////////////////////////////////////////////////////////

  // create user instances (admin, lecturers, and students)
  public static ArrayList loadUsers() {
    ArrayList list = new ArrayList();

    System.out.println("Load user (Admin)...");
    // new Admin(username, password, role)
    list.add(new Admin("admin", "abc123", "ADMIN"));

    System.out.println();

    System.out.println("Load users (Lecturer)...");
    ArrayList<String> lectList = readCSVFile("../CSV/Lecturers.csv");

    for (int i = 0; i < lectList.size(); i++) {
      String line = lectList.get(i);
      String[] data = line.split(","); // Assuming comma as delimiter

      // new Lecturer(name, workID, username, password);
      Lecturer lect = new Lecturer(data[0], data[1], data[2], data[3]);
      list.add(lect);
      System.out.println(lect);
    }

    System.out.println();

    System.out.println("Load users (Students)...");
    ArrayList<String> studList = readCSVFile("../CSV/Students.csv");

    for (int i = 0; i < studList.size(); i++) {
      String line = studList.get(i);
      String[] data = line.split(","); // Assuming comma as delimiter

      // new Student(name, matricNo, username, password)
      Student stud = new Student(data[0], data[1], data[2], data[3]);
      list.add(stud);
      System.out.println(stud);
    }

    System.out.println();

    return list;
  }

  // create course instances
  public static ArrayList loadCourses() {
    ArrayList list = new ArrayList();

    System.out.println("Load courses...");
    ArrayList<String> courseList = readCSVFile("../CSV/Courses.csv");

    for (int i = 0; i < courseList.size(); i++) {
      String line = courseList.get(i);
      String[] data = line.split(","); // Assuming comma as delimiter

      // new Course(name, code, credits);
      Course crs = new Course(data[0], data[1], Integer.parseInt(data[2]));
      list.add(crs);
      System.out.println(crs);
    }

    System.out.println();

    return list;
  }

  // assign courses to lecturersCourseAssg.csv
  public static void assignCourse(
      ArrayList<User> users,
      ArrayList<Course> courses) {

    System.out.println("Assign courses to lecturers...");
    ArrayList<String> crsAssgList = readCSVFile("../CSV/CourseAssg.csv");

    for (String line : crsAssgList) {

      String[] data = line.split(",");
      String courseCode = data[0];
      String workID = data[1];

      // find the course
      Course course = courses.stream()
          .filter(c -> c.getCode().equals(courseCode))
          .findFirst()
          .orElse(null);

      if (course == null)
        continue;

      // find the lecturer
      Lecturer lecturer = users.stream()
          .filter(User::isLecturer)
          .map(u -> (Lecturer) u)
          .filter(l -> l.getWorkID().equals(workID))
          .findFirst()
          .orElse(null);

      if (lecturer == null)
        continue;

      // assign lecturer ↔ course
      System.out.println(course + " -> " + lecturer);

      lecturer.assignCourse(
          new CourseAssg(course, "2025/2026", 1));

      course.assignLecturer(
          new LecturerAssg(lecturer, "2025/2026", 1));
    }
  }

  // register students' courses
  public static void registerCourse(ArrayList<User> users, ArrayList<Course> courses) {

    System.out.println("\nRegistering students to courses...");

    File folder = new File("../CSV/CourseMarks");
    File[] files = folder.listFiles((dir, name) -> name.endsWith(".csv"));

    if (files == null)
      return;

    for (File file : files) {

      // course code = file name
      String courseCode = file.getName().replace(".csv", "");

      // find course
      Course course = (Course) courses.stream()
          .filter(c -> ((Course) c).getCode().equals(courseCode))
          .findFirst()
          .orElse(null);

      if (course == null)
        continue;

      ArrayList<String> lines = readCSVFile(file.getPath());

      for (String line : lines) {

        String[] data = line.split(",");

        String matricNo = data[0];
        int cw = Integer.parseInt(data[1]);
        int exam = Integer.parseInt(data[2]);

        // find student
        Student student = (Student) users.stream()
            .filter(u -> ((User) u).isStudent())
            .map(u -> (Student) u)
            .filter(s -> s.getMatricNo().equals(matricNo))
            .findFirst()
            .orElse(null);

        if (student != null) {

          Mark mark = new Mark(cw, exam);

          CourseReg crsReg = new CourseReg(course, "2025/2026", 1, mark);

          StudentReg studReg = new StudentReg(student, "2025/2026", 1);

          student.registerCourse(crsReg);
          course.registerStudent(studReg);
        }
      }
    }
  }

  /////////////////////////////////////////////////////////////////////////////

  // return array of String separate
  public static ArrayList readCSVFile(String csvFile) {
    ArrayList<String> strList = new ArrayList<>();

    System.out.printf("\nRead and list CSV file content (%s):\n", csvFile);

    try (Scanner scanner = new Scanner(new File(csvFile))) {
      while (scanner.hasNextLine()) {
        // Read file content and remove Byte Order Mark (BOM) if present
        // The BOM included by Excel when save the file as CSV
        String line = scanner.nextLine().replace("\uFEFF", "");
        strList.add(line);
        System.out.println(line);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return strList;
  }
}
