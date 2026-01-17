import java.util.ArrayList;
import java.util.Scanner;

class Lecturer extends User {
  private String name;
  private String workID;
  private ArrayList<CourseAssg> crsAssgList;

  public Lecturer(String name, String workID, String username, String password) {
    super(username, password, "LECTURER");
    this.name = name;
    this.workID = workID;
    this.crsAssgList = new ArrayList<CourseAssg>();
  }

  public String getName() {
    return name;
  }
  public String getWorkID() {
    return workID;
  }

  public String toString() {
    return this.name + "\t" + this.workID + "\t" + super.toString();
  }

  public String getInfo() {
    return name + " - " + workID;
  }

  public String getAcctInfo() {
    return name + " - " + workID + " (Lecturer)";
  }

  public int getActCredit() {
    int totalCredits = 0;
    for (CourseAssg crsAssg : crsAssgList) {
      totalCredits += crsAssg.getCourse().getCredits();
    }
    return totalCredits;
  }

  public void assignCourse(CourseAssg crsAssg) {
    this.crsAssgList.add(crsAssg);
  }

  public void runTask(String menu) {
    System.out.println("Run " + this.getRole() + " task for '" + menu + "' operation\n");

    if (menu.equals("List Assigned Courses")) {
      if (crsAssgList.isEmpty()) {
        System.out.println("No courses assigned yet.");
      } else {
        System.out.println("Your Assigned Courses:");
        System.out.println("==============================================================");
        System.out.printf("| %-4s | %-25s | %-12s | %-7s |%n", "NO.", "COURSE", "SESSION", "CREDITS");
        System.out.println("==============================================================");
        int num = 1;
        for (CourseAssg crsAssg : crsAssgList) {
          System.out.printf("| %3d. | %-25.25s | %-12.12s | %7d |%n",
              num,
              crsAssg.getCourse(),
              crsAssg.toString(),
              crsAssg.getCourse().getCredits());
          num++;
        }
        System.out.println("==============================================================");
        System.out.println("\nTotal Credits: " + getActCredit());
      }

    } else if (menu.equals("List Students")) {
      if (crsAssgList.isEmpty()) {
        System.out.println("No courses assigned yet.");
      } else {
        Scanner scn = new Scanner(System.in);

        // Show courses first
        System.out.println("Your Assigned Courses:");
        System.out.println("===============================================");
        System.out.printf("| %-4s | %-25s | %-7s |%n", "NO.", "COURSE", "CREDITS");
        System.out.println("===============================================");
        for (int i = 0; i < crsAssgList.size(); i++) {
          Course course = crsAssgList.get(i).getCourse();
          System.out.printf("| %3d. | %-25.25s | %7d |%n", (i + 1), course, course.getCredits());
        }
        System.out.println("===============================================");

        System.out.printf("\nEnter course number to view students (1-%d): ", crsAssgList.size());
        int choice = scn.nextInt();

        if (choice > 0 && choice <= crsAssgList.size()) {
          Course selectedCourse = crsAssgList.get(choice - 1).getCourse();
          System.out.println("\nStudents in " + selectedCourse + ":");
          selectedCourse.listStudent();
        } else {
          System.out.println("Invalid choice!");
        }
      }

    } else if (menu.equals("Update Marks")) {
      System.out.println("Update Marks for student\n");
      updateMarks();
    }

    User.pressEnterContinue();
  }

  public void updateMarks() {

    Scanner scn = new Scanner(System.in);

    if (crsAssgList.isEmpty()) {
      System.out.println("No courses assigned.");
      return;
    }

    System.out.println("Update Marks");
    System.out.println("============");

    System.out.println("===============================================");
    System.out.printf("| %-4s | %-25s | %-7s |%n", "NO.", "COURSE", "CREDITS");
    System.out.println("===============================================");
    for (int i = 0; i < crsAssgList.size(); i++) {
      CourseAssg ca = crsAssgList.get(i);
      System.out.printf("| %3d. | %-25.25s | %7d |%n", i + 1, ca.getCourse(), ca.getCourse().getCredits());
    }
    System.out.println("===============================================");

    System.out.println();
    System.out.print("Select course (NO.): ");
    int cChoice = scn.nextInt();

    if (cChoice < 1 || cChoice > crsAssgList.size()) {
      System.out.println("Invalid course selection.");
      return;
    }

    Course selectedCourse = crsAssgList.get(cChoice - 1).getCourse();

    ArrayList<StudentReg> studRegs = selectedCourse.getStudentRegList();

    if (studRegs.isEmpty()) {
      System.out.println("No students registered in this course.");
      return;
    }

    System.out.println("\nStudents:");
    System.out.println("=================================================");
    System.out.printf("| %-4s | %-25s | %-10s |%n", "NO.", "NAME", "MATRIC");
    System.out.println("=================================================");
    for (int i = 0; i < studRegs.size(); i++) {
      Student studentEntry = studRegs.get(i).getStudent();
      System.out.printf("| %3d. | %-25.25s | %-10.10s |%n",
          i + 1,
          studentEntry.getName(),
          studentEntry.getMatricNo());
    }
    System.out.println("=================================================");

    System.out.print("Select student (NO.)");
    int sChoice = scn.nextInt();

    if (sChoice < 1 || sChoice > studRegs.size()) {
      System.out.println("Invalid student selection.");
      return;
    }

    Student student = studRegs.get(sChoice - 1).getStudent();

    CourseReg courseReg = null;

    for (CourseReg cr : student.getCourseRegList()) {
      if (cr.getCourse().equals(selectedCourse)) {
        courseReg = cr;
        break;
      }
    }

    if (courseReg == null) {
      System.out.println("Course registration not found.");
      return;
    }

    System.out.print("Enter coursework marks: ");
    int cw = scn.nextInt();

    System.out.print("Enter final exam marks: ");
    int exam = scn.nextInt();

    courseReg.setMark(new Mark(cw, exam));

    System.out.println("Marks updated successfully.");
  }

}
