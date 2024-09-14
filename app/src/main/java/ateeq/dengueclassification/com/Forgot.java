package ateeq.dengueclassification.com;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Forgot extends AppCompatActivity {

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
        resetButton = findViewById(R.id.buttonResetPassword);

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        appName.startAnimation(fadeIn);
        cardView.startAnimation(fadeIn);
        resetButton.startAnimation(fadeIn);


    }
}