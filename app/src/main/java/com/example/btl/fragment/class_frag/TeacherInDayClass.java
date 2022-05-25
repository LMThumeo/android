package com.example.btl.fragment.class_frag;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.ShowAttendanceActivity;
import com.example.btl.adapter.RecycleViewAdapter;
import com.example.btl.adapter.StudentRecycleViewAdapter;
import com.example.btl.dao.AssetDB;
import com.example.btl.model.AttendedStudent;
import com.example.btl.model.Student;
import com.example.btl.model.StudyClass;
import com.example.btl.model.Teacher;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TeacherInDayClass extends Fragment implements RecycleViewAdapter.ItemListener {

    private Teacher teacher;
    private CalendarView calendarView;
    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;
    private AssetDB db;
    String day, date;

    public TeacherInDayClass(Teacher teacher) {
        this.teacher = teacher;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.teacher_inday_class_frag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        calendarView = view.findViewById(R.id.calendar);
        recyclerView = view.findViewById(R.id.recycleViewInDay);
        adapter = new RecycleViewAdapter();
        adapter.setItemListener(this);
        db = new AssetDB(getContext());

        recyclerViewInit();

        System.out.println("teacherinday");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int y, int m, int d) {
                day = LocalDate.of(y,m+1,d).getDayOfWeek().toString();
                date = LocalDate.of(y,m+1,d).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                System.out.println(y+" "+(m+1)+" "+d+" "+day);

                List<StudyClass> inDayClasses = db.getClassByTeacherAndDay(teacher.getId(), day);
                adapter.setList(inDayClasses);
                LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(manager);
            }
        });
    }

    private void recyclerViewInit() {
        List<StudyClass> classes = db.getClassByTeacher(teacher.getId());
        adapter.setList(classes);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
    }


    @Override
    public void onItemClick(View view, int pos) {
        StudyClass st = adapter.getItem(pos);
        List<AttendedStudent> attendedStudents = db.getAttendedStudent(st.getId(), date );
        Intent intent = new Intent(getContext(), ShowAttendanceActivity.class);
        intent.putExtra("attendedStudents", (Serializable) attendedStudents);
        intent.putExtra("studyclass", st);
        intent.putExtra("day", date);
        startActivity(intent);
    }
}
