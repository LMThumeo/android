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
import com.example.btl.model.Student;
import com.google.android.material.tabs.TabLayout;

public class CommonClass extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Student student;

    public CommonClass(Student student) {
        this.student = student;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.class_frag, container, false);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        ClassViewPagerAdapter adapter = new ClassViewPagerAdapter(getChildFragmentManager());
        adapter.setStudent(student);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
