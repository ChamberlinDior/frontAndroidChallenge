package com.order.cartejeu.api;

import com.order.cartejeu.dto.CommandDTO;
import com.order.cartejeu.model.Client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ClientApiService {

    // Récupérer tous les clients
    @GET("api/clients")
    Call<List<Client>> getAllClients();

    // Récupérer un client par ID
    @GET("api/clients/{id}")
    Call<Client> getClientById(@Path("id") Long id);

    // Créer un nouveau client
    @POST("api/clients")
    Call<Client> createClient(@Body Client client);

    // Mettre à jour un client
    @PUT("api/clients/{id}")
    Call<Client> updateClient(@Path("id") Long id, @Body Client client);

    // Supprimer un client
    @DELETE("api/clients/{id}")
    Call<Void> deleteClient(@Path("id") Long id);

    // Rechercher un client par nom ou prénom
    @GET("api/clients/search")
    Call<List<Client>> searchClientsByName(@Query("query") String query);

    // Rechercher un client par email
    @GET("api/clients/email/{email}")
    Call<Client> getClientByEmail(@Path("email") String email);

    // Rechercher un client par pseudo
    @GET("api/clients/pseudo/{pseudo}")
    Call<Client> getClientByPseudo(@Path("pseudo") String pseudo);

    // Rechercher les clients par statut du compte
    @GET("api/clients/status/{statutCompte}")
    Call<List<Client>> getClientsByStatutCompte(@Path("statutCompte") String statutCompte);

    // Rechercher les clients par ville
    @GET("api/clients/ville/{ville}")
    Call<List<Client>> getClientsByVille(@Path("ville") String ville);

    // Créer une commande
    @POST("api/commands")
    Call<CommandDTO> createCommand(@Body CommandDTO commandDTO);
}
