package produit;

public abstract class Produit implements IProduit {
    private String nom;
    private Unit unit;

    public String getNom() {
        return nom;
    }

    public abstract String decrireProduit();
    
    public int calculerPrix(int prix) {
        return prix;
    }
}

