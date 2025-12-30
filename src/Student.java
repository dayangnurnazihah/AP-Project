package src;
class Student extends User {
  private String name;
  private String matricNo;

  public Student(String name, String matricNo, String username, String password) {
    super(username, password, "STUDENT");
    this.name = name;
    this.matricNo = matricNo;
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

  public void runTask(String menu) {
    System.out.println("Run " + this.getRole() + " task for '" + menu + "' operation\n");
  }
}
