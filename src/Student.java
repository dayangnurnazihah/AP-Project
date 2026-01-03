import java.util.ArrayList;

class Student extends User {
  private String name;
  private String matricNo;
  private ArrayList<CourseReg> crsRegList;

  public Student(String name, String matricNo, String username, String password) {
    super(username, password, "STUDENT");
    this.name = name;
    this.matricNo = matricNo;
    this.crsRegList = new ArrayList<CourseReg>();
  }

  public String getMatricNo() {
    return matricNo;
  }

  public String toString() {
    return name + "\t" + matricNo + "\t" + super.toString();
  }

  public String getInfo() { 
    return name + " - " + matricNo; 
  }
  
  public String getAcctInfo() { 
    return name + " - " + matricNo + " (Student)"; 
  }

  public void registerCourse(CourseReg crsReg) {
    this.crsRegList.add(crsReg);
  }




  public void runTask(String menu) {
    System.out.println("Run " + this.getRole() + " task for '" + menu + "' operation\n");
    
    if (menu.equals("Register Course")) {
      System.out.println("Register Course Coming Soon!!.");
      System.out.println("This will allow you to register student to a course.");
      //???

    } else if (menu.equals("List Registered Courses")) {
      if (crsRegList.isEmpty()) {
        System.out.println("No courses registered yet.");
      } else {
        System.out.println("Your Registered Courses:");
        int num = 1;
        for (CourseReg crsReg : crsRegList) {
          System.out.printf("%d. %s (%s - Sem %d)\n", 
            num, 
            crsReg.getCourse(), 
            crsReg.toString(),
            crsReg.getSemester());
          num++;
        }
      }
    } else if (menu.equals("View Grades & CGPA")) {
      if (crsRegList.isEmpty()) {
        System.out.println("No courses registered yet.");
      } else {
        System.out.println("Your Grades:");
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-20s %-10s %-10s %-10s %-5s\n", 
          "Course", "CW", "Exam", "Total", "Grade");
        System.out.println("---------------------------------------------------------------");
        
        double totalPoints = 0;
        int totalCredits = 0;
        
        for (CourseReg crsReg : crsRegList) {
          Mark mark = crsReg.getMark();
          Course course = crsReg.getCourse();
          
          if (mark != null) {
            System.out.printf("%-20s %-10d %-10d %-10d %-5s\n",
              course.getCode(),
              mark.getCourseWork(),
              mark.getFinalExam(),
              mark.totalMark(),
              mark.grade());
            
            totalPoints += mark.point() * course.getCredits();
            totalCredits += course.getCredits();
          } else {
            System.out.printf("%-20s %-10s %-10s %-10s %-5s\n",
              course.getCode(), "-", "-", "-", "N/A");
          }
        }
        
        System.out.println("---------------------------------------------------------------");
        if (totalCredits > 0) {
          double cgpa = totalPoints / totalCredits;
          System.out.printf("CGPA: %.2f\n", cgpa);
        } else {
          System.out.println("CGPA: N/A (No marks entered yet)");
        }
      }
    }
    
    User.pressEnterContinue();
  }
}