package ru.obessonova.lesson02_androidschool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    EditText field1;
    EditText field2;

    RadioGroup operations;

    CheckBox floatValues;
    CheckBox signedValues;

    TextView resultField;

    Button calculateButton;

    String result;
    float number1;
    float number2;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        field1 = findViewById(R.id.field1);
        field2 = findViewById(R.id.field2);

        operations = findViewById(R.id.operations);
        floatValues = findViewById(R.id.floatValues);
        signedValues = findViewById(R.id.signedValues);
        resultField = findViewById(R.id.resultField);
        calculateButton = findViewById(R.id.calculateButton);

        operations.setOnCheckedChangeListener(this);

            if (field1.getText().toString().trim().length() > 0 && field2.getText().toString().trim().length() > 0) {
                number1 = Float.parseFloat(field1.getText().toString());
                number2 = Float.parseFloat(field2.getText().toString());
            }
    }

    public void checkBoxClicked(View view) {
        switch (view.getId()) {
            case R.id.floatValues:
                if (!floatValues.isChecked()) {
                    field1.setInputType(8192);
                    field2.setInputType(8192);
                } else {
                    number1 = (int) number1;
                    number2 = (int) number2;
                }
            case R.id.signedValues:
                if (signedValues.isChecked()) {
                    field1.setInputType(4096);
                    field2.setInputType(4096);
                } else {
                    field1.setInputType(0);
                    field2.setInputType(0);
                }
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case -1:
                Toast.makeText(getApplicationContext(), R.string.operation_not_found_message,
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.plus:
                result = "" + (number1 + number2);
                break;
            case R.id.minus:
                result = "" + (number1 - number2);
                break;
            case R.id.divide:
                result = "" + (number1 / number2);
                break;
            case R.id.multiple:
                result = "" + (number1 * number2);
                break;
            default:
                break;
        }
    }

    public void calculate(View view) {
        resultField.setText(result);
    }
}