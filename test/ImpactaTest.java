
package system;

import excecoes.EmailDuplicadoException;
import excecoes.InscricaoDuplicadaException;
import excecoes.AcaoLotadaException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ImpactaTest {

    @Test
    public void deveCadastrarVoluntarioComSucesso() {
        Impacta sistema = new Impacta();
        boolean resultado = sistema.cadastrarVoluntario(
                "Renato", "renato@email.com", "123"
        );

        assertTrue(resultado);
    }

    @Test
    public void naoDeveCadastrarEmailDuplicado() {
        Impacta sistema = new Impacta();
        sistema.cadastrarVoluntario(
                "Renato", "renato@email.com", "123"
        );
        assertThrows(
                EmailDuplicadoException.class,
                () -> sistema.cadastrarVoluntario(
                        "Abel", "RENATO@email.com", "456"
                )
        );
    }

    @Test
    public void deveExibirInformacoesDoVoluntario() {
        Impacta sistema = new Impacta();
        sistema.cadastrarVoluntario(
                "Cauã", "caua@email.com", "789"
        );
        String resultado = sistema.exibirVoluntario("caua@email.com");
        assertTrue(resultado.contains("Cauã"));
        assertTrue(resultado.contains("Quantidade de ações: 0"));
        assertTrue(resultado.contains("Pontuação acumulada: 0.0"));
    }

    @Test
    public void deveInformarQuandoVoluntarioNaoExistir() {
        Impacta sistema = new Impacta();
        String resultado = sistema.exibirVoluntario("naoexiste@email.com");
        assertEquals(
                "Voluntário não encontrado!",
                resultado
        );
    }

    @Test
    public void deveInscreverVoluntarioEmUmaAcao() {
        Impacta sistema = new Impacta();
        sistema.cadastrarVoluntario(
                "Renato", "renato@email.com", "123"
        );
        int id = sistema.cadastrarPlantio(
                "Plantio", "Plantar árvores",
                "01/12/2026 09:00", 5, 10
        );
        assertTrue(
                sistema.inscreverVoluntario("renato@email.com", id)
        );
        String resultado = sistema.exibirVoluntario("renato@email.com");
        assertTrue(resultado.contains("Quantidade de ações: 1"));
        assertTrue(resultado.contains("Pontuação acumulada: 25.0"));
    }

    @Test
    public void naoDeveInscreverVoluntarioInexistente() {
        Impacta sistema = new Impacta();
        int id = sistema.cadastrarPlantio(
                "Plantio", "Plantar árvores",
                "01/12/2026 09:00", 5, 10
        );
        assertFalse(
                sistema.inscreverVoluntario("naoexiste@email.com", id)
        );
    }

    @Test
    public void naoDeveInscreverEmAcaoInexistente() {
        Impacta sistema = new Impacta();
        sistema.cadastrarVoluntario(
                "Abel", "abel@email.com", "456"
        );
        assertFalse(
                sistema.inscreverVoluntario("abel@email.com", 999)
        );
    }

    @Test
    public void naoDeveInscreverDuasVezesNaMesmaAcao() {
        Impacta sistema = new Impacta();
        sistema.cadastrarVoluntario(
                "Cauã", "caua@email.com", "789"
        );
        int id = sistema.cadastrarPlantio(
                "Plantio", "Plantar árvores",
                "01/12/2026 09:00", 5, 10
        );

        sistema.inscreverVoluntario("caua@email.com", id);
        assertThrows(
                InscricaoDuplicadaException.class,
                () -> sistema.inscreverVoluntario(
                        "caua@email.com", id
                )
        );
    }

    @Test
    public void naoDeveInscreverQuandoAcaoEstiverLotada() {
        Impacta sistema = new Impacta();
        sistema.cadastrarVoluntario(
                "Renato", "renato@email.com", "123"
        );
        sistema.cadastrarVoluntario(
                "Abel", "abel@email.com", "456"
        );
        int id = sistema.cadastrarPlantio(
                "Plantio", "Plantar árvores",
                "01/12/2026 09:00", 1, 10
        );
        sistema.inscreverVoluntario("renato@email.com", id);
        assertThrows(
                AcaoLotadaException.class,
                () -> sistema.inscreverVoluntario(
                        "abel@email.com", id
                )
        );
    }

    @Test
    public void deveExibirMensagemQuandoAcaoNaoExistir() {
        Impacta sistema = new Impacta();
        assertEquals(
                "Ação não encontrada!",
                sistema.exibirDetalhesAcao(999)
        );
    }

    @Test
    public void deveListarVoluntariosPorPontuacaoDecrescente() {
        Impacta sistema = new Impacta();
        sistema.cadastrarVoluntario(
                "Renato", "renato@email.com", "123"
        );
        sistema.cadastrarVoluntario(
                "Abel", "abel@email.com", "456"
        );
        sistema.cadastrarVoluntario(
                "Cauã", "caua@email.com", "789"
        );
        int id = sistema.cadastrarPlantio(
                "Plantio", "Plantar árvores",
                "01/12/2026 09:00", 5, 10
        );
        sistema.inscreverVoluntario("renato@email.com", id);
        sistema.inscreverVoluntario("abel@email.com", id);
        String[] ranking = sistema.listarVoluntarios();
        assertEquals(3, ranking.length);
        assertTrue(ranking[0].contains("Abel"));
        assertTrue(ranking[1].contains("Renato"));
        assertTrue(ranking[2].contains("Cauã"));
    }
}
