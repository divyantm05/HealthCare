package com.example.healthcare;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.ColorKt;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class Register extends AppCompatActivity {

    private EditText username_et, email_et, password_et, confirm_password_et;
    private Button register_button;
    private TextView to_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        username_et=findViewById(R.id.username_register);
        email_et=findViewById(R.id.email_register);
        password_et=findViewById(R.id.passsword_register);
        confirm_password_et=findViewById(R.id.confirm_passsword_register);

        register_button=findViewById(R.id.register_button);
        to_login=findViewById(R.id.to_login);


        //register button on clck listner
        register_button.setOnClickListener(v->{
            String email=email_et.getText().toString().trim();
            String password=password_et.getText().toString().trim();
            String confirm_password=confirm_password_et.getText().toString().trim();
            String username=username_et.getText().toString().trim();

            if(username.isEmpty()&&email.isEmpty()&&password.isEmpty()&&confirm_password.isEmpty()){
                username_et.setError("Please enter username");
                email_et.setError("Please enter email");
                password_et.setError("Please enter password");
                confirm_password_et.setError("Please enter confirm password");

            }else if(username.isEmpty()){
                username_et.setError("Please enter username");
            } else if (password.isEmpty())  {
                password_et.setError("Please enter password");
            }else if (confirm_password.isEmpty())  {
                confirm_password_et.setError("Please enter confirm password");
            }
            else if(email.isEmpty()){
                email_et.setError("Please enter email");
            }else if(!isValid(password)){
                password_et.setError("invalid password");
                confirm_password_et.setError("invalid password");
                showSnackBar(v,"Password must contain atleast 8 characters, having letter, digits and special symbol");
            }else if(!password.equals(confirm_password)){
                password_et.setError("Password and confirm password must be same");
                confirm_password_et.setError("Password and confirm password must be same");
                confirm_password_et.setText("");
                confirm_password_et.requestFocus();
            }else{
                regiterUser(username,email,password);
            }

        });

        //login button on click listener
        to_login.setOnClickListener(v->{
            Intent i=new Intent(this,LoginActivity.class);
            startActivity(i);
        });

    }

    public static boolean isValid(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if ((c >= 33 && c <= 46) || c == 64) {
                hasSpecialChar = true;
            }

            if (hasLetter && hasDigit && hasSpecialChar) {
                return true;
            }
        }

        return false;
    }

    public void showSnackBar(View v,String msg){
        Snackbar.make(v,msg, Snackbar.LENGTH_LONG)
                .setAction("ok",view -> {
                    password_et.setText("");
                    confirm_password_et.setText("");
                    password_et.requestFocus();
                })
                .show();
    }
    public void regiterUser(String username,String email,String password){
        DataBase db=new DataBase(this,"healthcare",null,1);

        String auth=db.register(username,email,password);
        if(auth.equals("already registered")){
            username_et.setError("user already exits");
            username_et.requestFocus();
        }else if(auth.equals("failed")){
            Toast.makeText(getApplicationContext(),"registration failed",Toast.LENGTH_SHORT).show();
        }else{
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }

    }

}