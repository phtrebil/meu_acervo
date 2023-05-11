package com.example.meuacervo.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.example.meuacervo.databinding.AdicionarImagemBinding;
import com.squareup.picasso.Picasso;

import java.util.function.Consumer;

public class AdicionaImagemDialog {
    private Context context;

    public AdicionaImagemDialog(Context context) {
        this.context = context;
    }

    public void mostra(final Consumer<String> passandoURL) {
        final AdicionarImagemBinding binding = AdicionarImagemBinding
                .inflate(LayoutInflater.from(context));
        binding.formularioImagemBotaoCarregar
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = binding.URLText.getText().toString();
                        Picasso.get().load(url).into(binding.formularioImagemImageview)
                        ;
                    }
                });

        new AlertDialog.Builder(context)
                .setView(binding.getRoot())
                .setPositiveButton("Confirma", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String url = binding.URLText.getText().toString();
                        passandoURL.accept(url);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}

