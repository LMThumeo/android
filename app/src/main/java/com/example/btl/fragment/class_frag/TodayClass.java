package com.example.btl.fragment.class_frag;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class TodayClass extends Fragment implements RecycleViewAdapter.ItemListener {

    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;
    private AssetDB db;
    private Student student;

    public TodayClass(Student student) {
        this.student = student;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.today_class_frag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleViewToday);
        adapter = new RecycleViewAdapter();
        db = new AssetDB(getContext());
        adapter.setItemListener(this);

        List<StudyClass> studyClasses = db.getTodayClassByStudent(student.getId());
        adapter.setList(studyClasses);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void onItemClick(View view, int pos) {
        StudyClass st = adapter.getItem(pos);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curDateTime = format.format(Calendar.getInstance().getTime());
        String curDate = curDateTime.substring(0,10);
        String curTime = curDateTime.substring(11);

        if (curTime.compareTo(st.getTimeStart())<0 || curTime.compareTo(st.getTimeEnd())>0 ) {
            alert("Now is not the time to attend!");
        }
        else {
            if (db.isAttended(student.getId(), st.getId())) {
                alert("You attended this class today!");
            }
            else {
                db.attend(student.getId(), st.getId());
                alert("You attended successfully!");
            }
        }
    }

    private void alert (String mess) {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle("Notification")
                .setMessage(mess)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
        alertDialog.show();
    }
}
