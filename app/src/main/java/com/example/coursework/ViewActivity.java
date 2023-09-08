package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ViewActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_view);

        Bundle b = this.getIntent().getExtras();
        String[] array =b.getStringArray("I");

        helper = new myDbAdapter(this);

        title = findViewById(R.id.titleInput);
        readone = findViewById(R.id.read1Input);
        readtwo = findViewById(R.id.read2Input);
        date = findViewById(R.id.dateReadInput);
        comments = findViewById(R.id.commentsInput);
        parent = findViewById(R.id.parentInput);

        title.setText(array[1]);
        readone.setText(array[2].split("-")[0]);
        readtwo.setText(array[2].split("-")[1]);
        date.setText(array[3]);
        comments.setText(array[4]);
        parent.setText(array[5]);

        Button delete = (Button) findViewById(R.id.delete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRecord(v, array);

            }
        });

        Button edit = (Button) findViewById(R.id.edit);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editRecord(v, array);
            }
        });


        Button done = (Button) findViewById(R.id.done);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent done = new Intent(ViewActivity.this, MainActivity.class);
                startActivity(done);
            }
        });

        Button email = (Button) findViewById(R.id.email);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String mail = "mailto:default@example.com";
//                Uri mailUri = Uri.parse(mail);
                String subject = "Diary entry ";

                String body = "Reading Entry:  " + array[3] + "\n";
                body += "Title: " + array[1] + "\n";
                body += "Date: " + array[3] + "\n";
                body += "Pages " + array[2];
                body += "Child comments: " + array[4];
                body += "Parent comments: " + array[5];


//                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, mailUri);
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, body);

                if (emailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailIntent);
                }
                else {
                    Message.message(getApplicationContext(), "Error");
                }

            }
        });
    }


    public void email(View v, EditText email, String[] array){
       // String mail = email.getText().toString();
        String mail = "sara.ali.ahmed@hotmail.com";
        Uri mailUri = Uri.parse(mail);
        String subject = "Diary entry ";
        String body = (array[1]+"\n"+array[2]+"\n"+array[3]+"\n"+array[4]+"\n"+array[5]);
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, mailUri);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }
        else {
            Message.message(getApplicationContext(), "Error");
        }
    }

    public void deleteRecord(View v, String[] array){

        int id = helper.delete(array[0]);
        if (id<=0)
        {
            Message.message(getApplicationContext(), "Unsuccessful");
        }
        else {
            Message.message(getApplicationContext(), "Successful");
        }

        Intent done = new Intent(ViewActivity.this, MainActivity.class);
        startActivity(done);
    }

    public void editRecord(View v, String[] array){
        String booktitle = title.getText().toString();
        String[] read = {readone.getText().toString(), readtwo.getText().toString()};
        String dateRead = date.getText().toString();
        String comment = comments.getText().toString();
        String parents = parent.getText().toString();


        if ((booktitle.isEmpty() || (dateRead.isEmpty()) || (comment.isEmpty()) || parents.isEmpty())) {
            Message.message(getApplicationContext(), "Missing inputs");
        }
        else {
            long id = helper.updateName(array[0], booktitle, read, dateRead, comment, parents);
            if (id<=0)
            {
                Message.message(getApplicationContext(), "Unsuccessful");
            }
            else {
                Message.message(getApplicationContext(), "Successful");
            }
        }

        Intent done = new Intent(ViewActivity.this, MainActivity.class);
        startActivity(done);
    }
}