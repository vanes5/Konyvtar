package com.example.konyvtar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends BaseAdapter{
    private List<Book> books;
    private Context context;

    public BookAdapter(Context context, List<Book> books) {
        this.books = books;
        this.context = context;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int i) {
        return books.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.card, viewGroup,false);

        TextView title = view.findViewById(R.id.bookTitleTextView);
        TextView author = view.findViewById(R.id.bookAuthorTextView);
        TextView pages = view.findViewById(R.id.bookPagesTextView);
        //TextView year = view.findViewById(R.id.bookYearTextView);
        Button delete = view.findViewById(R.id.deleteButton);

        Book book = (Book)this.getItem(i);

        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        pages.setText(String.valueOf(book.getPages()));
        //year.setText(String.valueOf(book.getYear()));
        delete.setText("Törlés");
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                books.remove(i);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
