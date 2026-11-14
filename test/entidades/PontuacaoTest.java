package entidades;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PontuacaoTest {

    private LocalDateTime data = LocalDateTime.of(2026, 12, 1, 9, 0);

    @Test
    public void plantioDeveValer5MaisDoisPorMuda() {
        Acao plantio = new PlantioMudas(1, "Plantio na praça", "Plantar árvores", data, 10, 20);
        System.out.println(plantio.calcularPontuacao());
        assertEquals(45.0, plantio.calcularPontuacao());
    }

    @Test
    public void plantioSemMudasDeveValerSomenteOs5PontosBase() {
        Acao plantio = new PlantioMudas(1, "Plantio", "Sem mudas", data, 10, 0);
        System.out.println(plantio.calcularPontuacao());
        assertEquals(5.0, plantio.calcularPontuacao());
    }

    @Test
    public void mutiraoDeveValer4PorHora() {
        Acao mutirao = new MutiraoReciclagem(2, "Mutirão", "Coleta de recicláveis", data, 10, 6);
        System.out.println(mutirao.calcularPontuacao());
        assertEquals(24.0, mutirao.calcularPontuacao());
    }

    @Test
    public void oficinaComKitDeveValer3PorHoraMais10() {
        Acao oficina = new OficinaEcologica(3, "Oficina", "Compostagem", data, 10, 4, true);
        System.out.println(oficina.calcularPontuacao());
        assertEquals(22.0, oficina.calcularPontuacao());
    }

    @Test
    public void oficinaSemKitDeveValerSomente3PorHora() {
        Acao oficina = new OficinaEcologica(3, "Oficina", "Compostagem", data, 10, 4, false);
        System.out.println(oficina.calcularPontuacao());
        assertEquals(12.0, oficina.calcularPontuacao());
    }

    @Test
    public void mesmoMetodoDeveCalcularDiferenteParaCadaTipo() {
        Acao[] acoes = new Acao[3];
        acoes[0] = new PlantioMudas(1, "Plantio", "desc", data, 5, 10);
        acoes[1] = new MutiraoReciclagem(2, "Mutirão", "desc", data, 5, 3);
        acoes[2] = new OficinaEcologica(3, "Oficina", "desc", data, 5, 2, true);

        System.out.println(acoes[0].calcularPontuacao());
        System.out.println(acoes[1].calcularPontuacao());
        System.out.println(acoes[2].calcularPontuacao());

        assertEquals(25.0, acoes[0].calcularPontuacao());
        assertEquals(12.0, acoes[1].calcularPontuacao());
        assertEquals(16.0, acoes[2].calcularPontuacao());
    }
}
