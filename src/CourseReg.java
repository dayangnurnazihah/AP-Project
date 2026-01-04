class CourseReg {
  private Course course;
  private String session;
  private int semester;
  private Mark mark;

  public CourseReg(Course course, String session, int semester, Mark mark) {
    this.course = course;
    this.session = session;
    this.semester = semester;
    this.mark = mark;
  }

  public Course getCourse() {
    return course;
  }

  public Mark getMark() {
    return mark;
  }

  public int getSemester() {
    return semester;
  }

  public void setMark(Mark mark) {
    this.mark = mark;
  }

  public String toString() {
    return this.session + semester;
  }
}