package ca.ulaval.ima.tp3;

public class ListMarque {
    private Integer idMarque;
    private String nomMarque;

    public ListMarque(Integer idMarque, String nomMarque) {
        this.idMarque = idMarque;
        this.nomMarque = nomMarque;
    }

    public Integer getIdMarque() {
        return idMarque;
    }

    public String getNomMarque() {
        return nomMarque;
    }
}
