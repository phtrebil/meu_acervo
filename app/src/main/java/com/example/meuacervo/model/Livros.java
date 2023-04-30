package com.example.meuacervo.model;

public class Livros {

    private int id;
    private String título;
    private String autor;
    private String capa;
    private int paginas;

    private int avalicao;

    private String nota;

    public Livros(String título, String autor, String capa, int paginas, int id, int avalicao, String nota) {
        this.id = id;
        this.título = título;
        this.autor = autor;
        this.capa = capa;
        this.paginas = paginas;
        this.avalicao = avalicao;
        this.nota = nota;
    }

    public String getTítulo() {
        return título;
    }

    public void setTítulo(String título) {
        this.título = título;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }
}
