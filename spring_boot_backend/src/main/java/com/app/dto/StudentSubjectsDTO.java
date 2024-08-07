package com.app.dto;



import java.util.List;

public class StudentSubjectsDTO {
    private String studentName;
    private int numberOfSubjects;
    private List<String> subjectNames;

    public StudentSubjectsDTO(String studentName, int numberOfSubjects, List<String> subjectNames) {
        this.studentName = studentName;
        this.numberOfSubjects = numberOfSubjects;
        this.subjectNames = subjectNames;
    }

    // Getters and Setters

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getNumberOfSubjects() {
        return numberOfSubjects;
    }

    public void setNumberOfSubjects(int numberOfSubjects) {
        this.numberOfSubjects = numberOfSubjects;
    }

    public List<String> getSubjectNames() {
        return subjectNames;
    }

    public void setSubjectNames(List<String> subjectNames) {
        this.subjectNames = subjectNames;
    }
}

