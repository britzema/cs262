package edu.calvin.cs262.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // EditText for val1
    private EditText firstValEditText;
    // EditText for val2
    private EditText secondValEditText;
    // TextView for the result to be shown
    private TextView resultTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the 2 EditText variables and the 1 TextView
        firstValEditText = findViewById(R.id.value1);
        secondValEditText = findViewById(R.id.value2);
        resultTextView = findViewById(R.id.result);

    }

    public void calculateResult (View v) {
        //get the correct operation from the spinner operation
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        String operationString = mySpinner.getSelectedItem().toString();


        int firstValue = Integer.parseInt(firstValEditText.getText().toString());
        int secondValue = Integer.parseInt(secondValEditText.getText().toString());
        int result = 0;

        switch(operationString)
        {
            case "+":
                result = firstValue + secondValue;
                break;
            case "-":
                result = firstValue - secondValue;
                break;
            case "*":
                result = firstValue * secondValue;
                break;
            case "/":
                result = firstValue / secondValue;
                break;
        }

        //display the result in the TextView result
        final TextView textViewToChange = (TextView) findViewById(R.id.result);
        textViewToChange.setText(Integer.toString(result));

    }
}
