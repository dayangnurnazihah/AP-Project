class LecturerAssg {
  private Lecturer lecturer;
  private String session;
  private int semester;
  
  public LecturerAssg(Lecturer lecturer, String session, int semester) {
    this.lecturer= lecturer;
    this.session = session;
    this.semester = semester;
  }
  
  public Lecturer getLecturer() {
    return lecturer;
  }

}
