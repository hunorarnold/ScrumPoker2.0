
package com.example.adminapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.sql.Time;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText txtRoomName, txtQuestion, QuestionID, Vote;
    Button btnDate, btnTime, btnLogout, btnCreateRoom, btnViewRoom;
    FirebaseAuth mFirebaseAuth;
    private Boolean license = true;
    private DatabaseReference databaseRoom;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtRoomName = findViewById(R.id.editRoomName);
        txtQuestion = findViewById(R.id.addQuestion);
        QuestionID = findViewById(R.id.addQuestionID);

        btnCreateRoom = findViewById(R.id.btnCreate);
        btnViewRoom = findViewById(R.id.btnView);
        btnDate = findViewById(R.id.btn_pickDate);
        btnTime = findViewById(R.id.btn_pickTime);
        btnLogout = findViewById(R.id.btnLogout);


        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDateButton();
            }
        });

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTimeButton();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Intent intentLogout = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentLogout);

            }
        });

        databaseRoom = FirebaseDatabase.getInstance().getReference("rooms");

        btnCreateRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addRoom();

            }
        });
    }

    private void handleTimeButton() {

        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                String timeString = hour + ":" + minute;
                btnTime.setText(timeString);

            }
        }, HOUR, MINUTE, true);

        timePickerDialog.show();

    }

    private void handleDateButton() {

        Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                String dateString = year + "." + month + "." + date;
                btnDate.setText(dateString);

            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();

    }

    private void addRoom() {

        String roomName = txtRoomName.getText().toString();
        String roomQuestion = txtQuestion.getText().toString();
        String questionID = QuestionID.getText().toString();

        if(!roomName.isEmpty()){

            String id = databaseRoom.push().getKey();

            Room room = new Room(id, roomName, roomQuestion, questionID);

            databaseRoom.child(id).setValue(room);
            Toast.makeText(this, "Room added", Toast.LENGTH_LONG).show();

        }
        else{

            Toast.makeText(this, "You should enter a name", Toast.LENGTH_LONG).show();

        }

        if(!roomQuestion.isEmpty()){


        }
        else{

            Toast.makeText(this, "You should enter a question", Toast.LENGTH_LONG).show();

        }

        if(!questionID.isEmpty()){


        }
        else{

            Toast.makeText(this, "You should enter a question ID", Toast.LENGTH_LONG).show();

        }

    }

}
