package scenario;

import personnages.Gaulois;
import produit.Poisson;
import produit.Produit;
import produit.Sanglier;
import villagegaulois.Etal;
import villagegaulois.IEtal;
import villagegaulois.IVillage;

public class Scenario {

    public static void main(String[] args) {
        IEtal[] marche = new IEtal[3];

        IVillage village = new IVillage() {
            public <P extends Produit> boolean installerVendeur(
                Etal<P> etal,
                Gaulois vendeur,
                P[] produit,
                int prix
            ) {
                int i = 0;
                while (marche[i] != null) {
                    if (++i == marche.length) {
                        return false;
                    }
                }
                marche[i] = etal;
                etal.installerVendeur(vendeur, produit, prix);
                return true;
            }

            public void acheterProduit(String produit, int quantiteSouhaitee) {
                int i = 0;
                // int prix = 0;
                int besoin = quantiteSouhaitee;
                while (besoin > 0 && i < marche.length) {
                    int nb = marche[i].contientProduit(produit, besoin);
                    if (nb != 0) {
                        besoin -= nb;
                        int prixActuel = marche[i].acheterProduit(nb);
                        // prix += prixActuel;
                        System.out.println(
                            "À l’étal n°" +
                                (i + 1) +
                                ", j’achète " +
                                nb +
                                " sangliers et je paye " +
                                prixActuel +
                                " sous."
                        );
                    }
                    ++i;
                }
                System.out.println(
                    "Je voulais " +
                        quantiteSouhaitee +
                        " sangliers, j’en ai acheté " +
                        (quantiteSouhaitee - besoin) +
                        "."
                );
            }

            @Override
            public String toString() {
                StringBuilder s = new StringBuilder();

                for (IEtal etal: marche) {
                    s.append(etal.etatEtal());
                }
                
                return s.toString();
            }
        };

        // fin

        Gaulois ordralfabetix = new Gaulois("Ordralfabétix", 9);
        Gaulois obelix = new Gaulois("Obélix", 20);
        Gaulois asterix = new Gaulois("Astérix", 6);

        Etal<Sanglier> etalSanglierObelix = new Etal<>();
        Etal<Sanglier> etalSanglierAsterix = new Etal<>();
        Etal<Poisson> etalPoisson = new Etal<>();

        Sanglier sanglier1 = new Sanglier(2000, obelix);
        Sanglier sanglier2 = new Sanglier(1500, obelix);
        Sanglier sanglier3 = new Sanglier(1000, asterix);
        Sanglier sanglier4 = new Sanglier(500, asterix);

        Sanglier[] sangliersObelix = { sanglier1, sanglier2 };
        Sanglier[] sangliersAsterix = { sanglier3, sanglier4 };

        Poisson poisson1 = new Poisson("lundi");
        Poisson[] poissons = { poisson1 };

        village.installerVendeur(
            etalSanglierAsterix,
            asterix,
            sangliersAsterix,
            10
        );
        village.installerVendeur(
            etalSanglierObelix,
            obelix,
            sangliersObelix,
            8
        );
        village.installerVendeur(etalPoisson, ordralfabetix, poissons, 5);

        System.out.println(village);

        village.acheterProduit("sanglier", 3);

        System.out.println(village);
    }
}
