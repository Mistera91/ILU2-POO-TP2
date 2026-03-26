package frontiere;

import controleur.ControlAcheterProduit;
import controleur.ControlAfficherMarche;
import controleur.ControlVerifierIdentite;
import villagegaulois.Etal;
import personnages.Gaulois;

public class BoundaryAcheterProduit {
    private ControlAcheterProduit controlAcheterProduit;
    private ControlAfficherMarche controlAfficherMarche;
	private ControlVerifierIdentite controlVerifierIdentite;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
    	StringBuilder question = new StringBuilder();
    	question.append("Êtes-vous :\n");
    	question.append("1 - je veux acheter un produit.\n");
    	question.append("2 - je veux avoir une vue d'ensemble du marché.\n");
    	question.append("3 - quitter l'application.\n");
    	int choixUtilisateur = -1;
    	do {
    		choixUtilisateur = Clavier.entrerEntier(question.toString());
    		switch (choixUtilisateur) {
    		case 1:
                if (!controlVerifierIdentite.verifierIdentite(nomAcheteur)) {
                    System.out.println("Je suis désolée " + nomAcheteur + " mais il faut être un habitant de notre village pour commercer ici.");
                }
    		    String produit = Clavier.entrerChaine("Quel produit voulez-vous acheter ?");
                Gaulois[] gaulois = controlAcheterProduit.rechercherVendeursProduit(produit);
                if (gaulois.length == 0) {
                    System.out.println("Désolé, personne ne vend ce produit au marché.");
                }
                StringBuilder q = new StringBuilder();
                q.append("Chez quel commerçant voulez-vous acheter des fleurs ?\n");
                for (int i = 0; i < gaulois.length; ++i) {
                    q.append(i + 1 + " - " + gaulois[i].getNom());
                }
                int iVendeur = -1;
                do {
                    iVendeur = Clavier.entrerEntier(q.toString());
                } while (iVendeur <= 0 || iVendeur - 1 > gaulois.length);
                Gaulois vendeur = gaulois[iVendeur - 1];
                System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " + vendeur.getNom());
                System.out.println("Bonjour " + nomAcheteur);
                int quantiteAcheter = Clavier.entrerEntier("Combien de " + produit + " voulez-vous acheter ?");
                int quantiteAchetee = controlAcheterProduit.acheterProduit(vendeur.getNom(), quantiteAcheter);
                if (quantiteAchetee == quantiteAcheter) {
                    System.out.println(nomAcheteur + " achète " + quantiteAchetee + " " + produit + " à " + vendeur.getNom());
                } else if (quantiteAchetee == 0) {
                    System.out.println(nomAcheteur + " veut acheter " + quantiteAcheter + " " + produit + ", malheureusement il n’y en a plus !");
                } else {
                    System.out.println(nomAcheteur + " veut acheter " + quantiteAcheter + " " + produit + ", malheureusement " + vendeur.getNom() + " n’en a plus que " + quantiteAchetee + ". " + nomAcheteur + " achète tout le stock de " + vendeur.getNom() + ".");
                }
                break;
    
    		case 2:
                String[] infosMarche = controlAfficherMarche.donnerInfosMarche();
          		if (infosMarche.length == 0) {
                    System.out.println("Le marché est vide, revenez plus tard.");
          		} else {
          		    System.out.println(nomAcheteur + ", vous trouverez au marché");
         			for (int i = 0; i < infosMarche.length;) {
                        System.out.println("- " + infosMarche[i++] + " qui vend " + infosMarche[i++] + " " + infosMarche[i++]);
         			}
          		}
    			break;
    
    		default:
    			System.out
    					.println("Vous devez choisir le chiffre 1 ou 2 !");
    			break;
    		}
    	} while (choixUtilisateur != 1 && choixUtilisateur != 2);
	}
}
