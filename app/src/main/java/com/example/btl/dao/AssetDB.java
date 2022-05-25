package com.example.btl.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.btl.model.AttendedStudent;
import com.example.btl.model.Student;
import com.example.btl.model.StudentClass;
import com.example.btl.model.StudyClass;
import com.example.btl.model.Teacher;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AssetDB extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "attendance.db";
    private static final int DATABASE_VERSION = 1;

    public AssetDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Student loginStudent(String username, String password) {
        String whereClause = "username = ? and password = ?";
        String[] whereArgs = {username, password};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("student", null, whereClause, whereArgs, null, null, null);
        while (rs!=null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String user = rs.getString(1);
            String pass = rs.getString(2);
            String name = rs.getString(3);
            String phone= rs.getString(4);
            String mail= rs.getString(5);
            System.out.println(user);
            return new Student(id, user, pass, name, phone, mail);
        }
        return null;
    }

    public Teacher loginTeacher(String username, String password) {
        String whereClause = "username = ? and password = ?";
        String[] whereArgs = {username, password};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("teacher", null, whereClause, whereArgs, null, null, null);
        while (rs!=null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String user = rs.getString(1);
            String pass = rs.getString(2);
            String name = rs.getString(3);
            String mail= rs.getString(4);
            String seniority= rs.getString(5);
            String department= rs.getString(6);
            System.out.println(user);
            return new Teacher(id, user, pass, name, mail, seniority, department);
        }
        System.out.println("teacher null");
        return null;
    }

    public List<StudentClass> getStudentClassByStudent(int studentId) {
        List<StudentClass> classes = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String whereClause = "student_id = ?";
        String[] whereArgs = {Integer.toString(studentId)};
        Cursor rs = st.query("studentclass", null, whereClause, whereArgs, null, null, null);
        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            int classId = rs.getInt(2);
            classes.add(new StudentClass(id, studentId, classId));
        }
        return classes;
    }

    public List<StudyClass> getClassByStudent(int studentId) {
        List<StudyClass> list = new ArrayList<>();
        List<StudentClass> classIds = getStudentClassByStudent(studentId);
        SQLiteDatabase sql = getReadableDatabase();

        for (StudentClass st : classIds) {
            int i = st.getClassId();
            System.out.println("classId: " + i);
            String whereClause2 = "id = ?";
            String[] whereArgs2 = {Integer.toString(i)};
            Cursor r = sql.query("studyclass", null, whereClause2, whereArgs2, null, null, null);
            while (r != null && r.moveToNext()) {
                int id = r.getInt(0);
                String subject = r.getString(1);
                String group = Integer.toString(r.getInt(2));
                String timeStart = r.getString(3);
                String timeEnd = r.getString(4);
                String day = r.getString(5);
                int teacherId = r.getInt(6);
                String room = r.getString(7);
                list.add(new StudyClass(id, teacherId, subject, group, room, day, timeStart, timeEnd));
                System.out.println("day: " + day);
            }
        }
        return list;
    }

    public List<StudyClass> getClassByStudentAndDay(int studentId, String day) {
        List<StudyClass> classes = getClassByStudent(studentId);
        List<StudyClass> todayClasses = new ArrayList<>();
//        String day = LocalDate.now().getDayOfWeek().toString();
//        System.out.println(day);
        for (StudyClass st : classes) {
            System.out.println(st.getSubject()+" "+st.getDay());
            if (st.getDay().equalsIgnoreCase(day)) {
                todayClasses.add(st);
            }
        }
        return todayClasses;
    }

    public List<StudyClass> getTodayClassByStudent(int studentId) {
        String day = LocalDate.now().getDayOfWeek().toString();
        return getClassByStudentAndDay(studentId, day);
    }

    public int findStudentClass(int studentId, int classId) {
        SQLiteDatabase sql = getReadableDatabase();
        String whereClause = "student_id = ? and class_id = ?";
        String[] whereArgs = {Integer.toString(studentId), Integer.toString(classId)};
        Cursor r = sql.query("studentclass", null, whereClause, whereArgs, null, null, null);
        while (r != null && r.moveToNext()) {
            return r.getInt(0);
        }
        return 0;
    }

    public boolean isAttended(int studentId, int classId) {
        int studentClassId = findStudentClass(studentId, classId);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curDateTime = format.format(Calendar.getInstance().getTime());
        String curDate = curDateTime.substring(0,10);

        SQLiteDatabase sql = getReadableDatabase();
        String whereClause = "student_class_id = ? and date = ?";
        String[] whereArgs = {Integer.toString(studentClassId), curDate};
        Cursor r = sql.query("attendance", null, whereClause, whereArgs, null, null, null);
        while (r != null && r.moveToNext()) {
            return true;
        }
        return false;
    }

    public long attend(int studentId, int classId) {
        int studentClassId = findStudentClass(studentId, classId);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curDateTime = format.format(Calendar.getInstance().getTime());
        String curDate = curDateTime.substring(0,10);
        String curTime = curDateTime.substring(11);

        ContentValues values = new ContentValues();
        values.put("student_class_id", studentClassId);
        values.put("date", curDate);
        values.put("time", curTime);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("attendance", null, values);
    }

    public List<StudyClass> getClassByTeacher(int teacherId) {
        List<StudyClass> list = new ArrayList<>();
        SQLiteDatabase sql = getReadableDatabase();

        String whereClause = "teacher_id = ?";
        String[] whereArgs = {Integer.toString(teacherId)};
        Cursor r = sql.query("studyclass", null, whereClause, whereArgs, null, null, null);
        while (r != null && r.moveToNext()) {
            int id = r.getInt(0);
            String subject = r.getString(1);
            String group = Integer.toString(r.getInt(2));
            String timeStart = r.getString(3);
            String timeEnd = r.getString(4);
            String day = r.getString(5);
//            int teacherId = r.getInt(6);
            String room = r.getString(7);
            list.add(new StudyClass(id, teacherId, subject, group, room, day, timeStart, timeEnd));
            System.out.println("id: "+id+" day: " + day);
        }
        return list;
    }

    public List<StudyClass> getClassByTeacherAndDay(int teacherId, String day) {
        List<StudyClass> classes = getClassByTeacher(teacherId);
        List<StudyClass> todayClasses = new ArrayList<>();
        for (StudyClass st : classes) {
            System.out.println(st.getSubject()+" "+st.getDay());
            if (st.getDay().equalsIgnoreCase(day)) {
                todayClasses.add(st);
            }
        }
        return todayClasses;
    }

    public List<StudyClass> getTodayClassByTeacher(int teacherId) {
        String day = LocalDate.now().getDayOfWeek().toString();
        return getClassByTeacherAndDay(teacherId, day);
    }

    public List<StudentClass> getStudentClassByClass(int classId){
        SQLiteDatabase sql = getReadableDatabase();
        List<StudentClass> studentClasses = new ArrayList<>();
        String whereClause = "class_id = ?";
        String[] whereArgs = {Integer.toString(classId)};
        Cursor r = sql.query("studentclass", null, whereClause, whereArgs, null, null, null);
        while (r != null && r.moveToNext()) {
            int id = r.getInt(0);
            int studentId = r.getInt(1);
            studentClasses.add(new StudentClass(id, studentId, classId));
            System.out.println("studentclass: "+id);
        }
        return studentClasses;
    }

    public Student getStudentById(int studentId) {
        SQLiteDatabase sql = getReadableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(studentId)};
        Cursor r = sql.query("student", null, whereClause, whereArgs, null, null, null);
        while (r != null && r.moveToNext()) {
            int id = r.getInt(0);
            String username = r.getString(1);
            String password = r.getString(2);
            String name = r.getString(3);
            String phone = r.getString(4);
            String mail = r.getString(5);
            return new Student(id, username, password, name, phone, mail);
        }
        return  null;
    }


    public List<AttendedStudent> getAttendedStudent(int classId, String date) {
        List<StudentClass> studentClasses = getStudentClassByClass(classId);
        List<AttendedStudent> attendedStudents = new ArrayList<>();
        SQLiteDatabase sql = getReadableDatabase();

        for(StudentClass st: studentClasses) {
            String whereClause = "student_class_id = ? and date = ?";
            String[] whereArgs = {Integer.toString(st.getId()), date};
            Cursor r = sql.query("attendance", null, whereClause, whereArgs, null, null, null);
            while (r != null && r.moveToNext()) {
                String time = r.getString(3);
                Student student = getStudentById(st.getStudentId());
                attendedStudents.add(new AttendedStudent(student, date, time));
            }
        }
        return attendedStudents;
    }
}
