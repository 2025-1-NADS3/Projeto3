package com.example.pipt2;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class UsuarioDAO {

    private SQLiteDatabase banco;
    private DBHelper dbHelper;

    public UsuarioDAO(Context context) {
        dbHelper = new DBHelper(context);
        banco = dbHelper.getWritableDatabase();
    }

    public long inserir(String nome, String email) {
        ContentValues valores = new ContentValues();
        valores.put(DBHelper.COLUNA_NOME, nome);
        valores.put(DBHelper.COLUNA_EMAIL, email);
        return banco.insert(DBHelper.TABELA_USUARIOS, null, valores);
    }

    public ArrayList<String> listarUsuarios() {
        ArrayList<String> lista = new ArrayList<>();

        Cursor cursor = banco.query(DBHelper.TABELA_USUARIOS,
                null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String nome = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUNA_NOME));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUNA_EMAIL));
            lista.add(nome + " - " + email);
        }

        cursor.close();
        return lista;
    }

    public int atualizar(int id, String nome, String email) {
        ContentValues valores = new ContentValues();
        valores.put(DBHelper.COLUNA_NOME, nome);
        valores.put(DBHelper.COLUNA_EMAIL, email);

        return banco.update(DBHelper.TABELA_USUARIOS,
                valores,
                DBHelper.COLUNA_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public int deletar(int id) {
        return banco.delete(DBHelper.TABELA_USUARIOS,
                DBHelper.COLUNA_ID + " = ?",
                new String[]{String.valueOf(id)});
    }
}