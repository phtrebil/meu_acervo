package com.example.meuacervo.model;

public class Livros {

    private int id;
    private String título;
    private String autor;
    private String capa;
    private int paginas;

    private int avalicao;

    private String nota;

    public Livros(int id, String título, String autor, String capa, int paginas, int avalicao, String nota) {
        this.id = id;
        this.título = título;
        this.autor = autor;
        this.capa = capa;
        this.paginas = paginas;
        this.avalicao = avalicao;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvalicao() {
        return avalicao;
    }

    public void setAvalicao(int avalicao) {
        this.avalicao = avalicao;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
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
