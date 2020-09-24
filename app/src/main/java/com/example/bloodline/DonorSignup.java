package com.example.bloodline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DonorSignup extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText nameEt, cityEt,  passwordEt, mobileEt,emailEt;
    Button submitButton;
    FirebaseAuth mfirebaseauth;
    FirebaseFirestore db;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_signup);
        mfirebaseauth= FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        nameEt = findViewById(R.id.name);
        cityEt = findViewById(R.id.city);

        passwordEt = findViewById(R.id.password);
        mobileEt = findViewById(R.id.number);
        submitButton = findViewById(R.id.submit_button);
        emailEt=findViewById(R.id.email);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name, city,email, password, mobile;
                name = nameEt.getText().toString();
                city = cityEt.getText().toString();
                password = passwordEt.getText().toString();
                mobile = mobileEt.getText().toString();
                email=emailEt.getText().toString();
                if (isValid(name, city, password, mobile,email)) {
                mfirebaseauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(DonorSignup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(DonorSignup.this,"Signup Unsuccessful",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            userid=mfirebaseauth.getCurrentUser().getUid();
                         DocumentReference documentReference= db.collection("users").document(userid);
                         Map<String,Object> users=new HashMap<>();
                         users.put("user_type","Donor");
                         users.put("Name",name);
                         users.put("city",city);
                         users.put("password",password);
                         users.put("mobile",mobile);
                         users.put("email",email);

                         documentReference.set(users).addOnSuccessListener(new OnSuccessListener<Void>() {
                             @Override
                             public void onSuccess(Void aVoid) {
                                 Log.d(TAG,"OnSuccess: User profile is created");
                                  startActivity(new Intent(DonorSignup.this,home.class));
                             }
                         });

                        }
                    }
                });
                }
                else{
                    Toast.makeText(DonorSignup.this,"sign up unsuccessful ",Toast.LENGTH_SHORT).show();
                }
    }
});
    }
    private boolean isValid(String name, String city, String password,
                            String mobile,String email) {

        if (name.isEmpty()) {
            nameEt.setError("Name is empty");
            return false;
        } else if (city.isEmpty()) {
            cityEt.setError("City name is required");
            return false;
        }
        else if (mobile.length() != 10) {
            mobileEt.setError("Invalid mobile number, number should be of 10 digits");
            return false;
        }
        else if (email.isEmpty()) {
            emailEt.setError("email id can't be empty");
            return false;
        }
        else if (password.isEmpty()) {
            passwordEt.setError("password can't be empty");
            return false;
        }
        else if(password.isEmpty() && email.isEmpty())
        {
            Toast.makeText(DonorSignup.this,"multiple are empty",Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}