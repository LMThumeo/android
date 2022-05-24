package com.example.btl.fragment.class_frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.adapter.RecycleViewAdapter;
import com.example.btl.dao.AssetDB;
import com.example.btl.model.Student;
import com.example.btl.model.StudyClass;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

public class InDayClass extends Fragment {

    private Student student;
    private CalendarView calendarView;
    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;
    private AssetDB db;

    public InDayClass(Student student) {
        this.student = student;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.inday_class_frag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        calendarView = view.findViewById(R.id.calendar);
        recyclerView = view.findViewById(R.id.recycleViewInDay);
        adapter = new RecycleViewAdapter();
        db = new AssetDB(getContext());

        recyclerViewInit();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int y, int m, int d) {
//                Calendar calendar = Calendar.getInstance();
//                calendar.set(y, m, d);
//                String day = DayOfWeek.of(calendar.get(Calendar.DAY_OF_WEEK)+1).toString();
                String day = LocalDate.of(y,m+1,d).getDayOfWeek().toString();

                System.out.println(y+" "+(m+1)+" "+d+" "+day);
                List<StudyClass> inDayClasses = db.getClassByStudentAndDay(student.getId(), day);
                adapter.setList(inDayClasses);
                LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(manager);
            }
        });
    }

    private void recyclerViewInit() {
        List<StudyClass> classes = db.getClassByStudent(student.getId());
        adapter.setList(classes);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
    }


}
