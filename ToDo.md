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
- Update Marks ✅

### Not Implemented
- N/A

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
- Register Course ✅

### Not Implemented
- N/A

### Notes
- Student can now:
  - View registered courses
  - View grades & CGPA
- Course Registration has been implemented:
  - Student-related features can be fully tested
  - The system will register student course automatically using .csv file provided
  - Student can manually register their own courses.

---

## Summary

### Working
- Admin module: fully functional
- Lecturer module: mostly functional
- Student module: mostly functional

### To Be Done
- N/A

### Done & Checked
- Implement student course registration - done
- Implement lecturer update marks - done
- Re-test student features after registration is available - done

### suggestion
- determine whether view student marks is needed in lecturer menu - //can be considered for future function(idlan)
