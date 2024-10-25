package com.example.healthcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private EditText username_et, password_et;
    private Button login_button;
    private TextView to_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //editext
        username_et=findViewById(R.id.username_login);
        password_et=findViewById(R.id.passsword_login);


        login_button=findViewById(R.id.login_button);
        to_register=findViewById(R.id.to_register);


        login_button.setOnClickListener(v->{
            String username=username_et.getText().toString().trim();
            String password=password_et.getText().toString().trim();

            if(username.isEmpty()&&password.isEmpty()){
                username_et.setError("Please enter username");
                password_et.setError("Please enter password");
            }else if(username.isEmpty()){
                username_et.setError("Please enter username");
            }else if(password.isEmpty()){
                password_et.setError("Please enter password");
            }else{
                DataBase db=new DataBase(this,"healthcare",null,1);
                boolean auth=db.login(username,password);
                if(auth) {
                    SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username",username);
                    editor.apply();
                    Intent i = new Intent(this, HomeActivity.class);
                    startActivity(i);
                }else{
                   password_et.setError("invalid credentials");
                   password_et.setText("");
                   password_et.requestFocus();
                }
            }
        });


        to_register.setOnClickListener(v->{
            Intent i=new Intent(this,Register.class);
            startActivity(i);
        });
    }
}