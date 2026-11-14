
package entidades;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class VoluntarioTest {

    @Test
    public void deveCriarVoluntarioCorretamente() {

        Voluntario v = new Voluntario(
                "Renato", "renato@email.com", "123"
        );

        assertEquals("Renato", v.getNome());
        assertEquals("renato@email.com", v.getEmail());
        assertEquals("123", v.getMatricula());
    }

    @Test
    public void deveIniciarComZeroAcoesEPontuacao() {
        Voluntario v = new Voluntario(
                "Abel", "abel@email.com", "456"
        );
        assertEquals(0, v.getQuantidadeAcoes());
        assertEquals(0.0, v.getPontuacaoImpacto());
    }

    @Test
    public void deveAtualizarQuantidadeEPontuacaoAoAdicionarAcao() {
        Voluntario v = new Voluntario(
                "Cauã", "caua@email.com", "789"
        );
        Acao acao = new PlantioMudas(
                1, "Plantio", "Árvores",
                LocalDateTime.of(2026, 12, 1, 9, 0),
                10, 10
        );
        v.adicionarAcao(acao);
        assertEquals(1, v.getQuantidadeAcoes());
        assertEquals(25.0, v.getPontuacaoImpacto());
    }
    @Test
    public void voluntariosComMesmoEmailDevemSerIguais() {
        Voluntario v1 = new Voluntario(
                "Renato", "renato@email.com", "123"
        );
        Voluntario v2 = new Voluntario(
                "Abel", "RENATO@email.com", "456"
        );
        assertEquals(v1, v2);
    }
    @Test
    public void voluntariosIguaisDevemTerMesmoHashCode() {
        Voluntario v1 = new Voluntario(
                "Cauã", "caua@email.com", "789"
        );
        Voluntario v2 = new Voluntario(
                "Cauã", "CAUA@email.com", "789"
        );
        assertEquals(v1.hashCode(), v2.hashCode());
    }
}
