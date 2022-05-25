package com.example.btl.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.btl.fragment.class_frag.InDayClass;
import com.example.btl.fragment.class_frag.TeacherInDayClass;
import com.example.btl.fragment.class_frag.TeacherTodayClass;
import com.example.btl.fragment.class_frag.TodayClass;
import com.example.btl.model.Student;
import com.example.btl.model.Teacher;

public class TeacherClassViewPagerAdapter extends FragmentStatePagerAdapter {

    private Teacher teacher;

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public TeacherClassViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new TeacherTodayClass(teacher);
            case 1 : return new TeacherInDayClass(teacher);
            default: return new TeacherTodayClass(teacher);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return  "Today";
            case 1: return "InDay";
            default: return "Today";
        }
    }
}