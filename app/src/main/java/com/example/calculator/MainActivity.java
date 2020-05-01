package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Stack;

import com.example.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity  {

ActivityMainBinding binding;


    @Override
        protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

    }
    public void res(View view)
    { String x = binding.result.getText().toString();

        switch (view.getId())
        {
            case R.id.button0 :

                    binding.result.setText(x+"0");
            break;
            case R.id.button1 :

                binding.result.setText(x+"1");
                break;
            case R.id.button2 :

                binding.result.setText(x+"2");
                break;
            case R.id.button3 :

                binding.result.setText(x+"3");
                break;
            case R.id.button4 :

                binding.result.setText(x+"4");
                break;
            case R.id.button5 :

                binding.result.setText(x+"5");
                break;
            case R.id.button6 :

                binding.result.setText(x+"6");
                break;
            case R.id.button7 :

                binding.result.setText(x+"7");
                break;
            case R.id.button8 :

                binding.result.setText(x+"8");
                break;
            case R.id.button9 :

                binding.result.setText(x+"9");
                break;
            case R.id.add :

                binding.result.setText(x+"+");
                break;
            case R.id.sub :

                binding.result.setText(x+"-");
                break;
            case R.id.equal :
                String a = binding.result.getText().toString();
                int c = evaluate(a);
                binding.result.setText(String.valueOf(c));
                break;
            case R.id.mult :

                binding.result.setText(x+"*");
                break;
            case R.id.clear :

                binding.result.setText("");
                break;

            case R.id.div :

                binding.result.setText(x+"/");
                break;

            case R.id.cut :
                String l = binding.result.getText().toString();
                l = l.substring(0,l.length()-1);
                binding.result.setText(l);
                break;
        }

    }



    public int evaluate(String expression){
        Stack<Integer> numbers = new Stack<>();

        Stack<Character> operations = new Stack<>();
        for(int i=0; i<expression.length();i++) {
            char c = expression.charAt(i);
            if(Character.isDigit(c)){
                int num = 0;
                while (Character.isDigit(c)) {
                    num = num*10 + (c-'0');
                    i++;
                    if(i < expression.length())
                        c = expression.charAt(i);
                    else
                        break;
                }
                i--;
                numbers.push(num);
            }else if(c=='('){
                operations.push(c);
            }
            else if(c==')') {
                while(operations.peek()!='('){
                    int output = performOperation(numbers, operations);
                    numbers.push(output);
                }
                operations.pop();
            }

            else if(isOperator(c)){

                while(!operations.isEmpty() && precedence(c)<precedence(operations.peek())){
                    int output = performOperation(numbers, operations);
                    numbers.push(output);
                }
                operations.push(c);
            }
        }


        while(!operations.isEmpty()){
            int output = performOperation(numbers, operations);
            numbers.push(output);
        }
        return numbers.pop();
    }

    static int precedence(char c){
        switch (c){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    public int performOperation(Stack<Integer> numbers, Stack<Character> operations) {
        int a = numbers.pop();
        int b = numbers.pop();
        char operation = operations.pop();
        switch (operation) {
            case '+':
                return a + b;
            case '-':
                return b - a;
            case '*':
                return a * b;
            case '/':
                if (a == 0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                return b / a;
        }
        return 0;
    }

    public boolean isOperator(char c){
        return (c=='+'||c=='-'||c=='/'||c=='*'||c=='^');
    }

}

