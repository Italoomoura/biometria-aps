package Util;

public class ModelPerson {
    
    private int id;
    private String nome;
    private String sobrenome;
    private String cargo;
    private String data_nasc;

    public ModelPerson() {
    }

    public ModelPerson(int id, String nome, String sobrenome, String cargo, String data_nasc) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cargo = cargo;
        this.data_nasc = data_nasc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }
    
    
}