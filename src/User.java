import java.util.ArrayList;
import java.util.Scanner;

class User {
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
    return (username.equals(this.username) && password.equals(this.password));
  }

  public static User login(ArrayList<User> users) {
    Scanner scn = new Scanner(System.in);
    System.out.print("Username: ");
    String username = scn.nextLine();

    System.out.print("Password: ");
    String password = scn.nextLine();

    for (int i = 0; i < users.size(); i++) {
      User user = (User)users.get(i);
      if (user.auth(username, password)) {
        System.out.println("Authenticate: " + user.getAcctInfo());
        System.out.println();
        return user;
      }
    }

    System.out.println("Invalid username and password!\n");
    return null;
  }
  
  public static void pressEnterContinue() {
    Scanner scn = new Scanner(System.in);
    System.out.print("Press enter to continue...");
    String enter = scn.nextLine();
    System.out.println();
  }
}