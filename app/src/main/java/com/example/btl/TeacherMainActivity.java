package com.example.btl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.btl.adapter.TeacherViewPagerAdapter;
import com.example.btl.adapter.ViewPagerAdapter;
import com.example.btl.model.Student;
import com.example.btl.model.Teacher;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TeacherMainActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);

        Intent intent = getIntent();
        Teacher teacher = (Teacher) intent.getSerializableExtra("teacher");

        navigationView = findViewById(R.id.navigation2);
        viewPager = findViewById(R.id.viewPager2);
        fab = findViewById(R.id.fab);
        TeacherViewPagerAdapter adapter = new TeacherViewPagerAdapter(getSupportFragmentManager());
        adapter.setTeacher(teacher);
        viewPager.setAdapter(adapter);

        System.out.println("main");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exitIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(exitIntent);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0: navigationView.getMenu().findItem(R.id.mToday).setChecked(true);
                        break;
                    case 1: navigationView.getMenu().findItem(R.id.mInfo).setChecked(true);
                        break;
//                    case 2: navigationView.getMenu().findItem(R.id.mSearch).setChecked(true);
//                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mToday: viewPager.setCurrentItem(0);
                        break;
                    case R.id.mInfo: viewPager.setCurrentItem(1);
                        break;
//                    case R.id.mSearch: viewPager.setCurrentItem(2);
//                        break;
                }
                return true;
            }
        });

    }

}
