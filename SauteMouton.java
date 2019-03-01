package fr.eni.tp.tableaux.sautemouton;
import java.util.Scanner;
/**
 * Correction SauteMouton.java
 * @author fbourell2018
 *
 */
public class TP_Tableau_SauteMouton {
	public static char moutonGauche = 'm';
	public static char moutonDroite = 'n';
	public static char placeLibre = '_';
	public static char[] tabMoutons = { moutonGauche, moutonGauche, moutonGauche, placeLibre, moutonDroite,
			moutonDroite, moutonDroite };
	public static Scanner scan = new Scanner(System.in);
	public static int gameover = -1;

	public static void main(String[] args) {
		// final int fin = -1;
		// bouclesPair();
		// boucleCentre();
		// bouclesImpair();
		System.out.println(tabMoutons.length / 2);

		while (gameover < 0) {
			afficheTabMouton();

			System.out.println(
					"Choisissez un chiffre entre 0 et " + (tabMoutons.length - 1) + " pour déplacer un mouton");
			int choix = scan.nextInt();
			scan.nextLine();

			while (choix < 0 || choix >= tabMoutons.length) {
				System.out.println(
						"Choisissez un chiffre entre 0 et " + (tabMoutons.length - 1) + " pour déplacer un mouton");
				choix = scan.nextInt();
				scan.nextLine();
			}

			if (tabMoutons[choix] == moutonGauche) {
				if (choix + 1 < tabMoutons.length || choix + 2 < tabMoutons.length) {
					if (tabMoutons[choix + 1] == placeLibre) {
						tabMoutons[choix + 1] = tabMoutons[choix];
						tabMoutons[choix] = placeLibre;
						isWin();
						isGameOver(choix);
						// isGameOverGauche(choix);
					} else if (tabMoutons[choix + 2] == placeLibre) {
						tabMoutons[choix + 2] = tabMoutons[choix];
						tabMoutons[choix] = placeLibre;
						isWin();
						isGameOver(choix);
						// isGameOverGauche(choix);
					} else {
						System.out.println("Ce mouton ne peut pas se déplacer");
					}
				} else {
					System.out.println("Ce mouton ne peut pas se déplacer");
				}
			} else if (tabMoutons[choix] == placeLibre) {
				System.out.println("Ce n'est pas un mouton");
			} else {
				if (choix - 1 >= 0 || choix - 2 > 0) {
					if (tabMoutons[choix - 1] == placeLibre) {
						tabMoutons[choix - 1] = tabMoutons[choix];
						tabMoutons[choix] = placeLibre;
						isWin();
						isGameOver(choix);
						// isGameOverDroite(choix);
					} else if (tabMoutons[choix - 2] == placeLibre) {
						tabMoutons[choix - 2] = tabMoutons[choix];
						tabMoutons[choix] = placeLibre;
						isWin();
						isGameOver(choix);
						// isGameOverDroite(choix);
					} else {
						System.out.println("Ce mouton ne peut pas se déplacer");
					}
				} else {
					System.out.println("Ce mouton ne peut pas se déplacer");
				}
			}
		}

		afficheTabMouton();
		if (gameover == 1) {
			System.out.println("Vous avez gagné !!");
		} else {
			System.out.println("Vous avez perdu !!");
		}

		scan.close();
	}

	/**
	 * Teste si l'utilisateur a gagné
	 */
	public static void isWin() {
		if (tabMoutons[0] == moutonDroite && tabMoutons[1] == moutonDroite && tabMoutons[2] == moutonDroite
				&& tabMoutons[4] == moutonGauche && tabMoutons[5] == moutonGauche && tabMoutons[6] == moutonGauche) {
			gameover = 1;
		}
	}

	/**
	 * Vérifie si les déplacements ne sont plus possibles
	 * 
	 * @param choix
	 *            la dernière case qui est vide
	 */
	public static void isGameOver(int choix) {
		if (gameover != 1) {
			if (choix > tabMoutons.length / 2) {
				if (tabMoutons[choix - 1] == moutonDroite && tabMoutons[choix - 2] == moutonDroite
						&& tabMoutons[choix - 3] == moutonDroite)
					gameover = 0;
			} else if (choix < tabMoutons.length / 2) {
				if (tabMoutons[choix + 1] == moutonGauche && tabMoutons[choix + 2] == moutonGauche
						&& tabMoutons[choix + 3] == moutonGauche)
					gameover = 0;
			} else {
				if (tabMoutons[choix + 1] == moutonGauche && tabMoutons[choix + 2] == moutonGauche
						&& tabMoutons[choix - 1] == moutonDroite && tabMoutons[choix - 2] == moutonDroite)
					gameover = 0;
			}
		}
	}

	/**
	 * Teste si l'utilisateur a perdu lorsqu'il déplace un mouton moutonGauche
	 * 
	 * @param {@code choix} est la case qui est vide placeLibre
	 */
	public static void isGameOverGauche(int choix) {
		if (gameover != 1) {
			if (choix == 0) {
				if (tabMoutons[choix + 1] == moutonGauche && tabMoutons[choix + 2] == moutonGauche)
					gameover = 0;
			} else if (choix > 1 && choix < tabMoutons.length - 2) {
				if (tabMoutons[choix + 1] == moutonGauche && tabMoutons[choix + 2] == moutonGauche
						&& tabMoutons[choix - 1] == moutonDroite && tabMoutons[choix - 2] == moutonDroite)
					gameover = 0;
			}
		}
	}

	/**
	 * Teste si l'utilisateur a perdu lorsqu'il déplace un mouton moutonDroite
	 * 
	 * @param {@code choix} est la case qui est vide placeLibre
	 */
	public static void isGameOverDroite(int choix) {
		if (gameover != 1) {
			if (choix == tabMoutons.length - 1) {
				if (tabMoutons[choix - 1] == moutonDroite && tabMoutons[choix - 2] == moutonDroite)
					gameover = 0;
			} else if (choix > 1 && choix < tabMoutons.length - 2) {
				if (tabMoutons[choix + 1] == moutonGauche && tabMoutons[choix + 2] == moutonGauche
						&& tabMoutons[choix - 1] == moutonDroite && tabMoutons[choix - 2] == moutonDroite)
					gameover = 0;
			}
		}
	}

	/**
	 * Affiche le tableau de moutons
	 */
	public static void afficheTabMouton() {
		for (int i = 0; i < tabMoutons.length; i++) {
			System.out.print(i);
		}
		System.out.println();
		for (char mouton : tabMoutons) {
			System.out.print(mouton);
		}
		System.out.println();
	}

	/*
	 * 1er travail, résolution barbare par l'ordi
	 */

	public static void boucleCentre() {
		int j1 = tabMoutons.length;
		while (tabMoutons[j1 - 2] != placeLibre) {
			j1--;
		}
		tabMoutons[j1 - 2] = tabMoutons[j1];
		tabMoutons[j1] = placeLibre;
		afficheTabMouton();
	}

	public static void bouclesPair() {
		// Boucle 1
		int j1 = 0;
		while (tabMoutons[j1 + 1] != placeLibre) {
			j1++;
		}
		tabMoutons[j1 + 1] = tabMoutons[j1];
		tabMoutons[j1] = placeLibre;
		afficheTabMouton();
		tabMoutons[j1] = tabMoutons[j1 + 2];
		tabMoutons[j1 + 2] = placeLibre;
		afficheTabMouton();

		// Boucle 2
		int j2 = tabMoutons.length;
		while (tabMoutons[j2 - 1] != placeLibre) {
			j2--;
		}
		tabMoutons[j2 - 1] = tabMoutons[j2];
		tabMoutons[j2] = placeLibre;
		afficheTabMouton();
		tabMoutons[j2] = tabMoutons[j2 - 2];
		tabMoutons[j2 - 2] = placeLibre;
		afficheTabMouton();

		// Boucle 3
		int j3 = 0;
		while (tabMoutons[j3 + 2] != placeLibre) {
			j3++;
		}
		tabMoutons[j3 + 2] = tabMoutons[j3];
		tabMoutons[j3] = placeLibre;
		afficheTabMouton();
		tabMoutons[j3] = tabMoutons[j3 - 1];
		tabMoutons[j3 - 1] = placeLibre;
		afficheTabMouton();

		// Boucle 4
		int j4 = tabMoutons.length;
		while (tabMoutons[j4 - 2] != placeLibre) {
			j4--;
		}
		tabMoutons[j4 - 2] = tabMoutons[j4];
		tabMoutons[j4] = placeLibre;
		afficheTabMouton();
	}

	public static void bouclesImpair() {
		// boucle 1
		int j1 = tabMoutons.length;
		while (tabMoutons[j1 - 2] != placeLibre) {
			j1--;
		}
		tabMoutons[j1 - 2] = tabMoutons[j1];
		tabMoutons[j1] = placeLibre;
		afficheTabMouton();
		tabMoutons[j1] = tabMoutons[j1 - 1];
		tabMoutons[j1 - 1] = placeLibre;
		afficheTabMouton();

		// boucle 2
		int j2 = 0;
		while (tabMoutons[j2 + 2] != placeLibre) {
			j2++;
		}
		tabMoutons[j2 + 2] = tabMoutons[j2];
		tabMoutons[j2] = placeLibre;
		afficheTabMouton();
		tabMoutons[j2] = tabMoutons[j2 + 2];
		tabMoutons[j2 - 2] = placeLibre;
		afficheTabMouton();

		// boucle 3
		int j3 = tabMoutons.length;
		while (tabMoutons[j3 - 1] != placeLibre) {
			j3--;
		}
		tabMoutons[j3 - 1] = tabMoutons[j3];
		tabMoutons[j3] = placeLibre;
		afficheTabMouton();
		tabMoutons[j3] = tabMoutons[j3 + 2];
		tabMoutons[j3 + 2] = placeLibre;
		afficheTabMouton();

		// boucle 4
		int j4 = 0;
		while (tabMoutons[j4 + 1] != placeLibre) {
			j4++;
		}
		tabMoutons[j4 + 1] = tabMoutons[j4];
		tabMoutons[j4] = placeLibre;
		afficheTabMouton();
	}

}
