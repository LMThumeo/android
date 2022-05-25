package com.example.btl.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.btl.R;
import com.example.btl.model.Student;
import com.example.btl.model.Teacher;

public class TeacherInfo extends Fragment {

    private TextView tvCode, tvMail, tvSeniority, tvDepart, tvName;
    private Teacher teacher;

    public TeacherInfo(Teacher teacher) {
        this.teacher = teacher;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.teacher_info_frag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvCode = view.findViewById(R.id.tvMaGV);
        tvMail = view.findViewById(R.id.tvMail);
        tvSeniority = view.findViewById(R.id.tvSeniority);
        tvDepart = view.findViewById(R.id.tvDepartment);
        tvName = view.findViewById(R.id.tvName);

        tvCode.setText(teacher.getUsername());
        tvMail.setText(teacher.getMail());
        tvSeniority.setText(teacher.getSeniority());
        tvDepart.setText(teacher.getDepartment());
        tvName.setText(teacher.getName());
        System.out.println("teacherinfo");

    }
}
