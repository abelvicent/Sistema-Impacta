
package entidades;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class SubclassesTest {

    private LocalDateTime data =
            LocalDateTime.of(2026, 12, 1, 9, 0);

    @Test
    public void deveCriarPlantioCorretamente() {
        PlantioMudas plantio = new PlantioMudas(
                1, "Plantio", "Plantar árvores",
                data, 10, 20
        );
        assertEquals("Plantio de Mudas", plantio.getTipo());
        assertEquals(20, plantio.getQtdMudas());
        assertEquals(
                "Quantidade de mudas: 20",
                plantio.detalhesEspecificos()
        );
    }

    @Test
    public void deveCriarMutiraoCorretamente() {
        MutiraoReciclagem mutirao = new MutiraoReciclagem(
                2, "Mutirão", "Reciclagem",
                data, 10, 6
        );
        assertEquals(
                "Mutirão de Reciclagem",
                mutirao.getTipo()
        );
        assertEquals(6, mutirao.getDuracaoHoras());
        assertEquals(
                "Duração: 6h",
                mutirao.detalhesEspecificos()
        );
    }

    @Test
    public void deveCriarOficinaComKitCorretamente() {
        OficinaEcologica oficina = new OficinaEcologica(
                3, "Oficina", "Compostagem",
                data, 10, 4, true
        );
        assertEquals(
                "Oficina Ecológica",
                oficina.getTipo()
        );
        assertEquals(4, oficina.getDuracaoHoras());
        assertTrue(oficina.isKitMaterial());
        assertTrue(
                oficina.detalhesEspecificos().contains(
                        "Kit de material educativo: Sim"
                )
        );
    }

    @Test
    public void deveCriarOficinaSemKitCorretamente() {
        OficinaEcologica oficina = new OficinaEcologica(
                3, "Oficina", "Compostagem",
                data, 10, 4, false
        );
        assertFalse(oficina.isKitMaterial());
        assertTrue(
                oficina.detalhesEspecificos().contains(
                        "Kit de material educativo: Não"
                )
        );
    }

    @Test
    public void todasAsSubclassesDevemSerDoTipoAcao() {
        Acao plantio = new PlantioMudas(
                1, "Plantio", "desc", data, 10, 5
        );
        Acao mutirao = new MutiraoReciclagem(
                2, "Mutirão", "desc", data, 10, 5
        );
        Acao oficina = new OficinaEcologica(
                3, "Oficina", "desc", data, 10, 5, true
        );
        assertInstanceOf(Acao.class, plantio);
        assertInstanceOf(Acao.class, mutirao);
        assertInstanceOf(Acao.class, oficina);
    }

    @Test
    public void detalhesDevemConterInformacoesEspecificas() {
        Acao plantio = new PlantioMudas(
                1, "Plantio", "Árvores", data, 10, 5
        );
        Acao mutirao = new MutiraoReciclagem(
                2, "Mutirão", "Reciclagem", data, 10, 3
        );
        Acao oficina = new OficinaEcologica(
                3, "Oficina", "Educação", data, 10, 2, true
        );
        assertTrue(plantio.exibirDetalhes().contains("Quantidade de mudas: 5"));
        assertTrue(mutirao.exibirDetalhes().contains("Duração: 3h"));
        assertTrue(oficina.exibirDetalhes().contains("Kit de material educativo: Sim"));
    }
}
