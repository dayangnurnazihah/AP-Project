class CourseAssg {
  private Course course;
  private String session;
  private int semester;
  
  public CourseAssg(Course course, String session, int semester) {
    this.course = course;
    this.session = session;
    this.semester = semester;
  }
  
  public Course getCourse() {
    return course;
  }

}
