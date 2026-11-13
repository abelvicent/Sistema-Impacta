package entidades;

import java.time.LocalDateTime;

public class MutiraoReciclagem extends Acao {

    protected int duracaoHoras;

    public MutiraoReciclagem(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras) {
        super(id, titulo, descricao, data, maxParticipantes);
        this.duracaoHoras = duracaoHoras;
    }

    @Override
    public double calcularPontuacao() {
        return 4 * duracaoHoras;
    }

    public int getDuracaoHoras() {
        return duracaoHoras;
    }

    @Override
    public String getTipo() {
        return "Mutirão de Reciclagem";
    }

    @Override
    public String detalhesEspecificos() {
        return "Duração: " + duracaoHoras + "h";
    }
}
