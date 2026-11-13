package entidades;

import excecoes.AcaoLotadaException;
import excecoes.InscricaoDuplicadaException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class Acao {
    protected int id;
    protected String titulo;
    protected String descricao;
    protected LocalDateTime data;
    protected int capacidadeMaxima;
    protected ArrayList<Voluntario> inscritos = new ArrayList<>();

    public Acao (int id, String titulo, String descricao, LocalDateTime data, int capacidadeMaxima) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public abstract double calcularPontuacao();

    public abstract String getTipo();

    public abstract String detalhesEspecificos();

    public void inscrever(Voluntario voluntario) {
        if (inscritos.contains(voluntario)) {
            throw new InscricaoDuplicadaException("O voluntário já está inscrito nesta ação!");
        }
        if (inscritos.size() >= capacidadeMaxima) {
            throw new AcaoLotadaException("A ação está lotada!");
        }
        inscritos.add(voluntario);
        voluntario.adicionarAcao(this);
    }

    public String exibirDetalhes() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        String texto = "Ação " + id + " - " + getTipo()
                + "\nTítulo: " + titulo
                + "\nDescrição: " + descricao
                + "\nData: " + data.format(formato)
                + "\n" + detalhesEspecificos()
                + "\nPontuação: " + calcularPontuacao()
                + "\nVagas: " + inscritos.size() + "/" + capacidadeMaxima
                + "\nVoluntários inscritos:";

        if (inscritos.isEmpty()) {
            texto += " nenhum";
        } else {
            for (Voluntario v : inscritos) {
                texto += "\n - " + v.getNome() + " (" + v.getEmail() + ")";
            }
        }
        return texto;
    }

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

    public ArrayList<Voluntario> getInscritos() {
        return new ArrayList<>(inscritos);
    }
}
