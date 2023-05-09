package com.example.meuacervo.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.meuacervo.R;
import com.example.meuacervo.database.LivrosDatabase;
import com.example.meuacervo.databinding.CadastroDeLivrosBinding;
import com.example.meuacervo.model.Livros;

public class CadastroDeLivrosFragments extends Fragment {

    private LivrosDatabase database;
    private NavController navController;
    private Livros livros;

    private CadastroDeLivrosBinding binding;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        database = new LivrosDatabase(getActivity());
        livros = new Livros(0, "", "", "", 0, 0, "");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = CadastroDeLivrosBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_cadasro_salvar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_cadastr_salvar){
            preencheLivro(livros);
            database.adicionaLivro(livros);
            vaiParaListaDeLivros();
        }

        return super.onOptionsItemSelected(item);
    }

    private void vaiParaListaDeLivros() {
        navController.navigate(R.id.action_cadastroDeLivrosFragments_to_listaDeLivrosFragments);
    }

    private void preencheLivro(Livros livros) {
        livros.setTítulo(binding.tituloAdd.getText().toString());
        livros.setAutor(binding.autorAdd.getText().toString());
        int numPaginas = Integer.parseInt(binding.pagAddCliente.getText().toString());
        livros.setPaginas(numPaginas);
        livros.setNota(binding.notaAdd.toString());
        int avaliacao = Integer.parseInt(binding.avaliacaoAdd.getText().toString());
        livros.setAvalicao(avaliacao);


    }
}
