package com.example.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.util.Patterns;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TableActivity extends AppCompatActivity {
    FloatingActionButton fabBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_table);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.table), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        populateTable();
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rollBack = new Intent(TableActivity.this , SenderActivity.class);
                startActivity(rollBack);
            }
        });
    }

    private void populateTable(){
        Intent data = getIntent();

        //getting sender's data
        String sender_name = data.getStringExtra("sender_name"), sender_contact = data.getStringExtra("sender_contact"),
                sender_country = data.getStringExtra("sender_country"), sender_address = data.getStringExtra("sender_address");

        //getting receivers data
        String receiver_name = data.getStringExtra("receiver_name"), receiver_contact = data.getStringExtra("receiver_contact"),
                receiver_country = data.getStringExtra("receiver_country"), receiver_address = data.getStringExtra("receiver_address");

        TableLayout table = findViewById(R.id.myTable);

        //sender's row
        TableRow sRow = new TableRow(this);
        addCell(sRow , sender_name);
        addCell(sRow , sender_country);
        addCell(sRow , sender_address);
        addCell(sRow , sender_contact);
        table.addView(sRow);

        //transaction icon
        TableRow iRow = new TableRow(this);
        ImageView icon = new ImageView(this);
        icon.setImageResource(R.drawable.transaction);
        TableRow.LayoutParams params = new TableRow.LayoutParams(50, 50);
        icon.setLayoutParams(params);
        iRow.addView(icon);
        table.addView(iRow);

        //receiver's row
        TableRow rRow = new TableRow(this);
        addCell(rRow , receiver_name);
        addCell(rRow , receiver_country);
        addCell(rRow , receiver_address);
        addCell(rRow , receiver_contact);
        table.addView(rRow);

        Toast.makeText(TableActivity.this, "Data Added Successfully!", Toast.LENGTH_SHORT).show();
    }

    private void addCell(TableRow row , String data){
        TextView textView = new TextView(this);
        textView.setText(data);
        textView.setPadding(16, 16, 16, 16);
        textView.setGravity(Gravity.CENTER);
        row.addView(textView);
    }

    private void init(){
        fabBtn = findViewById(R.id.fabAdd); // Correct
    }
}