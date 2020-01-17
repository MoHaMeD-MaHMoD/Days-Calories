package com.example.trackyourhealth.LogInAndSignUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trackyourhealth.MainActivity;
import com.example.trackyourhealth.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogInActivity extends AppCompatActivity {
    FirebaseAuth mAuth;


    @BindView(R.id.emailedittext)
    EditText emailedittext;
    @BindView(R.id.passwordedittext)
    EditText passwordedittext;
    @BindView(R.id.loginbutton)
    Button loginbutton;
    @BindView(R.id.forgetpassButton)
    TextView forgetpassButton;
    @BindView(R.id.GreateUserbutton)
    Button GreateUserbutton;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        progressBar.setVisibility(View.GONE);


    }


    @OnClick(R.id.loginbutton)
    public void onLoginbuttonClicked() {

        String email = emailedittext.getText().toString();
        String password = passwordedittext.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            if (mAuth.getCurrentUser().isEmailVerified()) {
                                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                                startActivity(intent);
                            }

                            // Sign in success, update UI with the signed-in user's information

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    @OnClick(R.id.forgetpassButton)
    public void onForgetpassButtonClicked() {
        Intent intent = new Intent(this, ForgetPasswordActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.GreateUserbutton)
    public void onGreateUserbuttonClicked() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);

    }
}
