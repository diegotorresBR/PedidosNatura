package WebService;

import java.io.Serializable;

/**
 * Created by diego on 01/12/15.
 */
public class Cupom implements Serializable {

    private int id;
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
