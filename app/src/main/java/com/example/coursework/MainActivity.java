package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.content.Intent;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    myDbAdapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        helper = new myDbAdapter(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String[]> list = helper.getData();
//        if (list.size() > 0) {
            for (String[] item : list) {
                records(randomColours(), item);
            }
//        }

        System.out.println(list.size());

        Button add = (Button) findViewById(R.id.addButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(MainActivity.this, AddActivity.class);
                startActivity(add);
            }
        });

        Button search = (Button) findViewById(R.id.searchButton);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent search = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(search);
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

        LinearLayout pressanddate = new LinearLayout(this);
        pressanddate.setOrientation(LinearLayout.HORIZONTAL);

        TextView date = new TextView(this);
        date.setText(item[3]);
        date.setTextColor(Color.WHITE);
        date.setTextSize(20);
        date.setPadding(300, 0, 0, 0);
        pressanddate.addView(date);

        TextView press = new TextView(this);
        press.setText("Press me!");
        press.setTextColor(Color.WHITE);
        press.setTextSize(18);
        press.setPadding(130, 0, 0, 0);
        pressanddate.addView(press);

        record.addView(pressanddate);

        LinearLayout bookTitleContainer = new LinearLayout(this);
        bookTitleContainer.setOrientation(LinearLayout.HORIZONTAL);
        bookTitleContainer.setPadding(0, 30, 0, 0);

        TextView bookTitle = new TextView(this);
        bookTitle.setText("Book Title:");
        bookTitle.setTextColor(Color.WHITE);
        bookTitle.setTextSize(20);
        bookTitle.setPadding(150, 0, 90, 0);
        bookTitleContainer.addView(bookTitle);


        TextView title = new TextView(this);
        title.setText(item[1]);
        title.setTextColor(Color.BLACK);
        title.setTextSize(18);
        title.setPadding(20, 20, 20, 20);
        title.setBackgroundColor(Color.parseColor("#E7EDF3"));
        bookTitleContainer.addView(title);

        record.addView(bookTitleContainer);

        LinearLayout moreContainer = new LinearLayout(this);
        moreContainer.setOrientation(LinearLayout.HORIZONTAL);
        moreContainer.setPadding(0, 30, 0, 0);

//        TextView rating = new TextView(this);
//        rating.setText("Rating:");
//        rating.setTextColor(Color.WHITE);
//        rating.setTextSize(20);
//        rating.setPadding(90, 0, 50, 0);
//        moreContainer.addView(rating);
//
//        TextView ratingItem = new TextView(this);
//        ratingItem.setText("3");
//        ratingItem.setTextColor(Color.BLACK);
//        ratingItem.setTextSize(20);
//        ratingItem.setPadding(20, 20, 20, 20);
//        ratingItem.setBackgroundColor(Color.parseColor("#E7EDF3"));
//        moreContainer.addView(ratingItem);

        TextView pagesRead = new TextView(this);
        pagesRead.setText("Pages Read:");
        pagesRead.setTextColor(Color.WHITE);
        pagesRead.setTextSize(20);
        pagesRead.setPadding(150, 0, 50, 0);
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

        record.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                edit.putExtra("Item", item);
                Bundle list = new Bundle();
                list.putStringArray("I", item);
                Intent edit = new Intent(MainActivity.this, ViewActivity.class);
                edit.putExtras(list);
                startActivity(edit);

            }});
    }
}