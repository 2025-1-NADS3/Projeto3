package com.example.pipt2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Lógica de cadastro aqui
        Button btnCadastrar = findViewById(R.id.btnCadastrar); // o ID do botão no XML
        EditText edtNome = findViewById(R.id.username);
        EditText edtEmail = findViewById(R.id.password); // ou outro campo para e-mail

        btnCadastrar.setOnClickListener(v -> {
            String nome = edtNome.getText().toString();
            String email = edtEmail.getText().toString();

            if (nome.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            } else {
                UsuarioDAO usuarioDAO = new UsuarioDAO(this);
                long resultado = usuarioDAO.inserir(nome, email);

                if (resultado != -1) {
                    Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Erro ao cadastrar!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
