package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.RetrofitClient;
import com.example.myapplication.UsuarioService;
import com.example.myapplication.LoginRequest;
import com.example.myapplication.LoginResponse;
import com.example.myapplication.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, senhaEditText;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.email);
        senhaEditText = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnCadastrar);

        btnLogin.setOnClickListener(view -> {
            String email = emailEditText.getText().toString().trim();
            String senha = senhaEditText.getText().toString().trim();

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            UsuarioService service = RetrofitClient.getRetrofitInstance().create(UsuarioService.class);
            LoginRequest request = new LoginRequest(email, senha);

            service.login(request).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().isSucesso()) {
                        Usuario usuario = response.body().getUsuario();

                        Toast.makeText(LoginActivity.this, "Bem-vindo, " + usuario.getNome(), Toast.LENGTH_SHORT).show();

                        // Redirecionar para a Carteira
                        //Intent intent = new Intent(LoginActivity.this, CarteiraActivity.class);
                        //intent.putExtra("usuarioNome", usuario.getNome());
                        //startActivity(intent);
                        //finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Email ou senha inválidos", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}
