package com.order.cartejeu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bouton pour accéder à la liste des clients
        Button btnViewClients = findViewById(R.id.btnViewClients);
        btnViewClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ClientListActivity.class);
                startActivity(intent);
            }
        });

        // Bouton pour accéder à la création de clients
        Button btnCreateClient = findViewById(R.id.btnCreateClient);
        btnCreateClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ClientCreationActivity.class);
                startActivity(intent);
            }
        });

        // Bouton pour créer une commande d’envoi de colis
        Button btnCreateShipment = findViewById(R.id.btnCreateShipment);
        btnCreateShipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShipmentCreationActivity.class);
                startActivity(intent);
            }
        });
    }
}
