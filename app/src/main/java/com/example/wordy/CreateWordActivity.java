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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class CreateWordActivity extends AppCompatActivity {
    Button back, save;
    String wordStr = "world";
    EditText word;
    TextView heading;
    boolean isAlphabetical = true;
    boolean exists = false;
    FirebaseDatabase db  = FirebaseDatabase.getInstance();
    DatabaseReference reference = db.getReference("words");
    ArrayList<String> wordList = new ArrayList<>();
    Random rand = new Random();
    String target = "world";



    View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CreateWordActivity.this, MainActivity.class );
//            intent.putExtra("word", randomWord(wordList));
//            intent.putExtra("word", wordList);
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
                    //TODO: MAKE SURE DUPLICATES AREN'T ADDED
                    if (!wordStr.isEmpty()) {
                        String key = reference.push().getKey();
                        reference.child(key).setValue(wordStr);
                        Toast.makeText(getApplicationContext(), "Word added successful", Toast.LENGTH_LONG).show();
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
        word = findViewById(R.id.wordEt_id);
        heading = findViewById(R.id.heading_id);

        back.setOnClickListener(backListener);
        save.setOnClickListener(saveListener);


//        reference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//                    String word = dataSnapshot.getValue(String.class);
//                    wordList.add(word);
//                }
//                target = randomWord(wordList);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }

    public String randomWord(ArrayList<String> list){
        int rand_num;
        if (list.size() != 0){
            rand_num = rand.nextInt(list.size());
            return list.get(rand_num).toUpperCase();
        }
        return wordStr;
    }

}