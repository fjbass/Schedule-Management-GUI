package entities;

public class Student {
    private int semester;
    private String className;
    private int studentNumber;
    private String name;

    public Student(int semester, String className, int studentNumber,
                   String name) {
        this.semester = semester;
        this.className = className;
        this.studentNumber = studentNumber;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}

