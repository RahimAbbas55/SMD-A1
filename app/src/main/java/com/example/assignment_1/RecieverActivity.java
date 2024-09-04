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

public class RecieverActivity extends AppCompatActivity {

    private EditText email , name , contact , country , address;
    private Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_receiver);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.receiver), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String receiver_email = email.getText().toString().trim(), receiver_name = name.getText().toString().trim(),
                        receiver_contact = contact.getText().toString().trim(), receiver_country = country.getText().toString().trim(),
                        receiver_address = address.getText().toString().trim();
                //checks for data validation
                boolean emailCheck = validateEmail(receiver_email), contactCheck = validateContact(receiver_contact),
                        nameCheck = validateName(receiver_name);

                if (emailCheck && contactCheck && nameCheck && !receiver_country.isEmpty() && !receiver_address.isEmpty()){
                    Intent receivedData = getIntent() , forwardData = new Intent(RecieverActivity.this , TableActivity.class);
                    String sender_name = receivedData.getStringExtra("sender_name") ,
                            sender_email = receivedData.getStringExtra("sender_email") ,
                            sender_contact = receivedData.getStringExtra("sender_contact") ,
                            sender_country = receivedData.getStringExtra("sender_country"),
                            sender_address = receivedData.getStringExtra("sender_address");

                    //forwarding sender data to the table screen
                    forwardData.putExtra("sender_name" , sender_name);
                    forwardData.putExtra("sender_email" , sender_email);
                    forwardData.putExtra("sender_contact" , sender_contact);
                    forwardData.putExtra("sender_country" , sender_country);
                    forwardData.putExtra("sender_address" , sender_address);

                    //forwarding receiver data to the table screen
                    forwardData.putExtra("receiver_name" , receiver_name);
                    forwardData.putExtra("receiver_email" , receiver_email);
                    forwardData.putExtra("receiver_contact" , receiver_contact);
                    forwardData.putExtra("receiver_country" , receiver_country);
                    forwardData.putExtra("receiver_address" , receiver_address);
                    startActivity(forwardData);
                    Toast.makeText(RecieverActivity.this, "Data Sent Successfully!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(RecieverActivity.this, "Please enter correct details!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void init(){
        email = findViewById(R.id.rEmail);
        name = findViewById(R.id.rName);
        contact = findViewById(R.id.rContact);
        country = findViewById(R.id.rCountry);
        address = findViewById(R.id.rAddress);
        nextBtn = findViewById(R.id.receiverbtn);
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