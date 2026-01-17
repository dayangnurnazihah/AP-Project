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
      System.out.println("======== LECTURER LIST ========");
      System.out.println();
      System.out.println("=================================================");
      System.out.printf("| %-4s | %-25s | %-10s |%n", "NO.", "NAME", "WORK ID");
      System.out.println("=================================================");
      for (LecturerAssg lectAssg : lectAssgList) {
        System.out.printf("| %3d. | %-25.25s | %-10.10s |%n",
            num,
            lectAssg.getLecturer().getName(),
            lectAssg.getLecturer().getWorkID());
        num++;
      }
      System.out.println("=================================================");
    }
  }

  public void listStudent() {
    if (studRegList.isEmpty()) {
      System.out.println("No students registered yet.");
    } else {
      int num = 1;
      System.out.println("========= STUDENT LIST ===========");
      System.out.println();
      System.out.println("=================================================");
      System.out.printf("| %-4s | %-25s | %-10s |%n", "NO.", "NAME", "MATRIC");
      System.out.println("=================================================");
      for (StudentReg studReg : studRegList) {
        System.out.printf("| %3d. | %-25s | %-10s |%n", num, studReg.getStudent().getName(),
            studReg.getStudent().getMatricNo());
        num++;
      }
      System.out.println("=================================================");

    }
  }
}
