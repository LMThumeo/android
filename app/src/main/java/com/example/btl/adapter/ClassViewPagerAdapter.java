package com.example.btl.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.btl.fragment.class_frag.InDayClass;
import com.example.btl.fragment.class_frag.TodayClass;
import com.example.btl.model.Student;

public class ClassViewPagerAdapter extends FragmentStatePagerAdapter {

    private Student student;

    public void setStudent(Student student) {
        this.student = student;
    }

    public ClassViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new TodayClass(student);
            case 1 : return new InDayClass(student);
//            case 2 : return new FragRobusta();
            default: return new TodayClass(student);
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
//            case 2: return "ROBUSTA";
            default: return "Today";
        }
    }
}