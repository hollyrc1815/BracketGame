
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Team extends Tournament {
	static ArrayList<Team> teamsArr = new ArrayList<>();

	public Team(String name, int seed) {
		super(name, seed);
		this.setName(name);
		this.setSeed(seed);
	}

	public static boolean tracker = false;

	public static int menu() {
		while (tracker) {
			tracker = true;
			System.out.println("Welcome to the tournament. Please Select from the following options: ");
		}

		Scanner scanner = new Scanner(System.in);
		System.out.println();
		System.out.println("Enter a number to choose an option:");
		System.out.println("1. Create Teams");
		System.out.println("2. List Teams");
		System.out.println("3. See Game");
		System.out.println("4. Exit");
		System.out.println("5. Remove All Teams");
		System.out.println();

		// makes sure we have an integer
		while (!scanner.hasNextInt()) {
			System.out.println("Your entry is not valid. Please chose a number between 1-4.");
			menuCheck(menu());

		}
		int reply = scanner.nextInt();
		return reply;
	}

	// logic for checking menu
	public static void menuCheck(int reply) {
		if (reply == 1) {
			createTeams();
			menuCheck(menu());
		} else if (reply == 2) {
			if (teamsArr.size() == 0) {
				Scanner scanner = new Scanner(System.in);
				System.out
						.println("You do not have any teams to list. Would you like to add teams? \n 1. Yes \n 2. No");
				String input = scanner.nextLine();
				if (input.equals("1") || input.equalsIgnoreCase("yes")) {
					createTeams();
					listTeams(teamsArr);
					menuCheck(menu());
				} else {
					menuCheck(menu());
				}

				;
			} else {
				listTeams(teamsArr);
				menuCheck(menu());
			}

		} else if (reply == 3) {
			if (teamsArr.size() == 0) {
				createTeams();
				listTeams(teamsArr);
				menuCheck(menu());
			} else {
				teamMatch(teamsArr);
				menuCheck(menu());
			}
		} else if (reply == 4) {
			System.out.println();
			System.out.println("Goodbye");

		} else if (reply == 5) {
			clearAll(teamsArr);
			menuCheck(menu());

		} else {
			while (reply != 1 || reply != 2 || reply != 3 || reply != 4 || reply != 5) {
				System.out.println("Your option is invalid");
				System.out.println();
				menu();
			}

		}
	}

	// method for listing teams
	public static void listTeams(ArrayList arr) {
		for (int i = 0; i < arr.size(); i++) {
			System.out.println("Team: " + ((Team) arr.get(i)).getName() + " rank: " + ((Team) arr.get(i)).getSeed());
		}
	}

	// checks for an odd number of teams
	public static Team isThisOdd(ArrayList arr) {
		Team oddTeam = null;
		if (arr.size() % 2 != 0) {
			oddTeam = (Team) arr.get(0);
			arr.remove(0);
			System.out.println('\n');
			System.out.println("Since there is an odd number of teams, Team " + oddTeam.getName() + " gets by week");
		}
		return oddTeam;
	}

	// method for matching teams, best to worst, and 2 to 3;
	public static void teamMatch(ArrayList arr) {
		ArrayList<Team> winners = new ArrayList<>();
		if (arr.size() == 1) {
			System.out.println("Team " + ((Team) arr.get(0)).getName() + " wins by default...LAAAAMMMME!");
		} else if (arr.size() < 5) {
			for (int i = 0; i < arr.size(); i++) {
				Team winner = playRound(((Team) arr.get(0)), ((Team) arr.get(arr.size() - 1)));
				System.out.println("Team " + ((Team) arr.get(0)).getName() + " vs " + " Team "
						+ ((Team) arr.get(arr.size() - 1)).getName());
				System.out.println("Winner of this round is " + winner.getName());
				winners.add(winner);
				teamsArr.remove(0);
				teamsArr.remove(arr.size() - 1);
			}

		} else {
			// checks for odd numbers
			Team oddTeam = isThisOdd(arr);

			// plays first and last teams and returns winner as long as there's a minimum of
			// 2 teams
			// in the teamsArr.

			int loopCounter = 0;
			while (arr.size() > 1) {
				Team t1 = (Team) arr.get(0);
				Team t2 = (Team) arr.get(arr.size() - 1);
				// testing start
				System.out.println("iterator is : " + loopCounter);
				System.out.println("team arrray size  is : " + arr.size());
				System.out.println("winnter arrray size  is : " + winners.size());
				System.out.println("t1 and t2 is" + t1.getName() + " " + t2.getName());
				// testing end
				winners.add(playRound(t1, t2));// this method is the logic for finding winner per round.
				// prints out the round, shows team vs team and the outcome of that game
				// once the team plays against each other, they are removed
				// from the teamsArr and stored in winnersArr.
				System.out.println("round " + (loopCounter + 1));
				System.out.println("Team " + t1.getName() + " vs Team " + t2.getName());
				System.out.println("And the  winner is : " + winners.get((winners.size() - 1)).getName() + '\n');
				arr.remove(0); // removes 1st team.
				arr.remove(arr.get(arr.size() - 1)); // removes last team.
				//////////////////////// testing start/////////////////////////
				System.out.println("this is the end of the for loop,  teams array size is now: " + arr.size());
				System.out.println("this is the end of the for loop,  winners array size is now: " + winners.size()
						+ " " + winners.get(winners.size() - 1).getName());
				//////////////////////// testing end///////////////////////////
				loopCounter++;
				// this is a test
				// if there is a by team, this puts that team back into the game
				// after 1 round has been played.
				if (oddTeam != null) {
					// because we want the best to play against the worst
					// this 'if' should provide a way for the winner of the
					// last round to play against the by week team.
					System.out.println("the odd team if statement: " + oddTeam.getName());
					System.out.println();
					winners.add(0, oddTeam);
					oddTeam = null;
					//////////////////////// testing start/////////////////////////
					System.out.println("inside the of team if statement... teams array size is now: " + arr.size());
					System.out.println("inside the of team if statement... winners array size is now: " + winners.size()
							+ " " + winners.get(0).getName());
					//////////////////////// testing end///////////////////////////
					Object oddWinner = playRound(t1, t2);
					winners.clear();
					winners.add((Team) oddWinner);
					//////////////////////// testing start/////////////////////////
					System.out.println("inside the of team if statement... teams array size is now: " + arr.size());
					System.out.println("inside the of team if statement... winners array size is now: " + winners.size()
							+ " " + winners.get(0).getName());
					//////////////////////// testing end///////////////////////////
				}
				// this is the end of test
			}

			// tournament rounds
			int i = 0;
			Team champ = null;
			while (winners.size() > 2) {
				i++;
				//////////////////////// testing start/////////////////////////
				System.out.println(
						"inside the of tournament if statement before the game is played... winners array size is now: "
								+ winners.size());
				System.out.println("inside the of tournament if statement before the game is played... teams : "
						+ ((Team) winners.get(0)).getName() + " " + ((Team) winners.get(winners.size() - 1)).getName());
				System.out.println("inside the of tournament if statement... winners array size is now: "
						+ winners.size() + " " + winners.get(0).getName());
				//////////////////////// testing end///////////////////////////
				champ = playRound(winners.get(0), winners.get(winners.size() - 1));
				winners.remove(0);
				winners.remove(winners.size() - 1);
				winners.add(1, champ);
				System.out.println("Tournament Round " + i + " the winner is " + champ.getName()
						+ " and the array size is " + winners.size());
				System.out.println();
				//////////////////////// testing start/////////////////////////
				System.out.println("inside the of tournament if statement... teams array size is now: " + arr.size());
				System.out.println("inside the of tournament if statement... teams : "
						+ ((Team) winners.get(0)).getName() + " " + ((Team) winners.get(winners.size() - 1)).getName());
				System.out.println("inside the of tournament if statement... winners array size is now: "
						+ winners.size() + " " + winners.get(0).getName());
				//////////////////////// testing end///////////////////////////
			}

			// System.out.println("Team " + champ.getName());
			System.out.println("Final Game: '\n' Team " + winners.get(0).getName() + " vs Team "
					+ winners.get(winners.size() - 1).getName());
			;
			Team winnerOfTournament = playRound(winners.get(0), winners.get(winners.size() - 1));
			// System.out.println("The tournament champion is " + winners.get(0).getName());
			System.out.println("The tournament winner is " + winnerOfTournament.getName());
			System.out.println();
		}
	}

// dynamically allows the user to create an arrayList of team instances and assign names and ranking.
	public static void createTeams() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("How many teams would you like to create? ");
		System.out.println();

		while (!scanner.hasNextInt()) {
			System.out.println("Invalid character. Please enter numeric values only");
			createTeams();
		}
		int amount = scanner.nextInt();
		int loopCounter = 0;

		for (int i = 0; i < amount; i++) {
			loopCounter++;
			Scanner scanner2 = new Scanner(System.in);
			System.out.println("Type in team # " + loopCounter + ",  name: ");
			System.out.println();
			String teamName = scanner2.nextLine();
//////////////////////start test/////////////////////
			int rank = 0;
			boolean isNum = false;
			while (!isNum)
				try {
					System.out.println("2.Set team # " + loopCounter + "'s rank: ");
					rank = scanner2.nextInt();
					scanner2.nextLine();
					isNum = true;
					// numeric value entered, so break the while loop
				} catch (Exception mychecker) {
					// Display Error message
					System.out.println("Invalid character found,Please enter numeric values only !!");
					scanner2.nextLine();// Advance the scanner
				} finally {

				}
//////////////////////////end test//////////////////
			teamsArr.add(new Team(teamName, rank));
			Collections.sort(teamsArr, new Comparator<Team>() {
				public int compare(Team t1, Team t2) {
					return Integer.valueOf(t1.getSeed()).compareTo(t2.getSeed());
				}
			});
		}

	}

	// gives two teams a random score and returns the winner
	// gets called by teamMatch method
	public static Team playRound(Team t1, Team t2) {
		((Team) t1).setScore(randomNum());
		((Team) t2).setScore(randomNum());
		if (t1.getScore() > t2.getScore()) {
			t1.setWins(t1.getWins() + 1);
			return t1;
		} else {
			t2.setWins(t2.getWins() + 1);
			return t2;
		}
	}

	// clear array
	public static void clearAll(List arr) {
		arr.clear();
		System.out.println(" \n All teams are cleared");
	}

	public static void main(String[] arg) {
		menuCheck(menu());
	}
}