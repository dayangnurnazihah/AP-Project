# Course Mark & Grade Management System

## Admin Module (Terminal UI)

### Available Functions
- Login ✅
- List Courses ✅
- Course Info (view lecturers & students) ✅
- List Students (all students) ✅
- List Lecturer (all lecturers) ✅
- Assign Course to Lecturer ✅
- Logout ✅

### Notes
- Admin has full access to all data.
- Courses may show no students if registration not done.
- CSV data is assumed valid.

---

## Lecturer Module (Terminal UI)

### Available Functions
- Login ✅
- List Assigned Courses ✅
- List Students (by assigned course) ✅
- Logout ✅

### Not Implemented
- Update Marks ⏳

### Notes
- Lecturer can only view students from their assigned courses.
- Marks update logic still needs to be implemented.

---

## Student Module (Terminal UI)

### Available Functions
- Login ✅
- List Registered Courses ✅ (empty if not registered)
- View Grades & CGPA ✅ (empty if not registered)
- Logout ✅

### Not Implemented
- Register Course ⏳

### Notes
- Student **must register courses first** before:
  - viewing registered courses
  - viewing grades & CGPA
- Since course registration is not implemented yet:
  - Student-related features cannot be fully tested
  - Further testing and fixes will be done after registration is completed

---

## Function
Please re-check this 2 function. Do whatever needed with this 2.

### Main.java (Line 212)

```

public static void registerCourse(ArrayList users) {

}

```

### Course.java (Line 29)

```

public void registerStudent(StudentReg studReg) {
 this.studRegList.add(studReg);
}

```
---

## Summary

### Working
- Admin module: fully functional
- Lecturer module: mostly functional
- Student module: partially functional

### To Be Done
- Implement student course registration - done
- Implement lecturer update marks - done
- Re-test student features after registration is available 

# new
- determine whether view student marks is needed in lecturer menu
