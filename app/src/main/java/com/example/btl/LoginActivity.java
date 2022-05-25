package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.btl.dao.AssetDB;
import com.example.btl.model.Student;
import com.example.btl.model.Teacher;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText eUser, ePass;
    private Spinner spRole;
    private Button btLogin;
    private AssetDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        eUser = findViewById(R.id.eUser);
        ePass = findViewById(R.id.ePass);
        spRole = findViewById(R.id.spRole);
        btLogin = findViewById(R.id.btLogin);
        db = new AssetDB(this);
        btLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btLogin:
                String user = eUser.getText().toString();
                String pass = ePass.getText().toString();
                String role = spRole.getSelectedItem().toString();
                if (role.equalsIgnoreCase("teacher")) {
                    Teacher teacher = db.loginTeacher(user, pass);
                    if (teacher == null ) {
                        Toast.makeText(this, "login failed", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent intent = new Intent(this, TeacherMainActivity.class);
                        intent.putExtra("teacher", teacher);
                        startActivity(intent);
                    }
                }
                else {
                    Student student = db.loginStudent(user, pass);
                    if (student == null ) {
                        Toast.makeText(this, "login failed", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent intent = new Intent(this, MainActivity.class);
                        intent.putExtra("student", student);
                        startActivity(intent);
                    }

                }
        }
    }
}