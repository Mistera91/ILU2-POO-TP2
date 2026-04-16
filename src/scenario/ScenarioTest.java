package scenario;

import personnages.Gaulois;
import produit.IProduit;
import produit.Poisson;
import produit.Produit;
import produit.Sanglier;
import villagegaulois.Etal;
import villagegaulois.IEtal;

public class ScenarioTest {

    public static void main(String[] argv) {
        IEtal[] marche = new IEtal[3];
        
        Gaulois ordralfabetix = new Gaulois("Ordralfabétix",9);
        Gaulois obelix = new Gaulois("Obélix",20);
        Gaulois asterix = new Gaulois("Asterix", 6);
        Sanglier sanglier1 = new Sanglier(2000, obelix);
        Sanglier sanglier2 = new Sanglier(1500, obelix);
        Sanglier sanglier3 = new Sanglier(1000, asterix);
        Sanglier sanglier4 = new Sanglier(500, asterix);
        Sanglier[] sangliersObelix = {sanglier1, sanglier2};
        Sanglier[] sangliersAsterix = {sanglier3, sanglier4};
        Poisson poisson1 = new Poisson("lundi");
        Poisson[] poissons = {poisson1};
        
        Etal<Sanglier> etalas = new Etal<Sanglier>();
        etalas.installerVendeur(asterix, sangliersAsterix, 10);
        marche[0] = etalas;
        
        Etal<Sanglier> etalob = new Etal<Sanglier>();
        etalob.installerVendeur(obelix, sangliersObelix, 8);
        marche[1] = etalob;
        
        Etal<Poisson> etalor = new Etal<Poisson>();
        etalor.installerVendeur(ordralfabetix, poissons, 7);
        marche[2] = etalor;
        
        for (IEtal etal: marche) {
            System.out.println(etal.etatEtal());
        }
        
        
        System.out.println("À l’étal n°1, j’achète 2 sangliers et je paye " + marche[0].acheterProduit(2) + " sous.");
        System.out.println("À l’étal n°2, j’achète 1 sanglier et je paye " + marche[1].acheterProduit(1) + " sous.");
        System.out.println("Je voulais 3 sangliers, j’en ai acheté 3.");
        
        for (IEtal etal: marche) {
            System.out.println(etal.etatEtal());
        }
        
        
    }
}
