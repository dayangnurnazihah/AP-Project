import java.util.ArrayList;

class Course {
  private String name;
  private String code;
  private int credits;
  private ArrayList<LecturerAssg> lectAssgList;
  private ArrayList<StudentReg> studRegList;

  public Course(String name, String code, int credits) {
    this.name = name;
    this.code = code;
    this.credits = credits;
    this.lectAssgList = new ArrayList<LecturerAssg>();
    this.studRegList = new ArrayList<StudentReg>();
  }

  public String getCode() {
    return this.code;
  }

  public int getCredits() {
    return this.credits;
  }

  public String toString() {
    return this.code + "-" + this.name;
  }

  public void assignLecturer(LecturerAssg lectAssg) {
    this.lectAssgList.add(lectAssg);
  }

  public void registerStudent(StudentReg studReg) {
    this.studRegList.add(studReg);
  }

  public ArrayList<StudentReg> getStudentRegList() {
    return studRegList;
  }

  public void listLecturer() {
    if (lectAssgList.isEmpty()) {
      System.out.println("No lecturers assigned yet.");
    } else {
      int num = 1;
      for (LecturerAssg lectAssg : lectAssgList) {
        System.out.printf("%d. %s\n", num, lectAssg.getLecturer().getInfo());
        num++;
      }
    }
  }

  public void listStudent() {
    if (studRegList.isEmpty()) {
      System.out.println("No students registered yet.");
    } else {
      int num = 1;
      for (StudentReg studReg : studRegList) {
        System.out.printf("%d. %s\n", num, studReg.getStudent().getInfo());
        num++;
      }
    }
  }
}