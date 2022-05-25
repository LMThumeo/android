package com.example.btl.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.btl.R;
import com.example.btl.adapter.ClassViewPagerAdapter;
import com.example.btl.adapter.TeacherClassViewPagerAdapter;
import com.example.btl.adapter.TeacherViewPagerAdapter;
import com.example.btl.model.Student;
import com.example.btl.model.Teacher;
import com.google.android.material.tabs.TabLayout;

public class TeacherClass extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Teacher teacher;

    public TeacherClass(Teacher teacher) {
        this.teacher = teacher;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.teacher_class_frag, container, false);
        tabLayout = view.findViewById(R.id.tabLayout2);
        viewPager = view.findViewById(R.id.viewPager2);
        TeacherClassViewPagerAdapter adapter = new TeacherClassViewPagerAdapter(getChildFragmentManager());
        adapter.setTeacher(teacher);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        System.out.println("teacherclass");
        return view;
    }
}
