package villagegaulois;

import personnages.Gaulois;
import produit.IProduit;
import produit.Produit;
import villagegaulois.IEtal;

public class Etal<P extends IProduit> implements IEtal {

    private Gaulois vendeur;
    private P[] produits;
    private int nbProduit = 0;
    private int quantiteDebutMarche;
    private int quantite;
    private int prix;
    private boolean etalOccupe = false;

    public boolean isEtalOccupe() {
        return etalOccupe;
    }

    public Gaulois getVendeur() {
        return vendeur;
    }

    public int getQuantite() {
        return quantite;
    }

    public P[] getProduits() {
        return produits;
    }

    public Etal() {
        
    }
    
    public void installerVendeur(Gaulois vendeur, P[] produit, int prix) {
        this.vendeur = vendeur;
        produits = produit;
        this.prix = prix;
        quantite = produit.length;
        nbProduit = produit.length;
        quantiteDebutMarche = produit.length;
    }

    // public void occuperEtal(Gaulois vendeur, Produit produit, int quantite) {
    // 	this.vendeur = vendeur;
    // 	this.produits = produit;
    // 	this.quantite = quantite;
    // 	quantiteDebutMarche = quantite;
    // 	etalOccupe = true;
    // }

    // public boolean contientProduit(Produit produit) {
    // 	return this.produit.equals(produit);
    // }

    // public int acheterProduit(int quantiteAcheter) {
    //     if (quantite == 0) {
    //         quantiteAcheter = 0;
    //     }
    //     if (quantiteAcheter > quantite) {
    //         quantiteAcheter = quantite;
    //     }
    //     if (etalOccupe) {
    //         quantite -= quantiteAcheter;
    //     }
    //     return quantiteAcheter;
    // }

    public void libererEtal() {
        etalOccupe = false;
    }

    /**
     *
     * @return donneesVente est un tableau de chaine contenant [0] : un boolean
     *         indiquant si l'étal est occupé [1] : nom du vendeur [2] : produit
     *         vendu [2] : quantité de produit à vendre au début du marché [4] :
     *         quantité de produit vendu
     */
    @Override
    public int contientProduit(String produit, int quantiteSouhaitee) {
        int quantiteAVendre = 0;
        if (nbProduit != 0 && this.produits[0].getNom().equals(produit)) {
            if (nbProduit >= quantiteSouhaitee) {
                quantiteAVendre = quantiteSouhaitee;
            } else {
                quantiteAVendre = nbProduit;
            }
        }
        return quantiteAVendre;
    }

    @Override
    public int acheterProduit(int quantiteSouhaite) {
        int prixPaye = 0;
        for (
            int i = nbProduit - 1;
            i > nbProduit - quantiteSouhaite - 1 || i > 1;
            i--
        ) {
            prixPaye += produits[i].calculerPrix(prix); //question 3.d
        }
        if (nbProduit >= quantiteSouhaite) {
            nbProduit -= quantiteSouhaite;
        } else {
            nbProduit = 0;
        }
        return prixPaye;
    }

    @Override
    public String etatEtal() {
        StringBuilder chaine = new StringBuilder(vendeur.getNom());
        if (nbProduit > 0) {
            chaine.append(" vend ");
            chaine.append(nbProduit + " produits :");
            for (int i = 0; i < nbProduit; i++) {
                chaine.append("\n- " + produits[i].decrireProduit());
            }
        } else {
            chaine.append(" n'a plus rien à vendre.");
        }
        chaine.append("\n");
        return chaine.toString();
    }
}
