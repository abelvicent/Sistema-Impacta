
package entidades;

import excecoes.AcaoLotadaException;
import excecoes.InscricaoDuplicadaException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AcaoTest {
    private LocalDateTime data =
            LocalDateTime.of(2026, 12, 1, 9, 0);
    @Test
    public void deveInscreverVoluntarioComSucesso() {
        Acao acao = new PlantioMudas(
                1, "Plantio", "Plantar árvores", data, 5, 10
        );
        Voluntario voluntario = new Voluntario(
                "Igor", "igor@email.com", "123"
        );
        acao.inscrever(voluntario);
        assertEquals(1, acao.getInscritos().size());
        assertEquals(1, voluntario.getQuantidadeAcoes());
        assertEquals(25.0, voluntario.getPontuacaoImpacto());
    }
    @Test
    public void naoDevePermitirInscricaoDuplicada() {
        Acao acao = new PlantioMudas(
                1, "Plantio", "Plantar árvores", data, 5, 10
        );
        Voluntario voluntario = new Voluntario(
                "Igor", "igor@email.com", "123"
        );
        acao.inscrever(voluntario);
        assertThrows(
                InscricaoDuplicadaException.class,
                () -> acao.inscrever(voluntario)
        );
    }
    @Test
    public void naoDevePermitirInscricaoQuandoAcaoEstiverLotada() {
        Acao acao = new PlantioMudas(
                1, "Plantio", "Plantar árvores", data, 1, 10
        );
        Voluntario v1 = new Voluntario(
                "Caua", "caua@email.com", "123"
        );
        Voluntario v2 = new Voluntario(
                "Abel", "abel@email.com", "456"
        );
        acao.inscrever(v1);
        assertThrows(
                AcaoLotadaException.class,
                () -> acao.inscrever(v2)
        );
    }
    @Test
    public void deveRetornarUmaCopiaDaListaDeInscritos() {
        Acao acao = new PlantioMudas(
                1, "Plantio", "Plantar árvores", data, 5, 10
        );
        Voluntario voluntario = new Voluntario(
                "Renato", "renato@email.com", "123"
        );
        acao.inscrever(voluntario);
        acao.getInscritos().clear();
        assertEquals(1, acao.getInscritos().size());
    }
    @Test
    public void deveExibirDetalhesDaAcao() {
        Acao acao = new PlantioMudas(
                1, "Plantio", "Plantar árvores", data, 5, 10
        );
        String detalhes = acao.exibirDetalhes();
        assertTrue(detalhes.contains("Plantio de Mudas"));
        assertTrue(detalhes.contains("Plantar árvores"));
        assertTrue(detalhes.contains("Quantidade de mudas: 10"));
        assertTrue(detalhes.contains("Pontuação: 25.0"));
    }
}
