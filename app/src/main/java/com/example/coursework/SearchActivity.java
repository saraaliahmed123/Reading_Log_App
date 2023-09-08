package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Space;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    myDbAdapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        helper = new myDbAdapter(this);

        ArrayList<String[]> list = helper.getData();

        Button search = (Button) findViewById(R.id.search);

        EditText input = (EditText) findViewById(R.id.input);
        
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout recordList = (LinearLayout) findViewById(R.id.records);
                recordList.removeAllViews();
                if (input.getText().toString().isEmpty())
                {
                    Message.message(getApplicationContext(), "No input");
                }
                else {
                    for (String[] item : list) {
                        if (item[3].contains(input.getText().toString())) {
                            records(randomColours(), item);
                        }
                    }
                }

            }
        });


            Button add = (Button) findViewById(R.id.addButton2);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent add = new Intent(SearchActivity.this, AddActivity.class);
                    startActivity(add);
                }
            });

        Button home = (Button) findViewById(R.id.homeButton2);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(home);
            }
        });
    }

    public String randomColours(){
        String[] colours = {"#BD4949", "#4985BD", "#CCCE4C", "#49BD7E"};
        int num = (int) (Math.random() * 4);
        return colours[num-1];
    }

    public void records(String colour, String[] item) {

        LinearLayout record = new LinearLayout(this);
        record.setOrientation(LinearLayout.VERTICAL);
        record.setPadding(0, 20, 0, 20);
        record.setBackgroundColor(Color.parseColor(colour));

        TextView date = new TextView(this);
        date.setText(item[3]);
        date.setTextColor(Color.WHITE);
        date.setTextSize(20);
        date.setPadding(300, 0, 0, 0);
        record.addView(date);

        LinearLayout bookTitleContainer = new LinearLayout(this);
        bookTitleContainer.setOrientation(LinearLayout.HORIZONTAL);

        TextView bookTitle = new TextView(this);
        bookTitle.setText("Book Title:");
        bookTitle.setTextColor(Color.WHITE);
        bookTitle.setTextSize(20);
        bookTitle.setPadding(150, 0, 100, 0);
        bookTitleContainer.addView(bookTitle);


        TextView title = new TextView(this);
        title.setText(item[1]);
        title.setTextColor(Color.BLACK);
        title.setTextSize(20);
        title.setPadding(20, 20, 20, 20);
        title.setBackgroundColor(Color.parseColor("#E7EDF3"));
        bookTitleContainer.addView(title);

        record.addView(bookTitleContainer);

        LinearLayout moreContainer = new LinearLayout(this);
        moreContainer.setOrientation(LinearLayout.HORIZONTAL);

        TextView rating = new TextView(this);
        rating.setText("Rating:");
        rating.setTextColor(Color.WHITE);
        rating.setTextSize(20);
        rating.setPadding(90, 0, 50, 0);
        moreContainer.addView(rating);

        TextView ratingItem = new TextView(this);
        ratingItem.setText("3");
        ratingItem.setTextColor(Color.BLACK);
        ratingItem.setTextSize(20);
        ratingItem.setPadding(20, 20, 20, 20);
        ratingItem.setBackgroundColor(Color.parseColor("#E7EDF3"));
        moreContainer.addView(ratingItem);

        TextView pagesRead = new TextView(this);
        pagesRead.setText("Pages Read:");
        pagesRead.setTextColor(Color.WHITE);
        pagesRead.setTextSize(20);
        pagesRead.setPadding(50, 0, 50, 0);
        moreContainer.addView(pagesRead);

        TextView pagesReadNum = new TextView(this);
        pagesReadNum.setText(item[2]);
        pagesReadNum.setTextColor(Color.BLACK);
        pagesReadNum.setTextSize(20);
        pagesReadNum.setPadding(20, 20, 20, 20);
        pagesReadNum.setBackgroundColor(Color.parseColor("#E7EDF3"));
        moreContainer.addView(pagesReadNum);

        record.addView(moreContainer);

        Space space = new Space(this);
        space.setBackgroundColor(Color.WHITE);
        space.setMinimumHeight(100);


        LinearLayout recordList = (LinearLayout) findViewById(R.id.records);
        recordList.addView(record);
        recordList.addView(space);
    }
}