package system;

import entidades.Voluntario;
import excecoes.EmailDuplicadoException;

import java.util.ArrayList;
import java.util.HashSet;

public class SistemaVoluntarios {

  HashSet<Voluntario> voluntarios = new HashSet<>(); //aqui fazemos um hash da classe Voluntario

    public boolean cadastrarVoluntario(String nome, String email, String matricula, int quantidadeAcoes, double pontuacaoImpacto) {
        for (Voluntario exis : voluntarios) {
            if (exis.getEmail().equalsIgnoreCase(email)) {
                throw new EmailDuplicadoException("E-mail já cadastrado!");
            }
        }
        Voluntario voluntario = new Voluntario(nome, email, matricula, quantidadeAcoes, pontuacaoImpacto );
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

    public void listarVoluntarios(){
        if(voluntarios.isEmpty()){
            System.out.println("Nenhum voluntário cadastrado!");
        }else {
            ArrayList<Voluntario> ranking = new ArrayList<>();

            ranking.sort((v1, v2) ->  //o metodo sort serve para ordenar uma lista, e passamos dois parametreos que servirá para ordenar, no caso V1 e v2, v1 um objeto de voluntario e v2 outro
                    Double.compare(v2.getPontuacaoImpacto(), v1.getPontuacaoImpacto())); // a -> é uma expresssao lambda que indica o que o java  deve fazer com esses parametros, no caso da vez deve comparar a potuançao
                                                                                        // invertemos de v1 , v2 que estava no sort para v2, v1 pois queremos na ordem crescente, caso contrario seria decrescente.
            for(Voluntario exis : ranking){
                System.out.println(exis);
            }
        }
    }
}
