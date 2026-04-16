package produit;

import personnages.Gaulois;

public class Sanglier extends Produit {
    private int poids;
    private Gaulois chasseur;
    private String nom = "sanglier";
    protected Unit unit = Unit.kg;

    public Sanglier(int poids, Gaulois chasseur) {
        this.poids = poids;
        this.chasseur = chasseur;
    }
    
    public String getNom() {
        return nom;
    }

    public String decrireProduit() {
        return nom + " de " + poids + unit.toString() + " chassé par " + chasseur.getNom() + ".";
    }
    
    public int calculerPrix(int prix) {
        return prix * poids/1000;
    }
}
