package com.order.cartejeu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.order.cartejeu.api.ApiClient;
import com.order.cartejeu.api.ClientApiService;
import com.order.cartejeu.dto.CommandDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommandActivity extends AppCompatActivity {

    private EditText etTypeColis, etPoids, etDimensions, etAdresseDepart, etAdresseArrivee, etInstructionsSpecifiques, etDateLivraison;
    private Button btnSubmitCommand;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);

        // Liaison avec les composants XML
        etTypeColis = findViewById(R.id.etTypeColis);
        etPoids = findViewById(R.id.etPoids);
        etDimensions = findViewById(R.id.etDimensions);
        etAdresseDepart = findViewById(R.id.etAdresseDepart);
        etAdresseArrivee = findViewById(R.id.etAdresseArrivee);
        etInstructionsSpecifiques = findViewById(R.id.etInstructionsSpecifiques);
        etDateLivraison = findViewById(R.id.etDateLivraison); // Champ pour la date de livraison prévue
        btnSubmitCommand = findViewById(R.id.btnSubmitCommand);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        // Gestion du clic sur le bouton pour créer une commande
        btnSubmitCommand.setOnClickListener(v -> createCommand());
    }

    private void createCommand() {
        // Récupération des données utilisateur
        String typeColis = etTypeColis.getText().toString().trim();
        String poids = etPoids.getText().toString().trim();
        String dimensions = etDimensions.getText().toString().trim();
        String adresseDepart = etAdresseDepart.getText().toString().trim();
        String adresseArrivee = etAdresseArrivee.getText().toString().trim();
        String instructionsSpecifiques = etInstructionsSpecifiques.getText().toString().trim();
        String dateLivraisonStr = etDateLivraison.getText().toString().trim();

        if (typeColis.isEmpty() || poids.isEmpty() || dimensions.isEmpty() || adresseDepart.isEmpty() || adresseArrivee.isEmpty() || dateLivraisonStr.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs obligatoires.", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        try {
            // Format de la date
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Date dateLivraisonPrevue = inputFormat.parse(dateLivraisonStr);

            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            String formattedDate = outputFormat.format(dateLivraisonPrevue);

            // Récupérer l'ID du client à partir des SharedPreferences
            long clientId = getSharedPreferences("UserSession", MODE_PRIVATE).getLong("ClientId", -1);
            if (clientId == -1) {
                Toast.makeText(this, "Impossible de récupérer l'identité du client.", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                return;
            }

            // Création du DTO
            CommandDTO newCommand = new CommandDTO();
            newCommand.setClientId(clientId);
            newCommand.setTypeColis(typeColis);
            newCommand.setPoids(Double.parseDouble(poids));
            newCommand.setDimensions(Double.parseDouble(dimensions));
            newCommand.setAdresseDepart(adresseDepart);
            newCommand.setAdresseArrivee(adresseArrivee);
            newCommand.setInstructionsSpecifiques(instructionsSpecifiques);
            newCommand.setStatutCommande("EN_ATTENTE");
            newCommand.setDateLivraisonPrevue(formattedDate);

            // Appel à l'API
            ClientApiService clientApiService = ApiClient.getClient().create(ClientApiService.class);
            Call<CommandDTO> call = clientApiService.createCommand(newCommand);

            call.enqueue(new Callback<CommandDTO>() {
                @Override
                public void onResponse(Call<CommandDTO> call, Response<CommandDTO> response) {
                    progressBar.setVisibility(View.GONE);
                    if (response.isSuccessful()) {
                        Toast.makeText(CommandActivity.this, "Commande créée avec succès !", Toast.LENGTH_SHORT).show();
                        clearFields();
                    } else {
                        Toast.makeText(CommandActivity.this, "Erreur : " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CommandDTO> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(CommandActivity.this, "Erreur de connexion : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Format de date invalide. Utilisez 'dd/MM/yyyy'.", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        etTypeColis.setText("");
        etPoids.setText("");
        etDimensions.setText("");
        etAdresseDepart.setText("");
        etAdresseArrivee.setText("");
        etInstructionsSpecifiques.setText("");
        etDateLivraison.setText("");
    }
}
