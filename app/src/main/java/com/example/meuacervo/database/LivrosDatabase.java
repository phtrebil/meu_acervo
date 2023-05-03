package com.example.meuacervo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.meuacervo.model.Livros;

public class LivrosDatabase extends SQLiteOpenHelper {

    private Context context;
    private static final String NOME_DATABASE = "Livro.db";
    private static final int VERSAO_DATABASE = 1;
    private static final String NOME_TABELA = "meu_acervo";

    private static final String COLUNA_ID = "_id";
    private static final String COLUNA_TITULO = "livro_titulo";
    private static final String COLUNA_AUTOR = "livro_autor";
    private static final String COLUNA_CAPA = "livro_capa";
    private static final String COLUNA_PAG = "livro_pagina";
    private static final String COLUNA_AVALIACAO = "livro_avalicao";
    private static final String COLUNA_NOTA = "livro_nota";


    public LivrosDatabase(@Nullable Context context) {
        super(context, NOME_DATABASE, null, VERSAO_DATABASE);
        this.context = context;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + NOME_DATABASE +
                        " (" + COLUNA_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, " +
                        COLUNA_TITULO + " TEXT, " +
                        COLUNA_AUTOR + "TEXT, " +
                        COLUNA_CAPA + "TEXT, " +
                        COLUNA_PAG + "INTEGER, " +
                        COLUNA_AVALIACAO + "INTEGER, " +
                        COLUNA_NOTA + "TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + NOME_TABELA);
        onCreate(db);

    }
    void adicionaLivro(Livros livros){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUNA_AUTOR, livros.getAutor());
        cv.put(COLUNA_TITULO, livros.getTítulo());
        cv.put(COLUNA_CAPA, livros.getCapa());
        cv.put(COLUNA_AVALIACAO, livros.getAvalicao());
        cv.put(COLUNA_PAG, livros.getPaginas());
        cv.put(COLUNA_NOTA, livros.getNota());

        Long resultadoDb = db.insert(NOME_TABELA, null, cv);

        if(resultadoDb == -1){
            Toast.makeText(context, "erro ao adicionar livro", Toast.LENGTH_SHORT);
        }else{
            Toast.makeText(context, "livro adicionado com sucesso", Toast.LENGTH_SHORT);
        }
    }
}
