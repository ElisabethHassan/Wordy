package com.example.wordy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class CreateWordActivity extends AppCompatActivity {
    Button back, save, clear;
    String wordStr = "world";
    EditText word;
    TextView heading;
    boolean isAlphabetical = true;
    boolean exists = false;
    FirebaseDatabase db  = FirebaseDatabase.getInstance();
    DatabaseReference reference = db.getReference("words");
    ArrayList<String> wordList = new ArrayList<>();
    Random rand = new Random();


    View.OnClickListener clearListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            clearDatabase();
        }
    };

    View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CreateWordActivity.this, MainActivity.class );
            startActivity(intent);
        }
    };

    View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String wordStr = word.getText().toString();
            if(wordStr.length() == 5){

                for (int i = 0; i < word.getText().toString().length(); i++){
                    char c = word.getText().toString().charAt(i);
                    if (!Character.isLetter(c)) {
                        isAlphabetical = false;
                        break;
                    }
                }

                if(isAlphabetical == true){
                    //handles duplicates
                    if (!wordStr.isEmpty()) {
                        reference.orderByValue().equalTo(wordStr).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(!snapshot.exists()){
                                    String key = reference.push().getKey();
                                    reference.child(key).setValue(wordStr);
                                    Toast.makeText(getApplicationContext(), "Word added successful", Toast.LENGTH_LONG).show();
                                    word.setText("");
                                } else Toast.makeText(getApplicationContext(), "This word already exists", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                }
            } else {
                heading.setTextColor((int) Color.parseColor("#ee70ff"));
                Toast.makeText(getApplicationContext(), "Word must be 5 letters", Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_word);

        save = findViewById(R.id.save_button_id);
        back = findViewById(R.id.backButton_id);
        clear = findViewById(R.id.clear_button);
        word = findViewById(R.id.wordEt_id);
        heading = findViewById(R.id.heading_id);

        back.setOnClickListener(backListener);
        save.setOnClickListener(saveListener);
        clear.setOnClickListener(clearListener);

    }

    public void clearDatabase(){
        reference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Database cleared successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to clear database", Toast.LENGTH_SHORT).show();
            }
        });
    }

}