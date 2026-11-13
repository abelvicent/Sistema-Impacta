package entidades;

import java.time.LocalDateTime;

public class OficinaEcologica extends Acao {

    protected int duracaoHoras;
    protected boolean kitMaterial;

    public OficinaEcologica(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras, boolean kitMaterial) {
        super(id, titulo, descricao, data, maxParticipantes);
        this.duracaoHoras = duracaoHoras;
        this.kitMaterial = kitMaterial;
    }

    @Override
    public double calcularPontuacao() {
        return (3 * duracaoHoras) + (kitMaterial ? 10 : 0);
    }

    public int getDuracaoHoras() {
        return duracaoHoras;
    }

    public boolean isKitMaterial() {
        return kitMaterial;
    }

    @Override
    public String getTipo() {
        return "Oficina Ecológica";
    }

    @Override
    public String detalhesEspecificos() {
        return "Duração: " + duracaoHoras + "h"
                + "\nKit de material educativo: " + (kitMaterial ? "Sim" : "Não");
    }
}
