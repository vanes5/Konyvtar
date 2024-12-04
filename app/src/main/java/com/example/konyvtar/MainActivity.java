package com.example.konyvtar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText bookTitle;
    private EditText bookAuthor;
    private EditText bookPages;
    private Button addButton;
    private ListView bookListView;
    private ArrayList<Book> books;
    private BookAdapter bookAdapter;
    private boolean isAllChecked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAllChecked = CheckAll();
                if (isAllChecked){
                    books.add(new Book(bookTitle.getText().toString(), bookAuthor.getText().toString(),Integer.parseInt(bookPages.getText().toString())));
                    bookAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Sikeres felvétel", Toast.LENGTH_SHORT).show();
                    bookTitle.getText().clear();
                    bookPages.getText().clear();
                    bookAuthor.getText().clear();
                }


            }
        });

        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book clicked = books.get(i);
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("bookTitle", clicked.getTitle());
                intent.putExtra("bookAuthor", clicked.getAuthor());
                intent.putExtra("bookPages",String.valueOf(clicked.getPages()));
                intent.putExtra("bookYear", String.valueOf(clicked.getYear()));
                startActivity(intent);
                finish();
            }
        });


    }

    private boolean CheckAll(){
        if (bookTitle.length() == 0){
            bookTitle.setError("Kötelező kitölteni");
            return false;
        }
        if (bookAuthor.length() == 0){
            bookAuthor.setError("Kötelező kitölteni");
            return false;
        }
        if (bookPages.length() == 0){
            bookPages.setError("Kötelező kitölteni");
            return false;
        }
        if (Integer.parseInt(bookPages.getText().toString()) < 50){
            bookPages.setError("Az oldalszámnak nagyobbnak kell lennie, mint 50");
            return false;
        }
        return true;
    }

    public void init(){
        bookTitle = findViewById(R.id.bookTitleEditText);
        bookAuthor = findViewById(R.id.bookAuthorEditText);
        bookPages = findViewById(R.id.bookPagesEditText);
        addButton = findViewById(R.id.addButton);
        bookListView = findViewById(R.id.bookListView);
        books = new ArrayList<Book>();
        bookAdapter = new BookAdapter(MainActivity.this, books);
        bookListView.setAdapter(bookAdapter);
    }
}