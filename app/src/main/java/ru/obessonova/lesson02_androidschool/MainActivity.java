package ru.obessonova.lesson02_androidschool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
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
    RadioGroup operations2;

    CheckBox floatValues;
    CheckBox signedValues;

    TextView resultField;

    Button calculateButton;

    String result;
    float number1;
    float number2;

    boolean floatIsChecked;
    boolean signedIsChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        field1 = findViewById(R.id.field1);
        field2 = findViewById(R.id.field2);

        operations = findViewById(R.id.operations);
        operations2 = findViewById(R.id.operations2);
        floatValues = findViewById(R.id.floatValues);
        signedValues = findViewById(R.id.signedValues);
        resultField = findViewById(R.id.resultField);
        calculateButton = findViewById(R.id.calculateButton);

        operations.setOnCheckedChangeListener(this);
        operations2.setOnCheckedChangeListener(this);

        if(savedInstanceState!=null){
          number1 = savedInstanceState.getFloat("number1");
          number2 = savedInstanceState.getFloat("number2");
          result=savedInstanceState.getString("result");
        }
        field1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.clear:
                field1.setText("");
                field2.setText("");
                resultField.setText(R.string.result_field);
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId){
       /* switch (group.getId()) {
            case R.id.operations:
                operations2.clearCheck();
                break;
            case R.id.operations2:
                operations.clearCheck();
        }*/
        if (field1.getText().toString().length() > 0 && field2.getText().toString().length() > 0) {
            number1 = Float.parseFloat(field1.getText().toString());
            number2 = Float.parseFloat(field2.getText().toString());
            switch (checkedId) {
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
            }
        }
    }



    public void checkBoxClicked(View view) {
        floatIsChecked= floatValues.isChecked();
        signedIsChecked = signedValues.isChecked();

        if (floatIsChecked && signedIsChecked) {
            field1.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            field2.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        } if (floatIsChecked && !signedIsChecked) {
            field1.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            field2.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        } if (!floatIsChecked && signedIsChecked) {
            field1.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
            field2.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
        } else {
            field1.setInputType(InputType.TYPE_CLASS_NUMBER);
            field2.setInputType(InputType.TYPE_CLASS_NUMBER);
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

        savedInstanceState.putFloat("field1", number1);
        savedInstanceState.putFloat("field2", number2);
        savedInstanceState.putString("result", result);
       /* savedInstanceState.putBoolean("floatChecked", result);*/
    }
}
