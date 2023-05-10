package com.example.meuacervo.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meuacervo.R;
import com.example.meuacervo.database.LivrosDatabase;
import com.example.meuacervo.databinding.ListaDeLivrosBinding;
import com.example.meuacervo.model.Livros;
import com.example.meuacervo.ui.adapter.ListaDeLivrosAdapter;
import com.example.meuacervo.ui.fragments.ListaDeLivrosFragmentsDirections;

import java.util.List;

public class ListaDeLivrosFragments extends Fragment {

    private ListaDeLivrosAdapter adapter;

    private LivrosDatabase livrosDatabase;
    private ListaDeLivrosBinding binding;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ListaDeLivrosBinding.inflate(inflater, container, false);

        RecyclerView recyclerView = binding.rvListaDeLivros;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        livrosDatabase = new LivrosDatabase(getActivity());

        List<Livros> listaLivros = livrosDatabase.buscaTudo();
        ListaDeLivrosAdapter adapter = new ListaDeLivrosAdapter(listaLivros, getActivity());
        recyclerView.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtém a referência do NavController do NavHostFragment
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();

        configuraRecyclerView();
        configuraFab();
    }

    private void configuraRecyclerView() {
        adapter = new ListaDeLivrosAdapter(livrosDatabase.buscaTudo(), getActivity());
        binding.rvListaDeLivros.setAdapter(adapter);
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        binding.rvListaDeLivros.setLayoutManager(layout);

        adapter.setQuandoClicaNoItem(livro -> {
            vaiParaDetalhesFragment(livro);
        });

    }

    private void vaiParaDetalhesFragment(Livros livro) {
        navController.navigate(com.example.meuacervo.ui.fragments.ListaDeLivrosFragmentsDirections.actionListaDeLivrosFragmentsToDetalhesFragments(livro.getId()));
    }


    private void configuraFab() {
        binding.fabAdicionaLivro.setOnClickListener(view1 ->
                vaiParaFormulario()
        );
    }

    private void vaiParaFormulario() {
        navController.navigate(R.id.action_listaDeLivrosFragments_to_cadastroDeLivrosFragments);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

