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
    EditText word;
    TextView heading;
    boolean isAlphabetical = true;
    boolean exists = false;
    FirebaseDatabase db;
    DatabaseReference reference;
    ArrayList<String> wordList = new ArrayList<>();
    Random rand = new Random();



    View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CreateWordActivity.this, MainActivity.class );
//            intent.putExtra("word", randomWord(wordList));
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
                    reference.setValue(wordStr);
                    Toast.makeText(getApplicationContext(), "Word added successful", Toast.LENGTH_LONG).show();

                }
            } else {
                heading.setBackgroundColor((int) Color.pack(102,0,153));
                Toast.makeText(getApplicationContext(), "Word must be 5 letters", Toast.LENGTH_LONG).show();
            }
        }
    };


//    public String randomWord(ArrayList list){
//        int rand_num;
//        String word = "hello";
//        if (wordList.size() != 0){
//            rand_num = rand.nextInt(list.size());
//            return list.get(rand_num).toString();
//        }
//        return word;
//    }



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

        db = FirebaseDatabase.getInstance();
        reference = db.getReference("words");
        //words in the database to begin with
        reference.setValue("games");
        reference.setValue("honey");
        reference.setValue("ocean");
        wordList.add("ocean");
        wordList.add("games");
        wordList.add("honey");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String word = dataSnapshot.getValue(String.class);
                    wordList.add(word);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}