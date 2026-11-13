package entidades;

import java.time.LocalDateTime;

public class PlantioMudas extends Acao {

    protected int qtdMudas;

    public PlantioMudas(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes, int qtdMudas) {
        super(id, titulo, descricao, data, maxParticipantes);
        this.qtdMudas = qtdMudas;
    }

    @Override
    public double calcularPontuacao() {
        return 5 + (2 * qtdMudas);
    }

    public int getQtdMudas() {
        return qtdMudas;
    }

    @Override
    public String getTipo() {
        return "Plantio de Mudas";
    }

    @Override
    public String detalhesEspecificos() {
        return "Quantidade de mudas: " + qtdMudas;
    }
}
