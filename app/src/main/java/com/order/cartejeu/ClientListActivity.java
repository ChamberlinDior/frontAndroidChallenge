package com.order.cartejeu;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.order.cartejeu.api.ApiClient;
import com.order.cartejeu.api.ClientApiService;
import com.order.cartejeu.model.Client;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientListActivity extends AppCompatActivity {

    private ListView listViewClients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);

        listViewClients = findViewById(R.id.listViewClients);

        fetchClients();
    }

    private void fetchClients() {
        ClientApiService clientApiService = ApiClient.getClient().create(ClientApiService.class);
        Call<List<Client>> call = clientApiService.getAllClients();

        call.enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Client> clients = response.body();
                    ArrayAdapter<Client> adapter = new ArrayAdapter<>(
                            ClientListActivity.this,
                            android.R.layout.simple_list_item_1,
                            clients
                    );
                    listViewClients.setAdapter(adapter);
                } else {
                    Log.e("API_ERROR", "Erreur lors de la récupération des clients");
                }
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Log.e("API_ERROR", "Erreur : " + t.getMessage());
            }
        });
    }
}
