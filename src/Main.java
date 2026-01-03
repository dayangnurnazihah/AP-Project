import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
  public static void main(String[] args) {
    User currentUser = null;
    ArrayList users = loadUsers();
    ArrayList courses = loadCourses();
    
    assignCourse(users, courses);
    //registerCourse(users);

    System.out.println();

    System.out.println("Course Mark & Grade Management System");
    System.out.println("-------------------------------------");

    String[] startMenus =  {"Login", "Exit"};
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
            ((Admin)currentUser).runTask(menu, users, courses);
          }

        } else if (currentUser.isLecturer()) {
          String menu = chooseMenu(lectMenus);

          if (menu == "Logout") {
            currentUser = null;
          } else {
            ((Lecturer)currentUser).runTask(menu);
          }

        } else if (currentUser.isStudent()) {
          String menu = chooseMenu(studMenus);

          if (menu == "Logout") {
            currentUser = null;
          } else {
            ((Student)currentUser).runTask(menu);
          }
        }
      }
    }
  }

  /////////////////////////////////////////////////////////////////////////////

  public static String chooseMenu(String[] menus) {
    for (int i = 0; i < menus.length; i++) {
      System.out.println((i+1) + ". " + menus[i]);
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
  public static void assignCourse(ArrayList users, ArrayList courses) {
    System.out.println("Assign courses to lecturers...");
    ArrayList<String> crsAssgList = readCSVFile("../CSV/CourseAssg.csv");
    
    for (int i = 0; i < crsAssgList.size(); i++) {
      String line = crsAssgList.get(i);
      String[] data = line.split(","); // Assuming comma as delimiter
      
      String courseCode = data[0];
      String workID = data[1];
      
      // find the course
      Course crs = (Course)courses.stream().filter(c -> ((Course)c).getCode().equals(courseCode)).findFirst().get();
      
      // find the lecturer
      Stream<Lecturer> lectStream = users.stream().filter(u -> ((User)u).isLecturer());
      Lecturer lect = lectStream.filter(u -> u.getWorkID().equals(workID)).findFirst().get();
      
      // assign lecturer's course and course's lecturer
      System.out.println(crs + " -> " + lect);
      lect.assignCourse(new CourseAssg(crs, "2005/2006", 1));
      crs.assignLecturer(new LecturerAssg(lect, "2005/2006", 1));
      
      // find the lecturer and assign with the corresponding course
      /*lectStream.forEach(u -> {
        if (u.getWorkID().equals(workID)) {
          
          
        }
      });*/
      
    }
  }
  
  // register students' courses
  public static void registerCourse(ArrayList users) {
    
  }

  /////////////////////////////////////////////////////////////////////////////

  // return array of String separate
  public static ArrayList readCSVFile(String csvFile) {
    ArrayList<String> strList = new ArrayList<>();

    System.out.printf("Read and list CSV file content (%s):\n", csvFile);
    
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
