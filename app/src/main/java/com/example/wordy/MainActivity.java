package com.example.wordy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String word;
    ArrayList<String> wordList = new ArrayList<>();
//    String target = "world";
    Button addWordButton, restartButton, clearButton, enterButton;
    FirebaseDatabase db  = FirebaseDatabase.getInstance();
    DatabaseReference reference = db.getReference("words");
    EditText edt1, edt2, edt3, edt4, edt5;
    EditText edt6, edt7, edt8, edt9, edt10;
    EditText edt11, edt12, edt13, edt14, edt15;
    EditText edt16, edt17, edt18, edt19, edt20;
    EditText edt21, edt22, edt23, edt24, edt25;
    EditText edt26, edt27, edt28, edt29, edt30;
    Random rand = new Random();


    View.OnClickListener restartListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            clear();
            restart();
            makeGameStart();
            onRestart();
            getWord();
        }
    };

    View.OnClickListener clearListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            clear() ;
            restart();
        }
    };

    View.OnClickListener addWordListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, CreateWordActivity.class );
            startActivity(intent);

        }
    };

    View.OnClickListener enterListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (edt5.length() == 1)  validateRow(edt1, edt2, edt3, edt4, edt5);
            if (edt10.length() == 1) validateRow(edt6, edt7, edt8, edt9, edt10);
            if (edt15.length() == 1) validateRow(edt11, edt12, edt13, edt14, edt15);
            if (edt20.length() == 1) validateRow(edt16, edt17, edt18, edt19, edt20);
            if (edt25.length() == 1) validateRow(edt21, edt22, edt23, edt24, edt25);
            if (edt30.length() == 1) validateRow(edt26, edt27, edt28, edt29, edt30);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startGame();

        Intent intent = getIntent();
//        word = intent.getStringExtra("word");
        addWordButton = findViewById(R.id.button_addWord);
        restartButton = findViewById(R.id.button_restart);
        clearButton = findViewById(R.id.button_clear);
        enterButton = findViewById(R.id.enter_button);
        edt1 = findViewById(R.id.et_1);
        edt2 = findViewById(R.id.et_2);
        edt3 = findViewById(R.id.et_3);
        edt4 = findViewById(R.id.et_4);
        edt5 = findViewById(R.id.et_5);

        edt6 = findViewById(R.id.et_6);
        edt7 = findViewById(R.id.et_7);
        edt8 = findViewById(R.id.et_8);
        edt9 = findViewById(R.id.et_9);
        edt10 = findViewById(R.id.et_10);

        edt11 = findViewById(R.id.et_11);
        edt12 = findViewById(R.id.et_12);
        edt13 = findViewById(R.id.et_13);
        edt14 = findViewById(R.id.et_14);
        edt15 = findViewById(R.id.et_15);

        edt16 = findViewById(R.id.et_16);
        edt17 = findViewById(R.id.et_17);
        edt18 = findViewById(R.id.et_18);
        edt19 = findViewById(R.id.et_19);
        edt20 = findViewById(R.id.et_20);

        edt21 = findViewById(R.id.et_21);
        edt22 = findViewById(R.id.et_22);
        edt23 = findViewById(R.id.et_23);
        edt24 = findViewById(R.id.et_24);
        edt25 = findViewById(R.id.et_25);

        edt26 = findViewById(R.id.et_26);
        edt27 = findViewById(R.id.et_27);
        edt28 = findViewById(R.id.et_28);
        edt29 = findViewById(R.id.et_29);
        edt30 = findViewById(R.id.et_30);

//        db = FirebaseDatabase.getInstance();
//        reference = db.getReference("words");
//        reference.setValue(w.getWord());
//        reference.setValue(w2.getWord());
//        reference.setValue(w3.getWord());



        Toast.makeText(this, "Connection was successful", Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), word, Toast.LENGTH_LONG).show();

        enterButton.setOnClickListener(enterListener);
        clearButton.setOnClickListener(clearListener);
        restartButton.setOnClickListener(restartListener);
        addWordButton.setOnClickListener(addWordListener);
        getWord();



    }


    public void getWord(){
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String word = dataSnapshot.getValue(String.class);
                    wordList.add(word);
                }
                word = randomWord(wordList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void validateRow(EditText et1,EditText et2, EditText et3, EditText et4, EditText et5){
//        Intent intent = getIntent();
//        word = intent.getStringExtra("word").toString().toUpperCase();

        char editText1 = et1.getText().toString().toUpperCase().charAt(0);
        char editText2 = et2.getText().toString().toUpperCase().charAt(0);
        char editText3 = et3.getText().toString().toUpperCase().charAt(0);
        char editText4 = et4.getText().toString().toUpperCase().charAt(0);
        char editText5 = et5.getText().toString().toUpperCase().charAt(0);

        char w1 = word.charAt(0);
        char w2 = word.charAt(1);
        char w3 = word.charAt(2);
        char w4 = word.charAt(3);
        char w5 = word.charAt(4);

        // compares if later entered in edt is equal to any of the other letters
        if (editText1 == w2 || editText1 == w3 || editText1 == w4 || editText1 == w5) {
            et1.setBackgroundColor(Color.parseColor("#f0d138")); // sets color to yellow if so
        }
        // compares if later entered in edt is equal to any of the other letters
        if (editText2 == w1 || editText2 == w3 || editText2 == w4 || editText2 == w5) {
            et2.setBackgroundColor(Color.parseColor("#f0d138")); // sets color to yellow if so
        }
        // compares if later entered in edt is equal to any of the other letters
        if (editText3 == w1 || editText3 == w2 || editText3 == w4 || editText3 == w5) {
            et3.setBackgroundColor(Color.parseColor("#f0d138")); // sets color to yellow if so
        }
        // compares if later entered in edt is equal to any of the other letters
        if (editText4 == w1 || editText4 == w2 || editText4 == w3 || editText4 == w5) {
            et4.setBackgroundColor(Color.parseColor("#f0d138")); // sets color to yellow if so
        }
        // compares if later entered in edt is equal to any of the other letters
        if (editText5 == w1 || editText5 == w2 || editText5 == w3 || editText5 == w4) {
            et5.setBackgroundColor(Color.parseColor("#f0d138")); // sets color to yellow if so
        }



        // checks if letter is equal to letter in edt
        if (editText1 == w1) {
            et1.setBackgroundColor(Color.parseColor("#87ea6c")); // sets color to green if so
        }
        if (editText2 == w2) {
            et2.setBackgroundColor(Color.parseColor("#87ea6c")); // sets color to green if so
        }
        if (editText3 == w3) {
            et3.setBackgroundColor(Color.parseColor("#87ea6c")); // sets color to green if so
        }
        if (editText4 == w4) {
            et4.setBackgroundColor(Color.parseColor("#87ea6c")); // sets color to green if so
        }
        if (editText5 == w5) {
            et5.setBackgroundColor(Color.parseColor("#87ea6c")); // sets color to green if so
        }


        //checks if none of the letters are in the word
        if (editText1 != w1 && editText1 != w2 && editText1 != w3 && editText1 != w4 && editText1 != w5) {
            et1.setBackgroundColor(Color.parseColor("#797c7e")); // if so, changes color to grey
        }

        if (editText2 != w1 && editText2 != w2 && editText2 != w3 && editText2 != w4 && editText2 != w5) {
            et2.setBackgroundColor(Color.parseColor("#797c7e"));
        }

        if (editText3 != w1 && editText3 != w2 && editText3 != w3 && editText3 != w4 && editText3 != w5) {
            et3.setBackgroundColor(Color.parseColor("#797c7e"));
        }

        if (editText4 != w1 && editText4 != w2 && editText4 != w3 && editText4 != w4 && editText4 != w5) {
            et4.setBackgroundColor(Color.parseColor("#797c7e"));
        }

        if (editText5 != w1 && editText5 != w2 && editText5 != w3 && editText5 != w4 && editText5 != w5) {
            et5.setBackgroundColor(Color.parseColor("#797c7e"));
        }


        //Todo: Handle if the user guesses the word correctly
        if (editText1 == w1 && editText2 == w2 && editText3 == w3 && editText4 == w4 && editText5 == w5) {
            Toast.makeText(getApplicationContext(), "Congratulations, you have guessed the right word!", Toast.LENGTH_SHORT).show();
            makeGameStop();
        }

    }

    private void startGame(){

    }

    public void clear(){
        edt1.setText("");
        edt2.setText("");
        edt3.setText("");
        edt4.setText("");
        edt5.setText("");

        edt6.setText("");
        edt7.setText("");
        edt8.setText("");
        edt9.setText("");
        edt10.setText("");

        edt11.setText("");
        edt12.setText("");
        edt13.setText("");
        edt14.setText("");
        edt15.setText("");

        edt16.setText("");
        edt17.setText("");
        edt18.setText("");
        edt19.setText("");
        edt20.setText("");

        edt21.setText("");
        edt22.setText("");
        edt23.setText("");
        edt24.setText("");
        edt25.setText("");

        edt26.setText("");
        edt27.setText("");
        edt28.setText("");
        edt29.setText("");
        edt30.setText("");
    }

    public void restart(){
        edt1.setBackground(getDrawable(R.drawable.border));
        edt2.setBackground(getDrawable(R.drawable.border));
        edt3.setBackground(getDrawable(R.drawable.border));
        edt4.setBackground(getDrawable(R.drawable.border));
        edt5.setBackground(getDrawable(R.drawable.border));


        edt6.setBackground(getDrawable(R.drawable.border));
        edt7.setBackground(getDrawable(R.drawable.border));
        edt8.setBackground(getDrawable(R.drawable.border));
        edt9.setBackground(getDrawable(R.drawable.border));
        edt10.setBackground(getDrawable(R.drawable.border));

        edt11.setBackground(getDrawable(R.drawable.border));
        edt12.setBackground(getDrawable(R.drawable.border));
        edt13.setBackground(getDrawable(R.drawable.border));
        edt14.setBackground(getDrawable(R.drawable.border));
        edt15.setBackground(getDrawable(R.drawable.border));

        edt16.setBackground(getDrawable(R.drawable.border));
        edt17.setBackground(getDrawable(R.drawable.border));
        edt18.setBackground(getDrawable(R.drawable.border));
        edt19.setBackground(getDrawable(R.drawable.border));
        edt20.setBackground(getDrawable(R.drawable.border));

        edt21.setBackground(getDrawable(R.drawable.border));
        edt22.setBackground(getDrawable(R.drawable.border));
        edt23.setBackground(getDrawable(R.drawable.border));
        edt24.setBackground(getDrawable(R.drawable.border));
        edt25.setBackground(getDrawable(R.drawable.border));

        edt26.setBackground(getDrawable(R.drawable.border));
        edt27.setBackground(getDrawable(R.drawable.border));
        edt28.setBackground(getDrawable(R.drawable.border));
        edt29.setBackground(getDrawable(R.drawable.border));
        edt30.setBackground(getDrawable(R.drawable.border));
    }

    //TODO: FIX THIS CODE
    private void makeGameStart(){
        edt1.setEnabled(true);
        edt2.setEnabled(true);
        edt3.setEnabled(true);
        edt4.setEnabled(true);
        edt5.setEnabled(true);


        edt6.setEnabled(true);
        edt7.setEnabled(true);
        edt8.setEnabled(true);
        edt9.setEnabled(true);
        edt10.setEnabled(true);

        edt11.setEnabled(true);
        edt12.setEnabled(true);
        edt13.setEnabled(true);
        edt14.setEnabled(true);
        edt15.setEnabled(true);

        edt16.setEnabled(true);
        edt17.setEnabled(true);
        edt18.setEnabled(true);
        edt19.setEnabled(true);
        edt20.setEnabled(true);

        edt21.setEnabled(true);
        edt22.setEnabled(true);
        edt23.setEnabled(true);
        edt24.setEnabled(true);
        edt25.setEnabled(true);

        edt26.setEnabled(true);
        edt27.setEnabled(true);
        edt28.setEnabled(true);
        edt29.setEnabled(true);
        edt30.setEnabled(true);
    }


    //TODO: FIX THIS CODE
    private void makeGameStop(){
        edt1.setEnabled(false);
        edt2.setEnabled(false);
        edt3.setEnabled(false);
        edt4.setEnabled(false);
        edt5.setEnabled(false);


        edt6.setEnabled(false);
        edt7.setEnabled(false);
        edt8.setEnabled(false);
        edt9.setEnabled(false);
        edt10.setEnabled(false);

        edt11.setEnabled(false);
        edt12.setEnabled(false);
        edt13.setEnabled(false);
        edt14.setEnabled(false);
        edt15.setEnabled(false);

        edt16.setEnabled(false);
        edt17.setEnabled(false);
        edt18.setEnabled(false);
        edt19.setEnabled(false);
        edt20.setEnabled(false);

        edt21.setEnabled(false);
        edt22.setEnabled(false);
        edt23.setEnabled(false);
        edt24.setEnabled(false);
        edt25.setEnabled(false);

        edt26.setEnabled(false);
        edt27.setEnabled(false);
        edt28.setEnabled(false);
        edt29.setEnabled(false);
        edt30.setEnabled(false);
    }

    public String randomWord(ArrayList<String> list){
        int rand_num;
        if (list.size() != 0){
            rand_num = rand.nextInt(list.size());
            return list.get(rand_num).toUpperCase();
        }
        return "world";
    }





}