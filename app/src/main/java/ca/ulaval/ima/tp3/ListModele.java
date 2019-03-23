package ca.ulaval.ima.tp3;

public class ListModele {
    private Integer idModele;
    //private ListMarque InfosMarque;
    private String nomModele;

    public ListModele(Integer idModele, String nomModele) {
        this.idModele = idModele;
        this.nomModele = nomModele;
    }

    public Integer getIdModele() {
        return idModele;
    }

    public String getNomModele() {
        return nomModele;
    }
}
