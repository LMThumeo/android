package com.example.btl.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.btl.fragment.CommonClass;
import com.example.btl.fragment.StudentInfo;
import com.example.btl.fragment.class_frag.TodayClass;
import com.example.btl.model.Student;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private Student student;

    public void setStudent(Student student) {
        this.student = student;
    }

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new CommonClass(student);
            case 1: return new StudentInfo(student);
            default: return new CommonClass(student);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
