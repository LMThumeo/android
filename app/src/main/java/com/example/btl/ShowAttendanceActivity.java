package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.btl.adapter.StudentRecycleViewAdapter;
import com.example.btl.model.AttendedStudent;
import com.example.btl.model.StudyClass;

import java.util.List;

public class ShowAttendanceActivity extends AppCompatActivity {

    private TextView tvSubject, tvDate, tvGroup;
    private RecyclerView recyclerView;
    private StudentRecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_attendance);

        tvSubject = findViewById(R.id.tvSubject);
        tvDate = findViewById(R.id.tvDate);
        tvGroup = findViewById(R.id.tvGroup);
        recyclerView = findViewById(R.id.recycleViewDetail);

        Intent intent = getIntent();
        List<AttendedStudent> attendedStudents = (List<AttendedStudent>) intent.getSerializableExtra("attendedStudents");
        StudyClass st =(StudyClass) intent.getSerializableExtra("studyclass");
        String day = intent.getStringExtra("day");

        tvSubject.setText(st.getSubject());
        tvGroup.setText("nhóm: "+st.getGroup());
        tvDate.setText(day);
        adapter = new StudentRecycleViewAdapter();
        adapter.setList(attendedStudents);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

    }
}