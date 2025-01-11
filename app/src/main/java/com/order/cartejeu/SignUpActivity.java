package com.order.cartejeu;

import android.os.Bundle;
import android.util.Log;
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

public class SignUpActivity extends AppCompatActivity {

    private EditText etNom, etPrenom, etEmail, etTelephone, etPseudo, etMotDePasse;
    private Button btnSignUp;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etNom = findViewById(R.id.etNom);
        etPrenom = findViewById(R.id.etPrenom);
        etEmail = findViewById(R.id.etEmail);
        etTelephone = findViewById(R.id.etTelephone);
        etPseudo = findViewById(R.id.etPseudo);
        etMotDePasse = findViewById(R.id.etMotDePasse);
        btnSignUp = findViewById(R.id.btnSignUp);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        btnSignUp.setOnClickListener(v -> signUp());
    }

    private void signUp() {
        String nom = etNom.getText().toString().trim();
        String prenom = etPrenom.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String telephone = etTelephone.getText().toString().trim();
        String pseudo = etPseudo.getText().toString().trim();
        String motDePasse = etMotDePasse.getText().toString().trim();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || telephone.isEmpty() || pseudo.isEmpty() || motDePasse.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        Client newClient = new Client();
        newClient.setNom(nom);
        newClient.setPrenom(prenom);
        newClient.setEmail(email);
        newClient.setNumeroTelephone(telephone);
        newClient.setPseudo(pseudo);
        newClient.setMotDePasse(motDePasse);
        newClient.setStatutCompte("ACTIF");

        ClientApiService clientApiService = ApiClient.getClient().create(ClientApiService.class);
        Call<Client> call = clientApiService.createClient(newClient);

        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Inscription réussie !", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(SignUpActivity.this, "Erreur lors de l'inscription.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(SignUpActivity.this, "Erreur de connexion.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
