package com.order.cartejeu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.order.cartejeu.api.ApiClient;
import com.order.cartejeu.api.ClientApiService;
import com.order.cartejeu.model.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etPseudo, etMotDePasse;
    private Button btnLogin, btnSignUp;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etPseudo = findViewById(R.id.etPseudo);
        etMotDePasse = findViewById(R.id.etMotDePasse);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        btnLogin.setOnClickListener(v -> login());
        btnSignUp.setOnClickListener(v -> navigateToSignUp());
    }

    private void login() {
        String pseudo = etPseudo.getText().toString().trim();
        String motDePasse = etMotDePasse.getText().toString().trim();

        if (pseudo.isEmpty() || motDePasse.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        ClientApiService clientApiService = ApiClient.getClient().create(ClientApiService.class);
        Call<Client> call = clientApiService.getClientByPseudo(pseudo);

        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    Client client = response.body();
                    if (client.getMotDePasse().equals(motDePasse)) {
                        // Enregistrer l'ID du client dans les SharedPreferences
                        getSharedPreferences("UserSession", MODE_PRIVATE)
                                .edit()
                                .putLong("ClientId", client.getId())
                                .apply();

                        Toast.makeText(LoginActivity.this, "Connexion réussie !", Toast.LENGTH_SHORT).show();
                        navigateToMain();
                    } else {
                        Toast.makeText(LoginActivity.this, "Mot de passe incorrect.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Pseudo introuvable.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, "Erreur de connexion.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToMain() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void navigateToSignUp() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}
