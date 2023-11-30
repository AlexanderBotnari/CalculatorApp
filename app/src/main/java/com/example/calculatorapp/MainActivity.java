package com.example.calculatorapp;

import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private EditText editTextInputNumber;
    private double operand1 = Double.NaN;
    private String pendingOperation = "=";
    private boolean isDecimal = false;

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

        // Operation buttons
        findViewById(R.id.plusButton).setOnClickListener(v -> operationClicked("+"));
        findViewById(R.id.minusButton).setOnClickListener(v -> operationClicked("-"));
        findViewById(R.id.multiplyButton).setOnClickListener(v -> operationClicked("*"));
        findViewById(R.id.divideButton).setOnClickListener(v -> operationClicked("/"));
        findViewById(R.id.equalButton).setOnClickListener(v -> operationClicked("="));
        findViewById(R.id.clearButton).setOnClickListener(v -> clearClicked());
    }

    private void numberClicked(String number) {
        String currentText = editTextInputNumber.getText().toString();
        editTextInputNumber.setText(number);
    }

    private void operationClicked(String operation) {
        String value = editTextInputNumber.getText().toString();

        if (!value.isEmpty()) {
            double currentValue = Double.parseDouble(value);

            if (!Double.isNaN(operand1)) {
                switch (pendingOperation) {
                    case "=":
                        operand1 = currentValue;
                        break;
                    case "+":
                        operand1 += currentValue;
                        break;
                    case "-":
                        operand1 -= currentValue;
                        break;
                    case "*":
                        operand1 *= currentValue;
                        break;
                    case "/":
                        if (currentValue != 0) {
                            operand1 /= currentValue;
                        } else {
                            editTextInputNumber.setText("Error");
                            return;
                        }
                        break;
                }
                editTextInputNumber.setText(String.valueOf(operand1));
            } else {
                operand1 = currentValue;
            }
        }

        pendingOperation = operation.equals("=") ? "=" : operation;
    }

//    private void clearClicked() {
//        operand1 = Double.NaN;
//        pendingOperation = "=";
//        editTextInputNumber.setText("");
//    }
    private void clearClicked() {
        operand1 = Double.NaN;
        pendingOperation = "=";
        editTextInputNumber.setText("");
    }
}