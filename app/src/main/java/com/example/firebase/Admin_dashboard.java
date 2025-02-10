package com.example.firebase;
//Admin Dashboard

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Admin_dashboard extends AppCompatActivity {
    ImageView add_stu ,add_emp,emp_det,stu_det,issue,leave;
    ImageView bars;
    private RelativeLayout popupLayout;
    Button admin,notice,logout,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        bars = findViewById(R.id.three_bars);
        popupLayout = findViewById(R.id.popupLayout);
        admin = findViewById(R.id.navigateToPage1Button);
        notice = findViewById(R.id.navigateToPage2Button);
        logout = findViewById(R.id.logout);
        back = findViewById(R.id.back);

        add_stu = findViewById(R.id.add_stu);
        add_emp = findViewById(R.id.add_emp);
        emp_det = findViewById(R.id.emp_det);
        stu_det = findViewById(R.id.stu_det);
        issue   = findViewById(R.id.comp);
        leave   = findViewById(R.id.leave);


        bars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupLayout.getVisibility() == View.VISIBLE) {
                    popupLayout.setVisibility(View.GONE); // Close the popup
                } else {
                    popupLayout.setVisibility(View.VISIBLE); // Open the popup
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupLayout.setVisibility(View.GONE);

            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NoticeBoard.class);
                startActivity(i);
                finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Start.class);
                startActivity(i);
                finish();
            }
        });

        //---------------------------------------------------------------------------------------------------

        add_stu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Add_student.class);
                startActivity(i);
                finish();
            }
        });

        add_emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Add_emp.class);
                startActivity(i);
                finish();
            }
        });

        emp_det.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Choice = "View_Employees";
                Intent i = new Intent(getApplicationContext(), View_Student.class);
                i.putExtra("Choice",Choice);
                startActivity(i);
                finish();
            }
        });

        stu_det.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Choice = "View_Student";
                Intent i = new Intent(getApplicationContext(), View_Student.class);
                i.putExtra("Choice",Choice);
                startActivity(i);
                finish();
            }
        });

        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Choice = "Leave";
                Intent i = new Intent(getApplicationContext(), View_Student.class);
                i.putExtra("Choice",Choice);
                startActivity(i);
                finish();
            }
        });

        issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Choice = "Issue";
                Intent i = new Intent(getApplicationContext(), View_Student.class);
                i.putExtra("Choice",Choice);
                startActivity(i);
                finish();
            }
        });
    }
}