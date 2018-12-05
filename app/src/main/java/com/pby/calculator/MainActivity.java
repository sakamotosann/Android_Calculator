package com.pby.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button _0, _1, _2, _3, _4, _5, _6, _7, _8, _9, Add, Sub, Mul, Div, Dot, Eql, Clr, Del;
    TextView Input, Output;

    String input = "", output = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _0 = findViewById(R.id._0);
        _1 = findViewById(R.id._1);
        _2 = findViewById(R.id._2);
        _3 = findViewById(R.id._3);
        _4 = findViewById(R.id._4);
        _5 = findViewById(R.id._5);
        _6 = findViewById(R.id._6);
        _7 = findViewById(R.id._7);
        _8 = findViewById(R.id._8);
        _9 = findViewById(R.id._9);

        Add = findViewById(R.id.Add);
        Sub = findViewById(R.id.Sub);
        Mul = findViewById(R.id.Mul);
        Div = findViewById(R.id.Div);
        Dot = findViewById(R.id.Dot);
        Eql = findViewById(R.id.Eql);
        Clr = findViewById(R.id.Clr);
        Del = findViewById(R.id.Del);

        Input = findViewById(R.id.Input);
        Output = findViewById(R.id.Output);

        _0.setOnClickListener(this);
        _1.setOnClickListener(this);
        _2.setOnClickListener(this);
        _3.setOnClickListener(this);
        _4.setOnClickListener(this);
        _5.setOnClickListener(this);
        _6.setOnClickListener(this);
        _7.setOnClickListener(this);
        _8.setOnClickListener(this);
        _9.setOnClickListener(this);

        Add.setOnClickListener(this);
        Sub.setOnClickListener(this);
        Mul.setOnClickListener(this);
        Div.setOnClickListener(this);
        Dot.setOnClickListener(this);
        Eql.setOnClickListener(this);
        Clr.setOnClickListener(this);
        Del.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id._0:
                input += "0";
                break;
            case R.id._1:
                input += "1";
                break;
            case R.id._2:
                input += "2";
                break;
            case R.id._3:
                input += "3";
                break;
            case R.id._4:
                input += "4";
                break;
            case R.id._5:
                input += "5";
                break;
            case R.id._6:
                input += "6";
                break;
            case R.id._7:
                input += "7";
                break;
            case R.id._8:
                input += "8";
                break;
            case R.id._9:
                input += "9";
                break;
            case R.id.Add:
                input += "+";
                break;
            case R.id.Sub:
                input += "-";
                break;
            case R.id.Mul:
                input += "×";
                break;
            case R.id.Div:
                input += "÷";
                break;
            case R.id.Dot:
                input += ".";
                break;
            case R.id.Clr:
                input = output = "";
                break;
            case R.id.Del:
                if (input.length() > 0)
                    input = input.substring(0, input.length() - 1);
                break;
            case R.id.Eql:
                if (!input.equals("")) {
                    Stack<Double> num = new Stack<Double>();
                    Stack<Character> op = new Stack<Character>();
                    StringBuilder n = new StringBuilder();
                    boolean flag = true;
                    input += '#';
                    for (int i = 0; i < input.length(); ++i) {
                        if (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.')
                            n.append(input.charAt(i));
                        else {
                            num.push(Double.valueOf(n.toString()));
                            n.delete(0, n.length());
                            if (!op.empty() && (op.peek() == '×' || op.peek() == '÷')) {
                                double b = num.pop(), a = num.pop();
                                switch (op.pop()) {
                                    case '+':
                                        num.push(a + b);
                                        break;
                                    case '-':
                                        num.push(a - b);
                                        break;
                                    case '×':
                                        num.push(a * b);
                                        break;
                                    case '÷':
                                        if (b != 0)
                                            num.push(a / b);
                                        else
                                            flag = false;
                                        break;
                                }
                            }
                            op.push(input.charAt(i));
                        }
                    }
                    if (flag) {
                        op.pop();
                        while (!op.empty()) {
                            double b = num.pop(), a = num.pop();
                            switch (op.pop()) {
                                case '+':
                                    num.push(a + b);
                                    break;
                                case '-':
                                    num.push(a - b);
                                    break;
                            }
                        }
                        output = num.peek().toString();
                    } else
                        output = "can not divided by 0";
                    input = input.substring(0, input.length() - 1);
                }
                break;
        }
        Input.setText(input);
        Output.setText(output);
    }
}
