package com.order.cartejeu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.order.cartejeu.api.ApiClient;
import com.order.cartejeu.api.ClientApiService;
import com.order.cartejeu.model.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientCreationActivity extends AppCompatActivity {

    private EditText etNom, etPrenom, etEmail, etTelephone, etPseudo, etMotDePasse, etAdresseExpedition, etAdresseDestination, etTypeColis, etInstructionsSpeciales;
    private Spinner spStatutCompte;
    private Button btnSubmit;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_creation);

        // Liaison avec les composants XML
        etNom = findViewById(R.id.etNom);
        etPrenom = findViewById(R.id.etPrenom);
        etEmail = findViewById(R.id.etEmail);
        etTelephone = findViewById(R.id.etTelephone);
        etPseudo = findViewById(R.id.etPseudo);
        etMotDePasse = findViewById(R.id.etMotDePasse); // Champ mot de passe
        spStatutCompte = findViewById(R.id.spStatutCompte); // Spinner pour le statut du compte
        etAdresseExpedition = findViewById(R.id.etAdresseExpedition);
        etAdresseDestination = findViewById(R.id.etAdresseDestination);
        etTypeColis = findViewById(R.id.etTypeColis);
        etInstructionsSpeciales = findViewById(R.id.etInstructionsSpeciales);
        btnSubmit = findViewById(R.id.btnSubmit);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        // Configuration du Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.statut_compte_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStatutCompte.setAdapter(adapter);

        // Gestion du clic du bouton
        btnSubmit.setOnClickListener(v -> createClient());
    }

    private void createClient() {
        // Récupération des données utilisateur
        String nom = etNom.getText().toString().trim();
        String prenom = etPrenom.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String telephone = etTelephone.getText().toString().trim();
        String pseudo = etPseudo.getText().toString().trim();
        String motDePasse = etMotDePasse.getText().toString().trim();
        String statutCompte = spStatutCompte.getSelectedItem().toString(); // Récupération de la sélection
        String adresseExpedition = etAdresseExpedition.getText().toString().trim();
        String adresseDestination = etAdresseDestination.getText().toString().trim();
        String typeColis = etTypeColis.getText().toString().trim();
        String instructionsSpeciales = etInstructionsSpeciales.getText().toString().trim();

        // Validation des champs
        if (!validateInputs(nom, prenom, email, telephone, pseudo, motDePasse, statutCompte)) {
            return;
        }

        // Affichage de la barre de progression
        progressBar.setVisibility(View.VISIBLE);

        // Création de l'objet Client
        Client newClient = new Client();
        newClient.setNom(nom);
        newClient.setPrenom(prenom);
        newClient.setEmail(email);
        newClient.setNumeroTelephone(telephone);
        newClient.setPseudo(pseudo);
        newClient.setMotDePasse(motDePasse);
        newClient.setStatutCompte(statutCompte);
        newClient.setAdresseExpedition(adresseExpedition);
        newClient.setAdresseDestination(adresseDestination);
        newClient.setTypeColis(typeColis);
        newClient.setInstructionsSpeciales(instructionsSpeciales);

        // Appel à l'API via Retrofit
        ClientApiService clientApiService = ApiClient.getClient().create(ClientApiService.class);
        Call<Client> call = clientApiService.createClient(newClient);

        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    Toast.makeText(ClientCreationActivity.this, "Client créé avec succès", Toast.LENGTH_SHORT).show();
                    clearFields(); // Nettoyer les champs après création

                    // Rediriger vers MapActivity
                    Intent intent = new Intent(ClientCreationActivity.this, MapActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    handleApiError(response);
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.e("API_ERROR", "Erreur : " + t.getMessage());
                Toast.makeText(ClientCreationActivity.this, "Échec de la connexion au serveur", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateInputs(String nom, String prenom, String email, String telephone, String pseudo, String motDePasse, String statutCompte) {
        return validateField(etNom, "Le champ Nom est requis.") &&
                validateField(etPrenom, "Le champ Prénom est requis.") &&
                validateField(etEmail, "Veuillez entrer une adresse email valide.") &&
                validateField(etTelephone, "Le champ Téléphone est requis.") &&
                validateField(etPseudo, "Le champ Pseudo est requis.") &&
                validateField(etMotDePasse, "Le champ Mot de passe est requis.") &&
                !statutCompte.equals("Sélectionnez");
    }

    private boolean validateField(EditText field, String errorMessage) {
        if (field.getText().toString().trim().isEmpty()) {
            field.setError(errorMessage);
            return false;
        }
        return true;
    }

    private void handleApiError(Response<Client> response) {
        String errorMessage = "Erreur inconnue";
        try {
            if (response.errorBody() != null) {
                errorMessage = response.errorBody().string();
            }
        } catch (Exception e) {
            Log.e("API_ERROR", "Erreur de traitement des erreurs API : " + e.getMessage());
        }
        Log.e("API_ERROR", "Erreur API : " + errorMessage);
        Toast.makeText(ClientCreationActivity.this, "Erreur lors de la création : " + errorMessage, Toast.LENGTH_SHORT).show();
    }

    private void clearFields() {
        etNom.setText("");
        etPrenom.setText("");
        etEmail.setText("");
        etTelephone.setText("");
        etPseudo.setText("");
        etMotDePasse.setText("");
        spStatutCompte.setSelection(0); // Réinitialiser le Spinner
        etAdresseExpedition.setText("");
        etAdresseDestination.setText("");
        etTypeColis.setText("");
        etInstructionsSpeciales.setText("");
    }
}
