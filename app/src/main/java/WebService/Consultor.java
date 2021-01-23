package WebService;

import java.io.Serializable;

/**
 * Created by diego on 06/11/15.
 */
public class Consultor implements Serializable {

    private int id;
    private String nome, espaco;

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEspaco(String espaco) {
        this.espaco = espaco;
    }

    public int getId() {
        return id;
    }

    public String getEspaco() {
        return espaco;
    }

    public String getNome() {
        return nome;
    }
}
