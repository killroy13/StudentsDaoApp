package com.java.lesson.lesson10.dto;

/**
 * Created by User on 20.02.2018.
 */
public class Mark extends Model{
    private int mark;
    private int studentId;
    private int subjectId;

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "\n" + "id=" + id + ", mark=" + mark + ", studentId=" + studentId + ", subjectId=" + subjectId;
    }
}
