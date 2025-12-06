package com.example.lr8;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText etRus, etMath, etInform, etSocial, etPhys, etChem, etEng, etGeo;
    TextView tvResult;
    Button btnCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etRus = findViewById(R.id.etRus);
        etMath = findViewById(R.id.etMath);
        etInform = findViewById(R.id.etInform);
        etPhys = findViewById(R.id.etPhys);
        etChem = findViewById(R.id.etChem);
        etSocial = findViewById(R.id.etSocial);
        etEng = findViewById(R.id.etEng);
        etGeo = findViewById(R.id.etGeo);

        tvResult = findViewById(R.id.tvResult);
        btnCalc = findViewById(R.id.btnCalc);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://nti.urfu.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        btnCalc.setOnClickListener(v -> {
            hideKeyboard();

            int rus = getInt(etRus);
            int math = getInt(etMath);

            if (rus == 0 || math == 0) {
                Toast.makeText(this, "Введите баллы по Русскому и Математике!", Toast.LENGTH_SHORT).show();
                return;
            }

            int inform = getInt(etInform);
            int phys = getInt(etPhys);
            int chem = getInt(etChem);
            int social = getInt(etSocial);
            int eng = getInt(etEng);
            int geo = getInt(etGeo);

            UserScores request = new UserScores(math, rus, inform, social, phys, chem, eng, geo);

            tvResult.setText("Поиск направлений...");

            apiService.calculate(request).enqueue(new Callback<List<Specialty>>() {
                @Override
                public void onResponse(Call<List<Specialty>> call, Response<List<Specialty>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<Specialty> list = response.body();
                        StringBuilder sb = new StringBuilder();

                        if (list.isEmpty()) {
                            sb.append("К сожалению, с такими баллами подходящих направлений не найдено.");
                        } else {
                            sb.append("Вам подходят следующие направления (").append(list.size()).append("):\n\n");
                            for (Specialty s : list) {
                                sb.append(s.getDetails()).append("\n-----------------\n");
                            }
                        }
                        tvResult.setText(sb.toString());
                    } else {
                        tvResult.setText("Ошибка сервера: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<Specialty>> call, Throwable t) {
                    tvResult.setText("Ошибка сети. Проверьте подключение к интернету.\n" + t.getMessage());
                }
            });
        });
    }

    private int getInt(EditText et) {
        String s = et.getText().toString().trim();
        if (s.isEmpty()) return 0;
        try {
            int val = Integer.parseInt(s);
            if (val > 100) return 100;
            return val;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}