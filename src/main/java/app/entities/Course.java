package app.entities;

public class Course {
    private int semester;
    private String className;
    private String name;
    private String teacherIdentifier;
    private int credits;

    public Course() {
    }

    public Course(int semester, String className, String name, String teacherIdentifier, int credits) {
        this.semester = semester;
        this.className = className;
        this.name = name;
        this.teacherIdentifier = teacherIdentifier;
        this.credits = credits;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherIdentifier() {
        return teacherIdentifier;
    }

    public void setTeacherIdentifier(String teacherIdentifier) {
        this.teacherIdentifier = teacherIdentifier;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return semester + "," + className + "," + name + "," + teacherIdentifier + "," + credits;
    }
}
