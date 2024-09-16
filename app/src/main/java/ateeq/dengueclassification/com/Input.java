package ateeq.dengueclassification.com;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Input extends AppCompatActivity {

    private String genderValue, ns1Value, iggValue, igmValue, conditionValue, outcomeValue;
    private EditText Age, Wbc, Hct, Platelets, Temperature, Humidity, Wind;
    private Button btnSubmit;
    private TextView resultTextView;

    String url = "https://adeveloper-dengue.hf.space/predict";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input);

        Spinner inputGender = findViewById(R.id.input_gender);
        Spinner inputNs1 = findViewById(R.id.input_ns1);
        Spinner inputIgg = findViewById(R.id.input_igg);
        Spinner inputIgm = findViewById(R.id.input_igm);
        Spinner inputPatientCondition = findViewById(R.id.input_patient_condition);
        Spinner inputPatientOutcome = findViewById(R.id.input_patient_outcome);

        // Initialize views
        Age = findViewById(R.id.input_age);
        Wbc = findViewById(R.id.input_wbc);
        Hct = findViewById(R.id.input_hct);
        Platelets = findViewById(R.id.input_platelets);
        Temperature = findViewById(R.id.input_temperature);
        Humidity = findViewById(R.id.input_humidity);
        Wind = findViewById(R.id.input_wind_speed);

        btnSubmit = findViewById(R.id.btn_submit);

        // Set up ArrayAdapter for Gender Spinner
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this, R.array.gender_options, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputGender.setAdapter(genderAdapter);

        // Set up spinner listener for gender
        inputGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                genderValue = String.valueOf(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                genderValue = "1"; // Default value if nothing is selected
            }
        });

        // Set up ArrayAdapter for NS1 Spinner
        ArrayAdapter<CharSequence> ns1Adapter = ArrayAdapter.createFromResource(this, R.array.positive_negative_options, android.R.layout.simple_spinner_item);
        ns1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputNs1.setAdapter(ns1Adapter);

        // Set up spinner listener for NS1
        inputNs1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ns1Value = position == 0 ? "0" : "1"; // Assuming 0 for "Negative" and 1 for "Positive"
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ns1Value = "0"; // Default value if nothing is selected
            }
        });

        // Set up ArrayAdapter for IGG Spinner
        ArrayAdapter<CharSequence> iggAdapter = ArrayAdapter.createFromResource(this, R.array.positive_negative_options, android.R.layout.simple_spinner_item);
        iggAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputIgg.setAdapter(iggAdapter);

        // Set up spinner listener for IGG
        inputIgg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                iggValue = position == 0 ? "0" : "1"; // Assuming 0 for "Negative" and 1 for "Positive"
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                iggValue = "0"; // Default value if nothing is selected
            }
        });

        // Set up ArrayAdapter for IGM Spinner
        ArrayAdapter<CharSequence> igmAdapter = ArrayAdapter.createFromResource(this, R.array.positive_negative_options, android.R.layout.simple_spinner_item);
        igmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputIgm.setAdapter(igmAdapter);

        // Set up spinner listener for IGM
        inputIgm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                igmValue = position == 0 ? "0" : "1"; // Assuming 0 for "Negative" and 1 for "Positive"
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                igmValue = "0"; // Default value if nothing is selected
            }
        });

        // Set up ArrayAdapter for Patient Condition Spinner
        ArrayAdapter<CharSequence> conditionAdapter = ArrayAdapter.createFromResource(this, R.array.patient_condition_options, android.R.layout.simple_spinner_item);
        conditionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputPatientCondition.setAdapter(conditionAdapter);

        // Set up spinner listener for Patient Condition
        inputPatientCondition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                conditionValue = position == 0 ? "0" : "1"; // Assuming 0 for "Unstable" and 1 for "Stable"
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                conditionValue = "0"; // Default value if nothing is selected
            }
        });

        // Set up ArrayAdapter for Patient Outcome Spinner
        ArrayAdapter<CharSequence> outcomeAdapter = ArrayAdapter.createFromResource(this, R.array.patient_outcome_options, android.R.layout.simple_spinner_item);
        outcomeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputPatientOutcome.setAdapter(outcomeAdapter);

        // Set up spinner listener for Patient Outcome
        inputPatientOutcome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                outcomeValue = position == 0 ? "0" : "1"; // Assuming 0 for "Not Discharged" and 1 for "Discharged"
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                outcomeValue = "0"; // Default value if nothing is selected
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String age = Age.getText().toString();
                String wbc = Wbc.getText().toString();
                String hct = Hct.getText().toString();
                String platelets = Platelets.getText().toString();
                String temperature = Temperature.getText().toString();
                String humidity = Humidity.getText().toString();
                String wind = Wind.getText().toString();

                if (age.isEmpty()) {
                    Age.setError("Age is required");
                    Age.requestFocus();
                    return;
                }
                if (wbc.isEmpty()) {
                    Wbc.setError("Wbc is required");
                    Wbc.requestFocus();
                    return;
                }
                if (hct.isEmpty()) {
                    Hct.setError("Hct is required");
                    Hct.requestFocus();
                    return;
                }
                if (platelets.isEmpty()) {
                    Platelets.setError("Platelets is required");
                    Platelets.requestFocus();
                    return;
                }
                if (temperature.isEmpty()) {
                    Temperature.setError("Temperature is required");
                    Temperature.requestFocus();
                    return;
                }
                if (humidity.isEmpty()) {
                    Humidity.setError("Humidity is required");
                    Humidity.requestFocus();
                    return;
                }
                if (wind.isEmpty()) {
                    Wind.setError("Wind is required");
                    Wind.requestFocus();
                    return;
                }
                resultTextView = findViewById(R.id.result_text_view);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Pass the prediction result to the ResultActivity
                                resultTextView.setText(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Input.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("age", age);
                        params.put("gender", genderValue);
                        params.put("ns1", ns1Value);
                        params.put("igg", iggValue);
                        params.put("igm", igmValue);
                        params.put("wbc", wbc);
                        params.put("hct", hct);
                        params.put("platelets", platelets);
                        params.put("condition", conditionValue);
                        params.put("outcome", outcomeValue);
                        params.put("temperature", temperature);
                        params.put("humidity", humidity);
                        params.put("wind", wind);
                        return params;
                    }
                };

                // Add the request to the RequestQueue.
                Volley.newRequestQueue(Input.this).add(stringRequest);
            }
        });
    }
}
