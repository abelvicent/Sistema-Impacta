package system;

import entidades.Voluntario;
import excecoes.EmailDuplicadoException;
import java.util.HashSet;

public class SistemaVoluntarios {

  HashSet<Voluntario> voluntarios;

    public SistemaVoluntarios() {
        voluntarios = new HashSet<>();
    }

    public boolean cadastrarVoluntario(String nome, String email, String matricula) {

        for (Voluntario exis : voluntarios) {
            if (exis.getEmail().equalsIgnoreCase(email)) {
                throw new EmailDuplicadoException("E-mail já cadastrado!");
            }
        }
        Voluntario voluntario = new Voluntario(nome, email, matricula);
        voluntarios.add(voluntario);
        return true;
    }

    public String exibirVoluntario(String email) {

        for (Voluntario exis : voluntarios) {
            if (exis.getEmail().equalsIgnoreCase(email)) {
                return "Nome: " + exis.getNome() + "\nQuantidade de ações: " + exis.getQuantidadeAcoes() + "\nPontuação acumulada: " + exis.getPontuacaoImpacto();
            }
        }
        return "Voluntário não encontrado!";
    }
}

// falta listar
