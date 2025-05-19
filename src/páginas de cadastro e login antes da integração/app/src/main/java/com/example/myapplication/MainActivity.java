package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.RetrofitClient;
import com.example.myapplication.UsuarioService;
import com.example.myapplication.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText editNome, editCurso, editSemestre, editCPF, editRA, editEmail, editSenha;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Verifique se o layout está com o nome correto

        // Ligando os componentes do XML
        editNome = findViewById(R.id.editNome);
        editCurso = findViewById(R.id.editCurso);
        editSemestre = findViewById(R.id.editSemestre);
        editCPF = findViewById(R.id.editCPF);
        editRA = findViewById(R.id.editRA);
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(view -> {
            // Criando e preenchendo o objeto Usuario
            Usuario usuario = new Usuario();
            usuario.setNome(editNome.getText().toString());
            usuario.setCurso(editCurso.getText().toString());

            try {
                usuario.setSemestre(Integer.parseInt(editSemestre.getText().toString()));
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Semestre inválido", Toast.LENGTH_SHORT).show();
                return;
            }

            usuario.setCpf(editCPF.getText().toString());
            usuario.setRa(editRA.getText().toString());
            usuario.setDataNascimento("2000-01-01"); // Ajuste conforme necessário
            usuario.setEmail(editEmail.getText().toString());
            usuario.setSenha(editSenha.getText().toString());

            // Enviando com Retrofit
            UsuarioService service = RetrofitClient.getRetrofitInstance().create(UsuarioService.class);
            Call<Void> call = service.cadastrarUsuario(usuario);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Erro no cadastro: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Falha: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
