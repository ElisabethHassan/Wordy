package com.example.wordy;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String word = "games";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void validateRow(EditText et1,EditText et2, EditText et3, EditText et4, EditText et5){
        char editText1 = et1.getText().toString().charAt(0);
        char editText2 = et2.getText().toString().charAt(0);
        char editText3 = et3.getText().toString().charAt(0);
        char editText4 = et4.getText().toString().charAt(0);
        char editText5 = et5.getText().toString().charAt(0);

        char w1 = word.charAt(0);
        char w2 = word.charAt(1);
        char w3 = word.charAt(2);
        char w4 = word.charAt(3);
        char w5 = word.charAt(4);

        // compares if later entered in edt is equal to any of the other letters
        if (editText1 == w2 || editText1 == w3 || editText1 == w4 || editText1 == w5) {
            et1.setBackgroundColor(Color.parseColor("#ffff00")); // sets color to yellow if so
        }
        // compares if later entered in edt is equal to any of the other letters
        if (editText2 == w1 || editText2 == w3 || editText2 == w4 || editText2 == w5) {
            et2.setBackgroundColor(Color.parseColor("#ffff00")); // sets color to yellow if so
        }
        // compares if later entered in edt is equal to any of the other letters
        if (editText3 == w1 || editText3 == w2 || editText3 == w4 || editText3 == w5) {
            et3.setBackgroundColor(Color.parseColor("#ffff00")); // sets color to yellow if so
        }
        // compares if later entered in edt is equal to any of the other letters
        if (editText4 == w1 || editText4 == w2 || editText4 == w3 || editText4 == w5) {
            et4.setBackgroundColor(Color.parseColor("#ffff00")); // sets color to yellow if so
        }
        // compares if later entered in edt is equal to any of the other letters
        if (editText5 == w1 || editText5 == w2 || editText5 == w3 || editText5 == w4) {
            et5.setBackgroundColor(Color.parseColor("#ffff00")); // sets color to yellow if so
        }



        // checks if letter is equal to letter in edt
        if (editText1 == w1) {
            et1.setBackgroundColor(Color.parseColor("#33cc33")); // sets color to green if so
        }
        if (editText2 == w2) {
            et2.setBackgroundColor(Color.parseColor("#33cc33")); // sets color to green if so
        }
        if (editText3 == w3) {
            et3.setBackgroundColor(Color.parseColor("#33cc33")); // sets color to green if so
        }
        if (editText4 == w4) {
            et4.setBackgroundColor(Color.parseColor("#33cc33")); // sets color to green if so
        }
        if (editText5 == w5) {
            et5.setBackgroundColor(Color.parseColor("#33cc33")); // sets color to green if so
        }



        //checks if none of the letters are in the word
        if (editText1 != w1 && editText1 != w2 && editText1 != w3 && editText1 != w4 && editText1 != w5) {
            et1.setBackgroundColor(Color.parseColor("#ff3333")); // if so, changes color to grey
        }

        if (editText2 != w1 && editText2 != w2 && editText2 != w3 && editText2 != w4 && editText2 != w5) {
            et2.setBackgroundColor(Color.parseColor("#ff3333"));
        }

        if (editText3 != w1 && editText3 != w2 && editText3 != w3 && editText3 != w4 && editText3 != w5) {
            et3.setBackgroundColor(Color.parseColor("#aaa"));
        }

        if (editText4 != w1 && editText4 != w2 && editText4 != w3 && editText4 != w4 && editText4 != w5) {
            et4.setBackgroundColor(Color.parseColor("#ff3333"));
        }

        if (editText5 != w1 && editText5 != w2 && editText5 != w3 && editText5 != w4 && editText5 != w5) {
            et5.setBackgroundColor(Color.parseColor("#ff3333"));
        }


        //Todo: Handle if the user guesses the word correctly
        if (editText1 == w1 && editText2 == w2 && editText3 == w3 && editText4 == w4 && editText5 == w5) {
            Toast.makeText(getApplicationContext(), "Congratulations, you have guessed the right word!", Toast.LENGTH_SHORT).show();
        }


    }
}