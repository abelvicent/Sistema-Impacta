package entidades;

public class Voluntario {

    private String nome;
    private String email;
    private String matricula;
    private int quantidadeAcoes;
    private double pontuacaoImpacto;

    public Voluntario(String nome, String email, String matricula) {
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.quantidadeAcoes = 0;
        this.pontuacaoImpacto = 0;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getQuantidadeAcoes() {
        return quantidadeAcoes;
    }

    public double getPontuacaoImpacto() {
        return pontuacaoImpacto;
    }

    public void adicionarAcao(Acao acao) {
        quantidadeAcoes++;
        pontuacaoImpacto += acao.calcularPontuacao();
    }

    @Override
    public boolean equals(Object outro) {
        if (this == outro) {
            return true;
        }
        if (!(outro instanceof Voluntario)) {
            return false;
        }
        Voluntario v = (Voluntario) outro;
        return email.equalsIgnoreCase(v.email);
    }

    @Override
    public int hashCode() {
        return email.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return "Voluntario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", matricula='" + matricula + '\'' +
                ", quantidadeAcoes=" + quantidadeAcoes +
                ", pontuacaoImpacto=" + pontuacaoImpacto +
                '}';
    }
}
