package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Aqui é onde você detecta o clique no boleto
       // LinearLayout boletoLayout = findViewById(R.id.boleto1); // use o ID real do layout do boleto
        //boletoLayout.setOnClickListener(v -> mostrarDialogRecarga());
    }

    private void mostrarDialogRecarga() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_recarga, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.show();

        EditText numeroCartao = dialogView.findViewById(R.id.etNumeroCartao);
        EditText nomeCartao = dialogView.findViewById(R.id.etNomeCartao);
        EditText validade = dialogView.findViewById(R.id.etValidade);
        EditText cvv = dialogView.findViewById(R.id.etCVV);
        Button btnConfirmar = dialogView.findViewById(R.id.btnConfirmarRecarga);

        btnConfirmar.setOnClickListener(v -> {
            Toast.makeText(this, "Recarga confirmada!", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });
    }
}
