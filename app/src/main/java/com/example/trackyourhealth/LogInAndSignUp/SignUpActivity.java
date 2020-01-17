package com.example.trackyourhealth.LogInAndSignUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trackyourhealth.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    @BindView(R.id.emailedittext)
    EditText emailedittext;
    @BindView(R.id.passwordedittext)
    EditText passwordedittext;
    @BindView(R.id.signUpButton)
    Button signUpButton;
    @BindView(R.id.loginbutton)
    Button loginbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar) ;
        progressBar.setVisibility(View.GONE);

        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
    }


    @OnClick(R.id.signUpButton)
    public void onSignUpButtonClicked() {
        String email = emailedittext.getText().toString();
        String password = passwordedittext.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            sendEmailVerification();                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    @OnClick(R.id.loginbutton)
    public void onLoginbuttonClicked() {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);

    }

    public void sendEmailVerification() {
        mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
                    startActivity(intent);


                }

            }
        });
    }
}