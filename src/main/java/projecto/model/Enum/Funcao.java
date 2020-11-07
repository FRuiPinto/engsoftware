package projecto.model.Enum;

public enum Funcao {

    AJUNIOR(1,"Analista Junior",20.0),
    ASENIOR(2,"Analista Senior",80.0),
    DJUNIOR(3,"Desenvolvedor Junior",10.0),
    DSENIOR(4,"Desenvolvedor Senior",40.0),
    GPROJETO(5,"Gestor de Projecto",100.0);

    private Integer cod;
    private String nomeFuncao;
    private Double valorHora;

    private Funcao(Integer cod , String nomeFuncao , Double valorHora){
        this.cod = cod;
        this.nomeFuncao = nomeFuncao;
        this.valorHora = valorHora;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getNomeFuncao() {
        return nomeFuncao;
    }

    public void setNomeFuncao(String nomeFuncao) {
        this.nomeFuncao = nomeFuncao;
    }

    public Double getValorHora() {
        return valorHora;
    }

    public void setValorHora(Double valorHora) {
        this.valorHora = valorHora;
    }

    public static Funcao toEnum(Integer cod ){
        if(cod == null)
            return null;
        for(Funcao x : Funcao.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw  new IllegalArgumentException("Id invalido!" + cod);
    }
}
