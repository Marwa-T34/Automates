import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Automate {

    // Définition de l'alphabet :
    private static final char[] ALPHABET = {'a', 'b', 'c'}; 

    // Définition des états en utilisant des étiquettes String :
    private static final String ETAT_INITIAL = "1";
    private static final String ETAT_FINAL = "2";

    public static void main(String[] args) {
        int choix;
        // Création de la table de transitions en utilisant HashMap
        Map<String, Map<Character, String>> transition = new HashMap<>();

        // Initialiser les transitions pour chaque état
        transition.put("1", new HashMap<>());
        transition.put("2", new HashMap<>());
        transition.put("3", new HashMap<>());

        // Définir les transitions pour chaque état et chaque caractère de l'alphabet
        // Transitions pour l'état 1 :
        transition.get("1").put('a', "2");
        transition.get("1").put('b', "1");
        transition.get("1").put('c', "3");

        // Transitions pour l'état 2 :
        transition.get("2").put('a', "2");
        transition.get("2").put('b', "3");
        transition.get("2").put('c', "1");

        // Transitions pour l'état 3 :
        transition.get("3").put('a', "1");
        transition.get("3").put('b', "3");
        transition.get("3").put('c', null);  // Pas de transition valide avec 'c'

    
        // Demander à l'utilisateur de saisir un mot:
        do{
        Scanner read = new Scanner(System.in);
        System.out.println("Entrez un mot pour vérifier s'il est accepté par l'automate :");
        String word = read.nextLine();

        if (estAccepte(transition, word)) {
            System.out.println("Le mot \"" + word + "\" est accepté.");
        } else {
            System.out.println("Le mot \"" + word + "\" n'est pas accepté.");
        }
        
        System.out.println("\nSi vous souhaitez continuer,entrez '1' sionon entrez '0':");
        choix = read.nextInt();
    }while(choix==1);
}

    // Méthode pour vérifier si le caractère est dans l'alphabet
    private static boolean isInAlphabet(char c) {
        for (char symbol : ALPHABET) {
            if (c == symbol) {
                return true;
            }
        }
        return false;
    }

    // Méthode pour vérifier si un mot est accepté par l'automate :
    private static boolean estAccepte(Map<String, Map<Character, String>> transition, String mot) {
        String etatCourant = ETAT_INITIAL;

        // Itérer à travers chaque caractère du mot
        for (char c : mot.toCharArray()) {
            if (!isInAlphabet(c)) {
                return false;
            }

            String etatSuivant = transition.get(etatCourant).get(c);

            if (etatSuivant == null) {
                return false; // Pas de transition valide pour ce caractère
            }

            etatCourant = etatSuivant; // Passer à l'état suivant
        }

        return etatCourant.equals(ETAT_FINAL); // Vérifier si l'état final est atteint
    }
}

