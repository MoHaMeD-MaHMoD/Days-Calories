package com.example.trackyourhealth.LogInAndSignUp;

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
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPasswordActivity extends AppCompatActivity {
    FirebaseAuth auth;

    @BindView(R.id.ResetemailPasswordedittext)
    EditText ResetemailPasswordedittext;
    @BindView(R.id.ResetemailPasswordButton)
    Button ResetemailPasswordButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar) ;
        progressBar.setVisibility(View.GONE);

        ButterKnife.bind(this);
        auth = FirebaseAuth.getInstance();

    }

    @OnClick(R.id.ResetemailPasswordButton)
    public void onViewClicked() {
        String emailAddress = ResetemailPasswordedittext.getText().toString();

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Log.d(TAG, "Email sent.");
                            Toast.makeText(ForgetPasswordActivity.this, "Check your Email For a Link To Reset Your Password ", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}
