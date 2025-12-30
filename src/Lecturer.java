package src;
import java.util.ArrayList;

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
  
  public void assignCourse(CourseAssg crsAssg) {
    this.crsAssgList.add(crsAssg);
  }

  public void runTask(String menu) {
    System.out.println("Run " + this.getRole() + " task for '" + menu + "' operation\n");
    
    if (menu.equals("List Assigned Courses")) {
      for (CourseAssg crsAssg : crsAssgList) {
        System.out.println(crsAssg.getCourse());
      }
    }
    
    User.pressEnterContinue();
  }
}
