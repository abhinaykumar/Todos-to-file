package com.example.abhinay.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.w3c.dom.Text;

import static android.R.attr.data;

public class Main2Activity extends AppCompatActivity {

    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String item = getIntent().getStringExtra("item");
        position = getIntent().getIntExtra("position", 0);
        EditText itemText = (EditText) findViewById(R.id.etItem);
        itemText.setText(item);
        // Set cursor at the end of text
        itemText.setSelection(itemText.getText().length());
        // set focus
        itemText.requestFocus();

    }

    public void onSubmit(View v) {
        EditText itemText = (EditText) findViewById(R.id.etItem);
        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("item", itemText.getText().toString());
        data.putExtra("position", position); // ints work too
        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }
}
