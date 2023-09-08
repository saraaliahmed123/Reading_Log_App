package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    myDbAdapter helper;
    EditText title;
    EditText readone;
    EditText readtwo;
    EditText date;
    EditText comments;
    EditText parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        helper = new myDbAdapter(this);
        title = findViewById(R.id.titleInput);
        readone = findViewById(R.id.read1Input);
        readtwo = findViewById(R.id.read2Input);
        date = findViewById(R.id.dateReadInput);
        comments = findViewById(R.id.commentsInput);
        parent = findViewById(R.id.parentInput);




        Button home = (Button) findViewById(R.id.homeButton1);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(AddActivity.this, MainActivity.class);
                startActivity(home);
            }
        });

        Button search = (Button) findViewById(R.id.searchButton1);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent search = new Intent(AddActivity.this, SearchActivity.class);
                startActivity(search);
            }
        });

        Button done = (Button) findViewById(R.id.done);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addRecord(v);

            }
        });



    }

    public void addRecord(View v) {
        String booktitle = title.getText().toString();
        String[] read = {readone.getText().toString(), readtwo.getText().toString()};
        String dateRead = date.getText().toString();
        String comment = comments.getText().toString();
        String parents = parent.getText().toString();


        if ((booktitle.isEmpty() || (dateRead.isEmpty()) || (comment.isEmpty()) || parents.isEmpty())) {
            Message.message(getApplicationContext(), "Missing inputs");
        }
        else {
            long id = helper.insertData(booktitle, read, dateRead, comment, parents);
            if (id<=0)
            {
                Message.message(getApplicationContext(), "Unsuccessful");
            }
            else {
                Message.message(getApplicationContext(), "Successful");
            }
        }

        Intent search = new Intent(AddActivity.this, MainActivity.class);
        startActivity(search);
    }
}