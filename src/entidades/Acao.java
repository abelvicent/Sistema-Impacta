package entidades;

import java.time.LocalDateTime;

public abstract class Acao {
    protected int id;
    protected String titulo;
    protected String descricao;
    protected LocalDateTime data;
    protected int capacidadeMaxima;

    public Acao (int id, String titulo, String descricao, LocalDateTime data, int capacidadeMaxima) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public abstract double calcularPontuacao();

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }
}
