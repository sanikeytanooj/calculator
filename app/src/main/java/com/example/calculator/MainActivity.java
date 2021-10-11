package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display=findViewById(R.id.textView);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(view -> {
            if(getString(R.string.display).equals(display.getText().toString()))
                display.setText("");
        });
    }

    private void updateText(String strToAdd)
    {
        String oldStr=display.getText().toString();
        int cursorPos=display.getSelectionStart();
        String leftStr=oldStr.substring(0, cursorPos);
        String rightStr=oldStr.substring(cursorPos);
        if(getString(R.string.display).equals(display.getText().toString()))
        {
           display.setText(strToAdd);
        }
        else
        {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
        }
        display.setSelection(cursorPos + 1);
    }

    public void zero(View view) {
        updateText("0");
    }
    public void point(View view) {
        updateText(".");
    }
    public void plusminus(View view) {
        updateText("-");
    }
    public void equals(View view) {
        String userExp=display.getText().toString();
        userExp = userExp.replaceAll("÷","/");
        userExp = userExp.replaceAll("×","*");

        Expression exp=new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);

        display.setSelection(result.length());
    }
    public void plus(View view) {
        updateText("+");
    }
    public void one(View view) {
        updateText("1");
    }
    public void two(View view) {

        updateText("2");
    }
    public void three(View view) {
        updateText("3");
    }
    public void four(View view) {
        updateText("4");
    }
    public void five(View view) {
        updateText("5");
    }
    public void six(View view) {
        updateText("6");
    }
    public void subtract(View view) {
        updateText("-");
    }
    public void seven(View view) {
        updateText("7");
    }
    public void eight(View view) {
        updateText("8");
    }
    public void nine(View view) {
        updateText("9");
    }
    public void multiply(View view) {
        updateText("×");
    }
    public void divide(View view) {
        updateText("÷");
    }
    public void exponent(View view) {
        updateText("^");
    }
    public void parentheses(View view) {
        int cursorPos=display.getSelectionStart();
        int openpar = 0;
        int closedPar = 0;
        int textlen = display.getText().length();

        for (int i = 0;i < cursorPos; i++){
            if(display.getText().toString().charAt(i) == '(') {
                openpar +=1;
            }
            if(display.getText().toString().charAt(i) == ')') {
                closedPar +=1;
            }
        }
        if(openpar == closedPar || display.getText().toString().charAt(textlen - 1) == '(') {
            updateText("(");
            display.setSelection(cursorPos + 1);
        }
        else if(closedPar < openpar && display.getText().toString().charAt(textlen - 1) != '(') {
            updateText(")");
            display.setSelection(cursorPos + 1);
        }
        display.setSelection(cursorPos + 1);
    }
    public void clear(View view) {
        display.setText("");
    }
    public void backspace(View view) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if(cursorPos != 0 && textLen!= 0) {
            SpannableStringBuilder selection=(SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1,cursorPos,"");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }
}