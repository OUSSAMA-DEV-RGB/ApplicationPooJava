import java.util.Scanner;

public class GestionStock {
    private static final int MAX_PRODUITS = 100;
    private static Produit[] produits = new Produit[MAX_PRODUITS];
    private static int nombreProduits = 0;

    public static void printMenu() {
        System.out.println("\nMenu de Gestion de Stock");
        System.out.println("1. Ajouter un produit");
        System.out.println("2. Modifier un produit");
        System.out.println("3. Supprimer un produit");
        System.out.println("4. Afficher la liste des produits");
        System.out.println("5. Rechercher un produit");
        System.out.println("6. Calculer la valeur totale du stock");
        System.out.println("7. Quitter");
    }

    public static void ajouterProduit(Produit produit) {
        if (nombreProduits >= MAX_PRODUITS) {
            System.out.println("Erreur: Capacité maximale atteinte.");
            return;
        }
        for (int i = 0; i < nombreProduits; i++) {
            if (produits[i].getCode() == produit.getCode()) {
                System.out.println("Erreur: Code produit déjà existant.");
                return;
            }
        }
        produits[nombreProduits++] = produit;
        System.out.println("Produit ajouté avec succès.");
    }

    public static void modifierProduit(int code, String nouveauNom, int nouvelleQuantite, double nouveauPrix) {
        for (int i = 0; i < nombreProduits; i++) {
            if (produits[i].getCode() == code) {
                produits[i].setNom(nouveauNom);
                produits[i].setQuantite(nouvelleQuantite);
                produits[i].setPrix(nouveauPrix);
                System.out.println("Produit modifié avec succès.");
                return;
            }
        }
        System.out.println("Erreur: Produit introuvable.");
    }

    public static void supprimerProduit(int code) {
        for (int i = 0; i < nombreProduits; i++) {
            if (produits[i].getCode() == code) {
                produits[i] = produits[--nombreProduits];
                produits[nombreProduits] = null;
                System.out.println("Produit supprimé avec succès.");
                return;
            }
        }
        System.out.println("Erreur: Produit introuvable.");
    }

    public static void afficherProduits() {
        if (nombreProduits == 0) {
            System.out.println("Aucun produit en stock.");
        } else {
            for (int i = 0; i < nombreProduits; i++) {
                System.out.println(produits[i]);
            }
        }
    }

    public static void rechercherProduit(String nom) {
        for (int i = 0; i < nombreProduits; i++) {
            if (produits[i].getNom().equalsIgnoreCase(nom)) {
                System.out.println(produits[i]);
                return;
            }
        }
        System.out.println("Erreur: Produit introuvable.");
    }

    public static void calculerValeurStock() {
        double valeurTotale = 0;
        for (int i = 0; i < nombreProduits; i++) {
            valeurTotale += produits[i].valeurTotale();
        }
        System.out.println("Valeur totale du stock: " + valeurTotale);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            printMenu();
            System.out.print("Choix: ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choix) {
                case 1:
                    System.out.print("Code: ");
                    int code = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nom: ");
                    String nom = scanner.nextLine();
                    System.out.print("Quantité: ");
                    int quantite = scanner.nextInt();
                    System.out.print("Prix: ");
                    double prix = scanner.nextDouble();
                    ajouterProduit(new Produit(code, nom, quantite, prix));
                    break;
                case 2:
                    System.out.print("Code du produit à modifier: ");
                    int codeModif = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nouveau nom: ");
                    String nouveauNom = scanner.nextLine();
                    System.out.print("Nouvelle quantité: ");
                    int nouvelleQuantite = scanner.nextInt();
                    System.out.print("Nouveau prix: ");
                    double nouveauPrix = scanner.nextDouble();
                    modifierProduit(codeModif, nouveauNom, nouvelleQuantite, nouveauPrix);
                    break;
                case 3:
                    System.out.print("Code du produit à supprimer: ");
                    int codeSupp = scanner.nextInt();
                    supprimerProduit(codeSupp);
                    break;
                case 4:
                    afficherProduits();
                    break;
                case 5:
                    System.out.print("Nom du produit à rechercher: ");
                    String nomRecherche = scanner.nextLine();
                    rechercherProduit(nomRecherche);
                    break;
                case 6:
                    calculerValeurStock();
                    break;
                case 7:
                    System.out.println("Quitter l'application.");
                    break;
                default:
                    System.out.println("Choix invalide. Réessayez.");
            }
        } while (choix != 7);

        scanner.close();
    }
}
