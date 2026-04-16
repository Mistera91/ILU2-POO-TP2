package produit;


public class Poisson extends Produit {
    private String date;
    private String nom = "poisson";
    protected Unit unit = Unit.piece;
    
    public Poisson(String date) {
        this.date = date;
    }
    
    public String getNom() {
        return nom;
    }
    
    public String decrireProduit() {
        return nom + " pêché " + date + ".";
    }
}