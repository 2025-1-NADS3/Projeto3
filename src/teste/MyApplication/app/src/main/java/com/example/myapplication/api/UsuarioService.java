package com.example.myapplication.api;

import com.example.myapplication.model.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UsuarioService {
    @POST("/usuarios")
    Call<Void> cadastrarUsuario(@Body Usuario usuario);

    Call<Usuario> login(String email, String senha);
}
