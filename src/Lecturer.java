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
  
  public String getWorkID() {
    return workID;
  }

  public String toString() {
    return this.name + "\t" + this.workID + "\t" + super.toString();
  }
  
  public String getInfo() { 
    return name + " - " + workID ; 
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
        int num = 1;
        for (CourseAssg crsAssg : crsAssgList) {
          System.out.printf("%d. %s (%s) - %d credits\n", 
            num, 
            crsAssg.getCourse(), 
            crsAssg.toString(),
            crsAssg.getCourse().getCredits());
          num++;
        }
        System.out.println("\nTotal Credits: " + getActCredit());
      }
      
    } else if (menu.equals("List Students")) {
      if (crsAssgList.isEmpty()) {
        System.out.println("No courses assigned yet.");
      } else {
        Scanner scn = new Scanner(System.in);
        
        // Show courses first
        System.out.println("Your Assigned Courses:");
        for (int i = 0; i < crsAssgList.size(); i++) {
          System.out.printf("%d. %s\n", (i+1), crsAssgList.get(i).getCourse());
        }
        
        System.out.printf("\nEnter course number to view students (1-%d): ", crsAssgList.size());
        int choice = scn.nextInt();
        
        if (choice > 0 && choice <= crsAssgList.size()) {
          Course selectedCourse = crsAssgList.get(choice - 1).getCourse();
          System.out.println("\nStudents in " + selectedCourse + ":");
          System.out.println("---------------------------------------------------------------");
          selectedCourse.listStudent();
        } else {
          System.out.println("Invalid choice!");
        }
      }
      
    } else if (menu.equals("Update Marks")) {
      System.out.println("Update Marks feature coming soon!");
      System.out.println("This will allow you to enter/update coursework and exam marks for students.");
    }
    
    User.pressEnterContinue();
  }
}
