package com.example.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.util.Patterns;
import android.widget.Toast;

public class SenderActivity extends AppCompatActivity {

    private EditText email , name , contact , country , address;
    private Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sender);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.sender), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sender_email = email.getText().toString().trim(), sender_name = name.getText().toString().trim(),
                    sender_contact = contact.getText().toString().trim(), sender_country = country.getText().toString().trim(),
                    sender_address = address.getText().toString().trim();
                //checks for data validation
                boolean emailCheck = validateEmail(sender_email), contactCheck = validateContact(sender_contact),
                        nameCheck = validateName(sender_name);

                if (emailCheck && contactCheck && nameCheck && !sender_country.isEmpty() && !sender_address.isEmpty()){
                    //sending data to next screen
                    Intent data = new Intent(SenderActivity.this , RecieverActivity.class);
                    data.putExtra("sender_email" , sender_email);
                    data.putExtra("sender_name" , sender_name);
                    data.putExtra("sender_contact" , sender_contact);
                    data.putExtra("sender_country" , sender_country);
                    data.putExtra("sender_address" , sender_address);
                    startActivity(data);
                    Toast.makeText(SenderActivity.this, "Sender Data Received", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(SenderActivity.this, "Please enter correct details!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void init(){
        email = findViewById(R.id.sEmail);
        name = findViewById(R.id.sName);
        contact = findViewById(R.id.sContact);
        country = findViewById(R.id.sCountry);
        address = findViewById(R.id.sAddress);
        nextBtn = findViewById(R.id.senderbtn);
    }

    private boolean validateEmail(String email){
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean validateContact(String number){
        return number.length() == 11;
    }
    private boolean validateName(String name){
        return name != null && !name.trim().isEmpty() && name.matches("[a-zA-Z\\s]+");
    }

}