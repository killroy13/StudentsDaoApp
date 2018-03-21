package com.java.lesson.lesson10.dto;

/**
 * Created by User on 14.02.2018.
 */
public class Student extends Model {
    private int id;
    private String firstName;
    private String secondName;
    private int course;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String f_name) {
        this.firstName = f_name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String s_name) {
        this.secondName = s_name;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public Student(){

    }

    /** Метод для читабельного вывода таблицы*/
    @Override
    public String toString() {
        return "\n" + "id=" + id + " f_name=" + firstName + " s_name=" + secondName + " course=" + course;
    }
}
