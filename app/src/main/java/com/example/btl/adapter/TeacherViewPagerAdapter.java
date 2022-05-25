package com.example.btl.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.btl.fragment.CommonClass;
import com.example.btl.fragment.StudentInfo;
import com.example.btl.fragment.TeacherClass;
import com.example.btl.fragment.TeacherInfo;
import com.example.btl.model.Student;
import com.example.btl.model.Teacher;

public class TeacherViewPagerAdapter extends FragmentStatePagerAdapter {

    private Teacher teacher;

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public TeacherViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new TeacherClass(teacher);
            case 1: return new TeacherInfo(teacher);
            default: return new TeacherClass(teacher);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
