import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

  // 🔹 FIX 1: shared lists (used by Student.java)
  public static ArrayList<User> userList = new ArrayList<>();
  public static ArrayList<Course> courseList = new ArrayList<>();

  public static void main(String[] args) {
    User currentUser = null;

    userList = loadUsers();
    courseList = loadCourses();

    assignCourse(userList, courseList);

    System.out.println();
    System.out.println("Course Mark & Grade Management System");
    System.out.println("-------------------------------------");

    String[] startMenus = {"Login", "Exit"};
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
        if (chooseMenu(startMenus).equals("Login")) {
          currentUser = User.login(userList);
        } else {
          exit = true;
        }

      } else if (currentUser.isAdmin()) {
        String menu = chooseMenu(adminMenus);

        if (menu.equals("Logout")) {
          currentUser = null;
        } else {
          ((Admin)currentUser).runTask(menu, userList, courseList);
        }

      } else if (currentUser.isLecturer()) {
        String menu = chooseMenu(lectMenus);

        if (menu.equals("Logout")) {
          currentUser = null;
        } else {
          ((Lecturer)currentUser).runTask(menu);
        }

      } else if (currentUser.isStudent()) {
        String menu = chooseMenu(studMenus);

        if (menu.equals("Logout")) {
          currentUser = null;
        } else {
          ((Student)currentUser).runTask(menu);
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
      System.out.printf("Enter your choice (1-%d): ", menus.length);
      choice = User.keyin.nextInt();
      User.keyin.nextLine();
    }

    System.out.println();
    return menus[choice - 1];
  }

  /////////////////////////////////////////////////////////////////////////////

  public static ArrayList<User> loadUsers() {
    ArrayList<User> list = new ArrayList<>();

    list.add(new Admin("admin", "abc123", "ADMIN"));

    ArrayList<String> lectList = readCSVFile("../CSV/Lecturers.csv");
    for (String line : lectList) {
      String[] data = line.split(",");
      list.add(new Lecturer(data[0], data[1], data[2], data[3]));
    }

    ArrayList<String> studList = readCSVFile("../CSV/Students.csv");
    for (String line : studList) {
      String[] data = line.split(",");
      list.add(new Student(data[0], data[1], data[2], data[3]));
    }

    return list;
  }

  public static ArrayList<Course> loadCourses() {
    ArrayList<Course> list = new ArrayList<>();

    ArrayList<String> courseCSV = readCSVFile("../CSV/Courses.csv");
    for (String line : courseCSV) {
      String[] data = line.split(",");
      list.add(new Course(data[0], data[1], Integer.parseInt(data[2])));
    }

    return list;
  }

  public static void assignCourse(ArrayList<User> users, ArrayList<Course> courses) {
    ArrayList<String> crsAssgList = readCSVFile("../CSV/CourseAssg.csv");

    for (String line : crsAssgList) {
      String[] data = line.split(",");
      String courseCode = data[0];
      String workID = data[1];

      Course crs = courses.stream()
        .filter(c -> c.getCode().equals(courseCode))
        .findFirst().get();

      Lecturer lect = users.stream()
        .filter(u -> u.isLecturer())
        .map(u -> (Lecturer)u)
        .filter(l -> l.getWorkID().equals(workID))
        .findFirst().get();

      lect.assignCourse(new CourseAssg(crs, "2005/2006", 1));
      crs.assignLecturer(new LecturerAssg(lect, "2005/2006", 1));
    }
  }

  /////////////////////////////////////////////////////////////////////////////

  public static ArrayList<String> readCSVFile(String csvFile) {
    ArrayList<String> list = new ArrayList<>();

    try (Scanner scanner = new Scanner(new File(csvFile))) {
      while (scanner.hasNextLine()) {
        list.add(scanner.nextLine().replace("\uFEFF", ""));
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return list;
  }
}