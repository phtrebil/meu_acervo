package com.example.meuacervo.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.meuacervo.R;
import com.example.meuacervo.database.LivrosDatabase;
import com.example.meuacervo.databinding.AdicionarImagemBinding;
import com.example.meuacervo.databinding.CadastroDeLivrosBinding;
import com.example.meuacervo.model.Livros;
import com.example.meuacervo.ui.MainActivity;
import com.example.meuacervo.ui.dialog.AdicionaImagemDialog;
import com.example.meuacervo.ui.validador.ValidadorDeCampo;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import javax.xml.validation.Validator;

public class CadastroDeLivrosFragments extends Fragment {

    private LivrosDatabase database;
    private NavController navController;
    private Livros livro;

    private final List<ValidadorDeCampo> validadores = new ArrayList<>();

    private Bundle args;

    private CadastroDeLivrosBinding binding;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        database = new LivrosDatabase(getActivity());
        livro = new Livros(0, "", "", "", 0, 0, "");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = CadastroDeLivrosBinding.inflate(inflater, container, false);
        args = getArguments();
        if (args != null && args.containsKey("livroId")) {
            livro = database.buscaLivroPorId(args.getInt("livroId"));
            preencheCampos(livro);
        }
        return binding.getRoot();
    }

    private void preencheCampos(Livros livro) {
        binding.autorAdd.setText(livro.getAutor());
        binding.tituloAdd.setText(livro.getTítulo());
        binding.avaliacaoAdd.setText(String.valueOf(livro.getAvalicao()));
        binding.notaAdd.setText(livro.getNota());
        binding.pagAddCliente.setText(String.valueOf(livro.getPaginas()));

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        validaCampoAvaliacao();
        binding.imagemAddCapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdicionaImagemDialog dialog = new AdicionaImagemDialog(getContext());
                dialog.mostra(new Consumer<String>() {
                    @Override
                    public void accept(String imagem) {
                        String url = imagem;
                        Picasso.get().load(url).into(binding.imagemAddCapa);
                        livro.setCapa(imagem);
                    }
                });
            }
        });


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_cadasro_salvar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_cadastr_salvar){
            preencheLivro(livro);
            salvaLivro();
            vaiParaListaDeLivros();
        }

        return super.onOptionsItemSelected(item);
    }

    private void salvaLivro() {
        if (args != null && args.containsKey("livroId")){
            database.atualizaLivro(livro);
        }else{
            database.adicionaLivro(livro);
        }
    }

    private void vaiParaListaDeLivros() {
        navController.navigate(R.id.action_cadastroDeLivrosFragments_to_listaDeLivrosFragments);
    }

    private void preencheLivro(Livros livros) {
        livros.setTítulo(Objects.requireNonNull(binding.tituloAdd.getText()).toString());
        livros.setAutor(Objects.requireNonNull(binding.autorAdd.getText()).toString());
        int numPaginas = Integer.parseInt(Objects.requireNonNull(binding.pagAddCliente.getText()).toString());
        livros.setPaginas(numPaginas);
        livros.setNota(Objects.requireNonNull(binding.notaAdd.getText()).toString());
        int avaliacao = Integer.parseInt(Objects.requireNonNull(binding.avaliacaoAdd.getText()).toString());
        livros.setAvalicao(avaliacao);

    }

    private void validaCampoAvaliacao() {
        TextInputLayout textInputAvaliacao = binding.avaliacaoOutlinedTextField;
        EditText campoAvaliacao = textInputAvaliacao.getEditText();
        ValidadorDeCampo validadorAvaliacao = new ValidadorDeCampo(textInputAvaliacao);
        validadores.add(validadorAvaliacao);
        campoAvaliacao.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                validadorAvaliacao.estaValido();
            }
        });
    }


}
