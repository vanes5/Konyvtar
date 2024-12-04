package com.example.konyvtar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailsActivity extends AppCompatActivity {
    private TextView bookTitle;
    private TextView bookAuthor;
    private TextView bookpages;
    private TextView year;
    private Button backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public void init(){
        bookTitle = findViewById(R.id.bookTitleTextView);
        bookAuthor = findViewById(R.id.bookAuthorTextView);
        bookpages = findViewById(R.id.bookPagesTextView);
        year = findViewById(R.id.bookYearTextView);
        backbutton = findViewById(R.id.backButton);

        bookTitle.setText(getIntent().getStringExtra("bookTitle"));
        bookAuthor.setText(getIntent().getStringExtra("bookAuthor"));
        bookpages.setText(getIntent().getStringExtra("bookPages"));
        year.setText(getIntent().getStringExtra("bookYear"));

    }
}