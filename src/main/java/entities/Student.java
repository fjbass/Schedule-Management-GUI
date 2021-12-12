package entities;

public class Student {
    private int semester;
    private String className;
    private int studentNumber;
    private String name;

    public Student() {
    }

    public Student(int semester, String className, int studentIdentifier, String name) {
        this.semester = semester;
        this.className = className;
        this.studentNumber = studentIdentifier;
        this.name = name;
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

    public int getStudentIdentifier() {
        return studentNumber;
    }

    public void setStudentIdentifier(int studentIdentifier) {
        this.studentNumber = studentIdentifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
