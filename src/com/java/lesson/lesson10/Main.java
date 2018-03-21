package com.java.lesson.lesson10;

//import com.java.lesson.lesson10.dao.impl.MarksDaoImpl;

import com.java.lesson.lesson10.dao.impl.MarksDaoImpl;
import com.java.lesson.lesson10.dao.impl.StudentsDaoImpl;
import com.java.lesson.lesson10.dao.impl.SubjectsDaoImpl;
import com.java.lesson.lesson10.dto.Mark;
import com.java.lesson.lesson10.dto.Student;
import com.java.lesson.lesson10.dto.Subject;

/**
 * Created by User on 14.02.2018.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        /** Students */

        StudentsDaoImpl std = new StudentsDaoImpl();

        Student student1 = new Student();
        student1.setId(5);
        student1.setFirstName("Tom");
        student1.setSecondName("Array");
        student1.setCourse(3);

        Student student2 = new Student();
        student2.setId(19);
        student2.setFirstName("test2");
        student2.setSecondName("test2");
        student2.setCourse(3);

        /*Вывод по ID */
//        System.out.println(std.getById(1));

        /*Обновить данные*/
//        std.update(student2);

        /*Удалить из БД*/
//        std.delete(5);

        /*Добавить данные*/
//        std.insert(student1);

        /*Вывод списка stud*/
//        System.out.println(std.getAll());

        /*Закрытие ps и connection*/
//        std.close();

        /** Subjects */

        SubjectsDaoImpl sbj = new SubjectsDaoImpl();

        Subject subject1 = new Subject();
        subject1.setId(6);
        subject1.setSubject("Lirycs");

        Subject subject2 = new Subject();
        subject2.setId(7);
        subject2.setSubject("new");

        /*Вывод по ID */
//        System.out.println(sbj.getById(3));

        /*Обновить данные*/
//        sbj.update(subject1);

        /*Удалить из БД*/
//        sbj.delete(7);

        /*Добавить данные*/
//        sbj.insert(subject2);

        /*Вывод списка sbj*/
//        System.out.println(sbj.getAll());

        /*Закрытие ps и connection*/
//        sbj.close();

        /** Marks */

        MarksDaoImpl mrk = new MarksDaoImpl();

        Mark mark1 = new Mark();
        mark1.setId(10);
        mark1.setMark(4);
        mark1.setStudentId(5);
        mark1.setSubjectId(4);

        Mark mark2 = new Mark();
        mark2.setId(20);
        mark2.setMark(4);
        mark2.setStudentId(1);
        mark2.setSubjectId(4);

        /*Вывод по ID */
//        System.out.println(mrk.getById(2));

        /*Обновить данные*/
//        mrk.update(mark1);

        /*Удалить из БД*/
//        mrk.delete(20);

        /*Добавить данные*/
//        mrk.insert(mark2);

        /*Вывод списка*/
//        System.out.println(mrk.getAll());

        /*Получить все предметы одного студента вместе с их оценками*/
//        System.out.println(mrk.getSubjectsAndMarksOfStudent(2));

        /*Закрытие ps и connection*/
//        mrk.close();
    }
}

