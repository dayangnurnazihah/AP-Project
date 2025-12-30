package src;
import java.util.ArrayList;

class Course {
  private String name;
  private String code;
  private int credits;
  private ArrayList<LecturerAssg> lectAssgList;

  public Course(String name, String code, int credits) {
    this.name = name;
    this.code = code;
    this.credits = credits;
    this.lectAssgList = new ArrayList<LecturerAssg>();
  }
  
  public String getCode() { return this.code; }
  public int getCredits() { return this.credits; }

  public String toString() {
    return this.code + "-" + this.name;
  }
  
  public void assignLecturer(LecturerAssg lectAssg) {
    this.lectAssgList.add(lectAssg);
  }
  
  public void listLecturer() {
    int num = 1;
    for (LecturerAssg lectAssg : lectAssgList) {
      System.out.printf("%d. %s\n", num, lectAssg.getLecturer().getInfo());
      num++;
    }
    
  }
}
