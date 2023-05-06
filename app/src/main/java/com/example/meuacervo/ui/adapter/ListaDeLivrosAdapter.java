package com.example.meuacervo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meuacervo.R;
import com.example.meuacervo.model.Livros;
import com.example.meuacervo.ui.adapter.listener.QuandoClicaNoItem;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class ListaDeLivrosAdapter extends RecyclerView.Adapter<ListaDeLivrosAdapter.ListaDeLivrosViewHolder> {

    private List<Livros> livros;
    private Context context;
    private QuandoClicaNoItem quandoClicaNoItem;

    public ListaDeLivrosAdapter(List<Livros> livros, Context context) {
        this.livros = livros;
        this.context = context;
    }

    public void setQuandoClicaNoItem(QuandoClicaNoItem quandoClicaNoItem){
        this.quandoClicaNoItem = quandoClicaNoItem;
    }

    @NonNull
    @Override
    public ListaDeLivrosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_lista_de_livros, parent, false);
        return new ListaDeLivrosViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaDeLivrosViewHolder holder, int position) {
        Livros livro = livros.get(position);
        holder.vincula(livro);
    }

    @Override
    public int getItemCount() {
        return livros.size();
    }

    public class ListaDeLivrosViewHolder extends RecyclerView.ViewHolder {


        public ListaDeLivrosViewHolder(@NonNull View itemView) {
            super(itemView);


        }

        public void vincula(Livros livro) {
            TextView titulo = itemView.findViewById(R.id.titulo_livro);
            titulo.setText(livro.getTítulo());
            TextView autor = itemView.findViewById(R.id.nome_do_autor);
            autor.setText(livro.getAutor());
            TextView paginas = itemView.findViewById(R.id.paginas_item);
            paginas.setText(livro.getPaginas());
            ImageView capa = itemView.findViewById(R.id.capa_do_livro);
            Picasso.get().load(livro.getCapa()).into(capa);
        }
    }
}
