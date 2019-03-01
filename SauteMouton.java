import java.util.Scanner;

public class SauteMouton {

	public static final int NB_MOUTONS = 3;
	public static final int NB_CASES = 2 * NB_MOUTONS + 1;

	public static void main(String[] args) {

		char[] plateau = new char[NB_CASES];
		initialiser(plateau);
		do {
			afficher(plateau);
			deplacerUnMouton(plateau);
		} while (!gagne(plateau) && !perdu(plateau));
		afficher(plateau);
	}

	public static boolean perdu(char[] plateau) {
		boolean deplPossible;
		int posCaseVide = 0;
		while(plateau[posCaseVide]!=' ')
			posCaseVide++;
		if(posCaseVide>0 && plateau[posCaseVide-1] == '>')
			deplPossible = true;
		else if(posCaseVide>1 && plateau[posCaseVide-1] == '<' &&
				plateau[posCaseVide-2] == '>')
			deplPossible = true;
		else if(posCaseVide<NB_CASES-1 && plateau[posCaseVide+1]== '<')
			deplPossible = true;
		else if(posCaseVide<NB_CASES-2 && plateau[posCaseVide+1]== '>' &&
				plateau[posCaseVide+2]== '<')
			deplPossible = true;
		else
			deplPossible = false;
		if(!deplPossible)
			System.out.println("C'est perdu !");
		return !deplPossible;
	}

	public static boolean gagne(char[] plateau) {
		boolean victoire = true;
		int i=0;
		while(i < NB_MOUTONS && victoire) {
			victoire = plateau[i] == '<';
			i++;
		}
		victoire = victoire && plateau[NB_MOUTONS] == ' ';
		if(victoire)
			System.out.println("Victoire !!!!");
		return victoire;
	}

	private static void deplacerUnMouton(char[] plateau) {
		int numeroCase = saisieEntierEntreBornes(1, NB_CASES, "Quel mouton voulez-vous déplacer ?") - 1;
		switch (plateau[numeroCase]) {
		case ' ':
			System.out.println("Il n'y a pas mouton ici !");
			break;
		case '>':
			if (numeroCase + 1 < NB_CASES && plateau[numeroCase + 1] == ' ') {
				plateau[numeroCase] = ' ';
				plateau[numeroCase + 1] = '>';
			} else if (numeroCase + 2 < NB_CASES && plateau[numeroCase + 1] == '<' && plateau[numeroCase + 2] == ' ') {
				plateau[numeroCase] = ' ';
				plateau[numeroCase + 2] = '>';
			} else {
				System.out.println("Déplacement illégal !");
			}
			break;
		case '<':
			if (numeroCase - 1 >= 0 && plateau[numeroCase - 1] == ' ') {
				plateau[numeroCase] = ' ';
				plateau[numeroCase - 1] = '<';
			} else if (numeroCase - 2 >= 0 && plateau[numeroCase - 1] == '>' && plateau[numeroCase - 2] == ' ') {
				plateau[numeroCase] = ' ';
				plateau[numeroCase - 2] = '<';
			} else {
				System.out.println("Déplacement illégal !");
			}
			break;
		}
	}
	
	

	public static void afficher(char[] plateau) {
		for (int i = 0; i < NB_CASES; i++)
			System.out.print(plateau[i]);
		System.out.println();

	}

	public static void initialiser(char[] plateau) {
		for (int i = 0; i < NB_MOUTONS; i++)
			plateau[i] = '>';
		plateau[NB_MOUTONS] = ' ';
		for (int i = NB_MOUTONS + 1; i < NB_CASES; i++)
			plateau[i] = '<';
	}

	public static Scanner scan = new Scanner(System.in);
	/**
	 * Demande à l'utilisateur de saisir une valeur entière comprise entre
	 * {@code min} et {@code max}
	 * 
	 * @param min
	 *            minimum acceptable (valeur incluse)
	 * @param max
	 *            maximum acceptable (valeur incluse)
	 * @param message
	 *            message à afficher pour inviter l'utilisateur à saisir
	 * @return la valeur saisie par l'utilisateur forcément comprise entre
	 *         {@code min} et {@code max}
	 */
	public static int saisieEntierEntreBornes(int min, int max, String message) {
		System.out.println(message);
		int j = scan.nextInt();
		while (j < min || j > max) {
			System.out.println("Veuillez saisir un nombre compris entre " + min + " et " + max);
			j = scan.nextInt();
		}
		return j;
	}
}
