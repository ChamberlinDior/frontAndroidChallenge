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

public class ShipmentCreationActivity extends AppCompatActivity {

    private EditText etTypeColis, etPoids, etDimensions, etAdresseDepart, etAdresseArrivee, etInstructionsSpecifiques;
    private Button btnSubmit;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipment_creation);

        // Liaison avec les composants XML
        etTypeColis = findViewById(R.id.etTypeColis);
        etPoids = findViewById(R.id.etPoids);
        etDimensions = findViewById(R.id.etDimensions);
        etAdresseDepart = findViewById(R.id.etAdresseDepart);
        etAdresseArrivee = findViewById(R.id.etAdresseArrivee);
        etInstructionsSpecifiques = findViewById(R.id.etInstructionsSpecifiques);
        btnSubmit = findViewById(R.id.btnSubmit);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        // Gestion du clic sur le bouton de soumission
        btnSubmit.setOnClickListener(v -> createShipment());
    }

    private void createShipment() {
        // Récupération des données utilisateur
        String typeColis = etTypeColis.getText().toString().trim();
        String poids = etPoids.getText().toString().trim();
        String dimensions = etDimensions.getText().toString().trim();
        String adresseDepart = etAdresseDepart.getText().toString().trim();
        String adresseArrivee = etAdresseArrivee.getText().toString().trim();
        String instructionsSpecifiques = etInstructionsSpecifiques.getText().toString().trim();

        // Validation des champs
        if (typeColis.isEmpty() || poids.isEmpty() || dimensions.isEmpty() || adresseDepart.isEmpty() || adresseArrivee.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs obligatoires.", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        try {
            // Formatage de la date actuelle pour le backend
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            String formattedDate = dateFormat.format(new Date());

            // Création de l'objet CommandDTO
            CommandDTO newCommand = new CommandDTO();
            newCommand.setClientId(1L); // Remplacez par l'ID réel du client connecté
            newCommand.setTypeColis(typeColis);
            newCommand.setPoids(Double.parseDouble(poids));
            newCommand.setDimensions(Double.parseDouble(dimensions));
            newCommand.setAdresseDepart(adresseDepart);
            newCommand.setAdresseArrivee(adresseArrivee);
            newCommand.setInstructionsSpecifiques(instructionsSpecifiques);
            newCommand.setStatutCommande("EN_ATTENTE");
            newCommand.setDateLivraisonPrevue(formattedDate); // Ajout de la date formatée

            // Appel à l'API via Retrofit
            ClientApiService apiService = ApiClient.getClient().create(ClientApiService.class);
            Call<CommandDTO> call = apiService.createCommand(newCommand);

            call.enqueue(new Callback<CommandDTO>() {
                @Override
                public void onResponse(Call<CommandDTO> call, Response<CommandDTO> response) {
                    progressBar.setVisibility(View.GONE);
                    if (response.isSuccessful()) {
                        Toast.makeText(ShipmentCreationActivity.this, "Commande créée avec succès !", Toast.LENGTH_SHORT).show();
                        finish(); // Retour à l'activité précédente
                    } else {
                        Toast.makeText(ShipmentCreationActivity.this, "Erreur lors de la création de la commande.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CommandDTO> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ShipmentCreationActivity.this, "Erreur de connexion.", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Erreur de formatage de la date.", Toast.LENGTH_SHORT).show();
        }
    }
}
