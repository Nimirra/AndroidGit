package ru.obessonova.lesson02_androidschool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText field1;
    EditText field2;

    RadioGroup operations;
    RadioGroup operations2;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState != null) {
          /*  number1 = savedInstanceState.getFloat("number1");
            number2 = savedInstanceState.getFloat("number2");*/
            result = savedInstanceState.getString("result");
        }

        field1 = findViewById(R.id.field1);
        field2 = findViewById(R.id.field2);

        operations = findViewById(R.id.operations);
        operations2 = findViewById(R.id.operations2);
        floatValues = findViewById(R.id.floatValues);
        signedValues = findViewById(R.id.signedValues);
        resultField = findViewById(R.id.resultField);
        calculateButton = findViewById(R.id.calculateButton);

        if (field1.getText().toString().length() > 0 && field2.getText().toString().length() > 0) {
            number1 = Float.parseFloat(field1.getText().toString());
            number2 = Float.parseFloat(field2.getText().toString());
        }

        operations.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
              /*  RadioGroup otherGroup = group == operations ? operations2 : operations;
                otherGroup.clearCheck();*/
                switch (checkedId) {
                    case R.id.plus:
                        result = "" + (number1 + number2);
                        break;
                    case R.id.minus:
                        result = "" + (number1 - number2);
                        break;
                }
            }
        });
        operations2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               /* RadioGroup otherGroup = group == operations ? operations2 : operations;
                otherGroup.clearCheck();*/
                switch (checkedId) {
                    case R.id.divide:
                        result = "" + (number1 / number2);
                        break;
                    case R.id.multiple:
                        result = "" + (number1 * number2);
                        break;
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear:
               field1.clearComposingText();
               field2.clearComposingText();
               result="";
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void checkBoxClicked(View view) {
        switch (view.getId()) {
            case R.id.floatValues:
                if (!floatValues.isChecked()) {
                    field1.setInputType(8192);
                    field2.setInputType(8192);
                } /*else {
                    number1 = (int) number1;
                    number2 = (int) number2;
                }*/
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


    public void calculate(View view) {
        if (operations.getCheckedRadioButtonId() == -1 && operations2.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getApplicationContext(), R.string.operation_not_found_message,
                    Toast.LENGTH_SHORT).show();
        } else {
            resultField.setText(result);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    /*    savedInstanceState.putFloat("field1", number1);
        savedInstanceState.putFloat("field2", number2);*/
        savedInstanceState.putString("result", result);
    }
}