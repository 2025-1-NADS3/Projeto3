package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UsuarioService {
    @POST("/usuarios") // ou a rota que criou no seu `server.js`
    Call<Void> cadastrarUsuario(@Body Usuario usuario);

    @POST("/LoginActivity.java")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);


}
