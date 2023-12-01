package com.example.calculatorapp;

import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private EditText editTextInputNumber;
    private String operator = "";
    private String oldNumber;
    private boolean isNew = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInputNumber = findViewById(R.id.editTextInputNumber);

        // Number buttons
        findViewById(R.id.zeroButton).setOnClickListener(v -> numberClicked("0"));
        findViewById(R.id.button1).setOnClickListener(v -> numberClicked("1"));
        findViewById(R.id.button2).setOnClickListener(v -> numberClicked("2"));
        findViewById(R.id.button3).setOnClickListener(v -> numberClicked("3"));
        findViewById(R.id.button4).setOnClickListener(v -> numberClicked("4"));
        findViewById(R.id.button5).setOnClickListener(v -> numberClicked("5"));
        findViewById(R.id.button6).setOnClickListener(v -> numberClicked("6"));
        findViewById(R.id.button7).setOnClickListener(v -> numberClicked("7"));
        findViewById(R.id.button8).setOnClickListener(v -> numberClicked("8"));
        findViewById(R.id.button9).setOnClickListener(v -> numberClicked("9"));
        findViewById(R.id.pointButton).setOnClickListener(v -> numberClicked("."));
        findViewById(R.id.plusMinusButton).setOnClickListener(v -> numberClicked("-"));

        // Operation buttons
        findViewById(R.id.plusButton).setOnClickListener(v -> operationClicked("+"));
        findViewById(R.id.minusButton).setOnClickListener(v -> operationClicked("-"));
        findViewById(R.id.multiplyButton).setOnClickListener(v -> operationClicked("*"));
        findViewById(R.id.divideButton).setOnClickListener(v -> operationClicked("/"));
        findViewById(R.id.equalButton).setOnClickListener(v -> equalClick());
        findViewById(R.id.percentButton).setOnClickListener(v -> percentClick());
        findViewById(R.id.clearButton).setOnClickListener(v -> clearClicked());
    }

    private void percentClick() {
        if (operator.equals("")){
            String number = editTextInputNumber.getText().toString();
            double temp = Double.parseDouble(number) / 100;
            number = temp+"";
            editTextInputNumber.setText(number);
        }else{
            String newNumber = editTextInputNumber.getText().toString();
            Double result = 0.0;

            switch (operator){
                case "+":
                    result = Double.parseDouble(oldNumber) + Double.parseDouble(oldNumber) * Double.parseDouble(newNumber) / 100;
                    break;
                case "-":
                    result = Double.parseDouble(oldNumber) - Double.parseDouble(oldNumber) * Double.parseDouble(newNumber) / 100;
                    break;
                case "*":
                    result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber) / 100;
                    break;
                case "/":
                    result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber) * 100;
                    break;
            }

            editTextInputNumber.setText(String.valueOf(result));
            operator = "";
        }
    }

    private void numberClicked(String number) {
        if(isNew)
            editTextInputNumber.setText("");
        isNew = false;
        String currentText = editTextInputNumber.getText().toString();

        switch (number){
            case "1": currentText = currentText + "1"; break;
            case "2": currentText = currentText + "2"; break;
            case "3": currentText = currentText + "3"; break;
            case "4": currentText = currentText + "4"; break;
            case "5": currentText = currentText + "5"; break;
            case "6": currentText = currentText + "6"; break;
            case "7": currentText = currentText + "7"; break;
            case "8": currentText = currentText + "8"; break;
            case "9": currentText = currentText + "9"; break;
            case "0": currentText = currentText + "0"; break;

            case ".":
                if (!currentText.contains(".")){
                    currentText = currentText + ".";
                }
                break;

            case "-":
                if (currentText.equals("0") || currentText.equals("") || currentText.equals("0.0")){
                    currentText = "0";
                }else{
                    if (currentText.charAt(0) == '-'){
                        currentText = currentText.substring(1);
                    }else{
                        currentText = "-" + currentText;
                    }
                }
                break;
        }

        editTextInputNumber.setText(currentText);

    }

    private void operationClicked(String operation) {
        isNew = true;
        oldNumber = editTextInputNumber.getText().toString();

                switch (operation) {
                    case "+":
                        operator = "+";
                        break;
                    case "-":
                        operator = "-";
                        break;
                    case "*":
                        operator = "*";
                        break;
                    case "/":
                        operator = "/";
                        break;
                    case "%":
                        operator = "%";
                        break;
                }
    }

    private void equalClick(){
        String newNumber = editTextInputNumber.getText().toString();
        Double result = 0.0;

        switch (operator){
            case "+":
                result = Double.parseDouble(oldNumber) + Double.parseDouble(newNumber);
                break;
            case "-":
                result = Double.parseDouble(oldNumber) - Double.parseDouble(newNumber);
                break;
            case "*":
                result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber);
                break;
            case "/":
                result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber);
                break;
        }

        editTextInputNumber.setText(String.valueOf(result));
    }

    private void clearClicked() {
        editTextInputNumber.setText("0");
        isNew = true;
    }
}