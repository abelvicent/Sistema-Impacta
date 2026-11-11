package entidades;

public class Voluntario {

    protected String nome;
    protected String email;
    protected String matricula;
    protected int quantidadeAcoes;
    protected double pontuacaoImpacto;

    public Voluntario(String nome, String email, String matricula) {
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.quantidadeAcoes = quantidadeAcoes;
        this.pontuacaoImpacto = pontuacaoImpacto;
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