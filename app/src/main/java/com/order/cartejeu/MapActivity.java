package com.order.cartejeu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Bouton de recherche
        Button btnSearch = findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(v ->
                Toast.makeText(this, "Recherche en cours...", Toast.LENGTH_SHORT).show()
        );

        // Bouton pour afficher les informations
        Button btnShowInfo = findViewById(R.id.btn_show_info);
        btnShowInfo.setOnClickListener(v -> showInfoPopup());
    }

    private void showInfoPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Informations sur le colis");
        builder.setMessage("Nom du client: Jean Dupont\nAdresse: 123 Rue de Paris\nType de colis: Document");
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.show();
    }
}
