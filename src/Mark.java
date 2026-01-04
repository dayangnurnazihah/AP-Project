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
		if(total >= 90){
			return "A+";
		} else if(total >= 80){
			return "A";
		} else if(total >= 75){
			return "A-";
		} else if(total >= 70){
			return "B+";
		} else if(total >= 65){
			return "B";
		} else if(total >= 60){
			return "B-";
		} else if(total >= 55){
			return "C+";
		} else if(total >= 50){
			return "C";
		} else if(total >= 45){
			return "C-";
		} else if(total >= 40){
			return "D+";
		} else if(total >= 35){
			return "D";
		} else if(total >= 30){
			return "D-";
		} else if(total >= 0){
			return "E";
		} else{
			return "N/A";
		}
	}

	public double point () {
		String g = grade();
		if(g.equals("A+")){
			return 4.00;
		} else if(g.equals("A")){
			return 4.00;
		} else if(g.equals("A-")){
			return 3.67;
		} else if(g.equals("B+")){
			return 3.33;
		} else if(g.equals("B")){
			return 3.00;
		} else if(g.equals("B-")){
			return 2.67;
		} else if(g.equals("C+")){
			return 2.33;
		} else if(g.equals("C")){
			return 2.00;
		} else if(g.equals("C-")){
			return 1.67;
		} else if(g.equals("D+")){
			return 1.33;
		} else if(g.equals("D")){
			return 1.00;
		} else if(g.equals("D-")){
			return 0.67;
		} else if(g.equals("E")){
			return 0.00;
		} else {
			return 0.00;
		}
	}

	public String toString() {
		return "CW: " + courseWork + ", Exam: " + finalExam + ", Total: " + totalMark() + ", Grade: " + grade();
	}
}