package com.example.dict2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity  {
private EditText emailEt,passwordEt1,passwordEt2;
private Button SignUpButton;
private TextView SignIntv;
private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth = FirebaseAuth.getInstance();
        emailEt=findViewById(R.id.email);
        passwordEt1 = findViewById(R.id.password1);
        passwordEt2= findViewById(R.id.password2);
        SignUpButton = findViewById(R.id.register);
        SignIntv= findViewById(R.id.signInTv);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
        SignIntv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void Register(){

        String email = emailEt.getText().toString();
        String password1= passwordEt1.getText().toString();
        String password2= passwordEt2.getText().toString();
        if(TextUtils.isEmpty(email)){

            emailEt.setError("Enter your email");
            return;
        }
       else if(TextUtils.isEmpty(password1)){

            passwordEt1.setError("Enter your password");
            return;
        }
        else if(TextUtils.isEmpty(password2)){

            passwordEt2.setError("confirm your password");
            return;
        }
        else if(!password1.equals(password2)){

            passwordEt2.setError("Different password");
            return;
        }
        else if(password1.length()<4){

            passwordEt1.setError("Length should be > 4");
            return;
        }
        else if(!isValidEmail(email)){

            emailEt.setError("Invalid Email");
            return;
        }
firebaseAuth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful()){
            Toast.makeText(SignUp.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignUp.this,Dashboard.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(SignUp.this, "SignUp failure!", Toast.LENGTH_SHORT).show();

        }
    }
});





















    }
    private Boolean isValidEmail(CharSequence target){

        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());

    }


}