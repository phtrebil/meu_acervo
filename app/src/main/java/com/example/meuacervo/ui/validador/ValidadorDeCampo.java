package com.example.meuacervo.ui.validador;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class ValidadorDeCampo {
    private static final String AVALIACAO_INVALIDA = "Avaliação deve ser de 1 a 5";
    private final TextInputLayout textInputCampo;
    private final EditText campo;

    public ValidadorDeCampo(TextInputLayout textInputCampo) {
        this.textInputCampo = textInputCampo;
        this.campo = this.textInputCampo.getEditText();
    }

    private boolean validaAvaliacao() {
        String texto = campo.getText().toString();
        try {
            int avaliacao = Integer.parseInt(texto);
            if (avaliacao < 1 || avaliacao > 5) {
                textInputCampo.setError(AVALIACAO_INVALIDA);
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            // se não for possível converter para inteiro, assume que está inválido
            textInputCampo.setError(AVALIACAO_INVALIDA);
            return false;
        }
    }


    public boolean estaValido(){

        if(!validaAvaliacao()) return false;
        removeErro();
        return true;
    }



    private void removeErro() {
        textInputCampo.setError(null);
        textInputCampo.setErrorEnabled(false);
    }
}

