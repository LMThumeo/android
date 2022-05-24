package com.example.btl.fragment;

import android.content.Intent;
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

public class StudentInfo extends Fragment {

    private TextView tvCode, tvMail, tvPhone, tvName;
    private Student student;

    public StudentInfo(Student student) {
        this.student = student;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.student_info_frag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvCode = view.findViewById(R.id.tvMaSV);
        tvMail = view.findViewById(R.id.tvMail);
        tvPhone = view.findViewById(R.id.tvPhone);
        tvName = view.findViewById(R.id.tvName);

        tvCode.setText(student.getUsername());
        tvMail.setText(student.getMail());
        tvPhone.setText(student.getPhone());
        tvName.setText(student.getName());

    }
}
