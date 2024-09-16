package ateeq.dengueclassification.com;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot extends AppCompatActivity {

    private EditText editTextEmail;
    private FirebaseAuth firebaseAuth;
    private TextView appName;
    private CardView cardView;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot);

        appName = findViewById(R.id.appName); // Replace with the correct ID
        cardView = findViewById(R.id.cardView); // Replace with the correct ID
        editTextEmail = findViewById(R.id.editTextEmail);
        resetButton = findViewById(R.id.buttonResetPassword);


        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        appName.startAnimation(fadeIn);
        cardView.startAnimation(fadeIn);
        resetButton.startAnimation(fadeIn);

        firebaseAuth = FirebaseAuth.getInstance();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });

    }

    private void resetPassword() {
        String email = editTextEmail.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter your registered email", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Password reset email sent successfully
                            Toast.makeText(Forgot.this, "Password reset email sent. Check your email inbox.", Toast.LENGTH_LONG).show();
                            finish(); // Close the activity
                        } else {
                            // Password reset email sending failed
                            Toast.makeText(Forgot.this, "Failed to send reset email. Please try again later.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}