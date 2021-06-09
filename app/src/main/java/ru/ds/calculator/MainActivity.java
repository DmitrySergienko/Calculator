package ru.ds.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Calculator calculator;

    public static TextView resultField; // текстовое поле для вывода результата
    public static EditText numberField; // поле для ввода числа
    public static TextView operationField; //текстовое поле для вывода знака операции
    public static Double operand = null; //операнд
    public static String lastOperation = "="; //последняя операция

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator();

        //получаем все поля по id
        resultField = (TextView) findViewById(R.id.resultView);
        numberField = (EditText) findViewById(R.id.numberField);
        operationField = (TextView) findViewById(R.id.operationView);

        //стереть запись
        findViewById(R.id.key_coma).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultField.setText("0.0");
            }
        });
    }

    //обработка нажатия на кнопку
    public void onNumberClick(View view) {
        Button button = (Button) view;
        numberField.append(button.getText());

        if (lastOperation.equals("=") && operand != null) {
            operand = null;
        }
    }

    //обработка нажатия операции
    public void onOperationClick(View view) {
        Button button = (Button) view;
        String op = button.getText().toString();
        String number = numberField.getText().toString();

        if (number.length() > 0) {
            number = number.replace(',', '.');
            try {
                calculator.performOperation(Double.valueOf(number), op);
            } catch (NumberFormatException ex) {
                numberField.setText("");
            }
        }
        MainActivity.lastOperation = op;
        MainActivity.operationField.setText(MainActivity.lastOperation);
    }

    //сохраняем состояния
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("OPERATION", lastOperation);
        if (operand != null)
            outState.putDouble("OPERAND", operand);
        super.onSaveInstanceState(outState);
    }

    //получаем ранее сохраненное состояние
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastOperation = savedInstanceState.getString("OPERATION");
        operand = savedInstanceState.getDouble("OPERAND");
        resultField.setText(operand.toString());
        operationField.setText(lastOperation);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}