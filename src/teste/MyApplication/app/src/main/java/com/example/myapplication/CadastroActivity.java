package com.example.myapplication;

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

public class CadastroActivity extends AppCompatActivity {

    private EditText editNome, editCurso, editSemestre, editCPF, editRA, editNascimento, editEmail, editSenha;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro); // nome do seu XML

        editNome = findViewById(R.id.editNome);
        editCurso = findViewById(R.id.editCurso);
        editSemestre = findViewById(R.id.editSemestre);
        editCPF = findViewById(R.id.editCPF);
        editRA = findViewById(R.id.editRA);
        editNascimento = findViewById(R.id.editNascimento);
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        btnSignIn = findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(view -> {
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
            usuario.setDataNascimento(editNascimento.getText().toString());
            usuario.setEmail(editEmail.getText().toString());
            usuario.setSenha(editSenha.getText().toString());
            usuario.setSaldo(0.0);

            UsuarioService service = RetrofitClient.getRetrofitInstance().create(UsuarioService.class);
            Call<Void> call = service.cadastrarUsuario(usuario);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(CadastroActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(CadastroActivity.this, "Erro no cadastro: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(CadastroActivity.this, "Falha: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
