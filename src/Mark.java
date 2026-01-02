class Mark {
	private int courseWork;
	private int finalExam;

	public Mark (int courseWork, int finalExam) {
		this.courseWork = courseWork;
		this.finalExam = finalExam;
	}

	public int getCourseWork() {
		return courseWork;
	}

	public int getFinalExam() {
		return finalExam;
	}

	public void setMark (int cw, int fe) {
		this.courseWork = cw;
		this.finalExam = fe;
	}

	public int totalMark () {
		return courseWork + finalExam;
	}

	public String grade () {
		int total = totalMark();
		if(total >= 70){
			return "A";
		} else if(total >= 50){
			return "B";
		} else{
			return "C";
		}
	}

	public double point () {
		String g = grade();
		if(g.equals("A")){
			return 4.00;
		} else if(g.equals("B")){
			return 3.67;
		} else{
			return 3.00;
		}
	}

	public String toString() {
		return "CW: " + courseWork + ", Exam: " + finalExam + ", Total: " + totalMark() + ", Grade: " + grade();
	}
}