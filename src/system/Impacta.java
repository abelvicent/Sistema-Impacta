package system;

import entidades.Acao;
import entidades.MutiraoReciclagem;
import entidades.OficinaEcologica;
import entidades.PlantioMudas;
import entidades.Voluntario;
import excecoes.EmailDuplicadoException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;

public class Impacta {

    private HashSet<Voluntario> voluntarios = new HashSet<>(); //aqui fazemos um hash da classe Voluntario
    private ArrayList<Acao> acoes = new ArrayList<>();
    private int proximoId = 1;

    public boolean cadastrarVoluntario(String nome, String email, String matricula) {
        Voluntario voluntario = new Voluntario(nome, email, matricula);

        if (voluntarios.contains(voluntario)) {
            throw new EmailDuplicadoException("E-mail já cadastrado!");
        }
        voluntarios.add(voluntario);
        return true;
    }

    public String exibirVoluntario(String email) {
        Voluntario exis = buscarVoluntario(email);
        if (exis == null) {
            return "Voluntário não encontrado!";
        }
        return "Nome: " + exis.getNome() + "\nQuantidade de ações: " + exis.getQuantidadeAcoes() + "\nPontuação acumulada: " + exis.getPontuacaoImpacto();
    }

    public String[] listarVoluntarios() {
        ArrayList<Voluntario> ranking = new ArrayList<>(voluntarios);

        ranking.sort((v1, v2) -> { //o metodo sort serve para ordenar uma lista, e passamos dois parametreos que servirá para ordenar, no caso V1 e v2, v1 um objeto de voluntario e v2 outro
            int comparacao = Double.compare(v2.getPontuacaoImpacto(), v1.getPontuacaoImpacto()); // a -> é uma expresssao lambda que indica o que o java  deve fazer com esses parametros, no caso da vez deve comparar a potuançao
                                                                                                // invertemos de v1 , v2 que estava no sort para v2, v1 pois queremos na ordem decrescente, caso contrario seria crescente.
            if (comparacao == 0) {
                return v1.getNome().compareToIgnoreCase(v2.getNome());
            }
            return comparacao;
        });

        String[] resultado = new String[ranking.size()];
        for (int i = 0; i < ranking.size(); i++) {
            Voluntario v = ranking.get(i);
            resultado[i] = "Nome: " + v.getNome() + " | Ações: " + v.getQuantidadeAcoes() + " | Pontuação: " + v.getPontuacaoImpacto();
        }
        return resultado;
    }

    public int cadastrarPlantio(String titulo, String descricao, String data, int maxParticipantes, int qtdMudas) {
        Acao plantio = new PlantioMudas(proximoId, titulo, descricao, converterData(data), maxParticipantes, qtdMudas);
        return adicionarAcao(plantio);
    }

    public int cadastrarMutirao(String titulo, String descricao, String data, int maxParticipantes, int duracaoHoras) {
        Acao mutirao = new MutiraoReciclagem(proximoId, titulo, descricao, converterData(data), maxParticipantes, duracaoHoras);
        return adicionarAcao(mutirao);
    }

    public int cadastrarOficina(String titulo, String descricao, String data, int maxParticipantes, int duracaoHoras, boolean kitMaterial) {
        Acao oficina = new OficinaEcologica(proximoId, titulo, descricao, converterData(data), maxParticipantes, duracaoHoras, kitMaterial);
        return adicionarAcao(oficina);
    }

    public boolean inscreverVoluntario(String emailVoluntario, int idAcao) {
        Voluntario voluntario = buscarVoluntario(emailVoluntario);
        Acao acao = buscarAcao(idAcao);

        if (voluntario == null || acao == null) {
            return false;
        }
        acao.inscrever(voluntario);
        return true;
    }

    public String exibirDetalhesAcao(int idAcao) {
        Acao acao = buscarAcao(idAcao);
        if (acao == null) {
            return "Ação não encontrada!";
        }
        return acao.exibirDetalhes();
    }

    private int adicionarAcao(Acao acao) {
        acoes.add(acao);
        proximoId++;
        return acao.getId();
    }

    private Voluntario buscarVoluntario(String email) {
        for (Voluntario exis : voluntarios) {
            if (exis.getEmail().equalsIgnoreCase(email)) {
                return exis;
            }
        }
        return null;
    }

    private Acao buscarAcao(int id) {
        for (Acao acao : acoes) {
            if (acao.getId() == id) {
                return acao;
            }
        }
        return null;
    }

    private LocalDateTime converterData(String data) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(data, formato);
    }
}
