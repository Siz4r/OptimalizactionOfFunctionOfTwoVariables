package com.school;

class Test {
    private static int count = 0;
    private String studentId;

    static {
        System.out.println("Static initilizer has launched");
        count++;

    }

    {
        studentId = "";
        printNumberOfStudents();
        count++;
    }

    public Test(String studentId) {
        this.studentId = studentId;
    }

    public static void printNumberOfStudents() {
        System.out.println(count);
    }

    public void printStudentId() {
        System.out.println(this.studentId);
    }
}
