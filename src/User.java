import java.util.ArrayList;
import java.util.Scanner;

class User {

  // 🔹 FIX 1: shared scanner for whole system
  protected static Scanner keyin = new Scanner(System.in);

  private String username;
  private String password;
  private String role;

  public User() { }

  public User(String username, String password, String role) {
    this.username = username;
    this.password = password;
    this.role = role;
  }

  public String getRole() {
    return role;
  }

  public boolean isAdmin() {
    return this.role.equals("ADMIN");
  }

  public boolean isLecturer() {
    return this.role.equals("LECTURER");
  }

  public boolean isStudent() {
    return this.role.equals("STUDENT");
  }

  public String toString() {
    return username + "\t" + password;
  }

  public String getAcctInfo() {
    return username;
  }

  public boolean auth(String username, String password) {
    return username.equals(this.username) && password.equals(this.password);
  }

  // ================= LOGIN =================
  public static User login(ArrayList<User> users) {

    System.out.print("Username: ");
    String username = keyin.nextLine();

    System.out.print("Password: ");
    String password = keyin.nextLine();

    for (User user : users) {
      if (user.auth(username, password)) {
        System.out.println("Authenticate: " + user.getAcctInfo());
        System.out.println();
        return user;
      }
    }

    System.out.println("Invalid username and password!\n");
    return null;
  }

  // ================= PRESS ENTER =================
  public static void pressEnterContinue() {
    System.out.print("Press enter to continue...");
    keyin.nextLine();
    System.out.println();
  }
}