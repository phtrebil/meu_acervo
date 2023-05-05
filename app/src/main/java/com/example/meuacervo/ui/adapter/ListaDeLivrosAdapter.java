package com.example.meuacervo.ui.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meuacervo.model.Livros;

import java.util.List;

public class ListaDeLivrosAdapter extends RecyclerView.Adapter<ListaDeLivrosAdapter.ListaDeLivrosViewHolder> {

    private List<Livros> livros;

    @NonNull
    @Override
    public ListaDeLivrosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ListaDeLivrosViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ListaDeLivrosViewHolder extends RecyclerView.ViewHolder {
        public ListaDeLivrosViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
