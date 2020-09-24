package com.example.bloodline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    EditText  passwordEt,emailEt;
    Button login,signup;

    FirebaseAuth mfirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthstatelistner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
     mfirebaseAuth=FirebaseAuth.getInstance();
     emailEt=findViewById(R.id.email);
     passwordEt=findViewById(R.id.password);
     login=findViewById(R.id.submit_button);
     signup=findViewById(R.id.sign_up_text);
     mAuthstatelistner=new FirebaseAuth.AuthStateListener() {
         @Override
         public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
             FirebaseUser mfirebaseuser=mfirebaseAuth.getCurrentUser();
             if(mfirebaseuser!=null){
                 Toast.makeText(Login.this,"login successful",Toast.LENGTH_SHORT).show();
                 Intent intent=new Intent(Login.this,home.class);
                 startActivity(intent);
             }
             else
             {
                 Toast.makeText(Login.this,"login not successful",Toast.LENGTH_SHORT).show();
             }
         }
     };
     login.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String email=emailEt.getText().toString();
             String password=passwordEt.getText().toString();
             if (email.isEmpty()) {
                 emailEt.setError("email id can't be empty");

             }
             else if (password.isEmpty()) {
                 passwordEt.setError("password can't be empty");
                  }
             else if(password.isEmpty() && email.isEmpty())
             {
                 Toast.makeText(Login.this,"multiple are empty",Toast.LENGTH_SHORT).show();
             }
             else if(!(password.isEmpty() && email.isEmpty())){
                 mfirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if(!task.isSuccessful()){
                             Toast.makeText(Login.this,"login unsuccessful please check your credentials",Toast.LENGTH_SHORT).show();
                         }
                         else{
                             Intent i=new Intent(Login.this,home.class);
                             startActivity(i);
                         }

                     }
                 }) ;       }
             else{
                 Toast.makeText(Login.this,"error occured",Toast.LENGTH_SHORT).show();
             }
     }
    });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sign=new Intent(Login.this, ChooseSignUp.class);
                startActivity(sign);
            }
        });
}
protected void onStart(){
        super.onStart();
        mfirebaseAuth.addAuthStateListener(mAuthstatelistner);
}
}