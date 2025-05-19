package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.api.RetrofitClient;
import com.example.myapplication.api.UsuarioService;
import com.example.myapplication.model.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, senhaEditText;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // substitua com o nome real do XML, ex: activity_login

        emailEditText = findViewById(R.id.email);
        senhaEditText = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnCadastrar); // botão de login

        btnLogin.setOnClickListener(view -> {
            String email = emailEditText.getText().toString().trim();
            String senha = senhaEditText.getText().toString().trim();

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            UsuarioService service = RetrofitClient.getRetrofitInstance().create(UsuarioService.class);

            // Este método precisa estar implementado no UsuarioService
            Call<Usuario> call = service.login(email, senha);

            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Toast.makeText(LoginActivity.this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();

                        // Redireciona para a Carteira
                        //Intent intent = new Intent(LoginActivity.this, CarteiraActivity.class);
                        //intent.putExtra("usuarioEmail", email); // envia email, opcional
                        //startActivity(intent);
                        //finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Credenciais inválidas!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}
