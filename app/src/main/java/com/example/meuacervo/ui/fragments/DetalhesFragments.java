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
import com.example.meuacervo.databinding.DetalhesBinding;
import com.example.meuacervo.model.Livros;

public class DetalhesFragments extends Fragment {

    private NavController navController;
    private Livros livro;
    private LivrosDatabase database;
    private DetalhesBinding binding;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        livro = new Livros(0, "", "", "", 0, 0, "");;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DetalhesBinding.inflate(inflater, container, false);
        database = new LivrosDatabase(requireActivity());
        Bundle args = getArguments();
        if (args != null && args.containsKey("id_livro")) {
            livro = database.buscaLivroPorId(args.getInt("id_livro"));
        }


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        preencheCampos();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detalhes, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_detalhes_deletar){
            database.deleteLivro(livro);
            navController.navigate(R.id.action_detalhesFragments_to_listaDeLivrosFragments);
        }
        if(item.getItemId() == R.id.menu_detalhe_editar){
            vaiParaFormulario();
        }
        return super.onOptionsItemSelected(item);

    }

    private void vaiParaFormulario() {
        navController.navigate(R.id.action_detalhesFragments_to_cadastroDeLivrosFragments);
    }

    private void preencheCampos() {
        binding.autorDetalhes.setText(livro.getAutor());
        binding.tituloDetalhes.setText(livro.getTítulo());
        binding.paginasDetalhes.setText(String.valueOf(livro.getPaginas()));
        binding.avaliacaoDetalhes.setText(String.valueOf(livro.getAvalicao()));
        binding.notaDetalhes.setText(livro.getNota());
    }
}
