
import java.util.Random;

// methods
//	-create the teams
//		- scanner to take user input
//	-list out teams
//		-System.out.prinln()
//	-pair the teams 1&4 and 2&3 and determine winner
// 		-team1.gertRanking();
// 		- randomize winner
//	-exit the program
//		-System.out.println("Thanks for playing")
//		-break out of loop

// noticeable actions
// 	-loop to create

public class Tournament {

	private String name;
	private int wins;
	private int score;
	private int seed;

	public Tournament(String name, int seed) {
		this.name = name;
		this.seed = seed;
	}

	// getters
	public String getName() {
		return name;
	}

	public int getWins() {
		return wins;
	}

	public int getSeed() {
		return seed;
	}

	public int getScore() {
		return score;
	}

	// setters
	public Tournament setName(String name) {
		this.name = name;
		return this;
	}

	public Tournament setWins(int wins) {
		this.wins = wins;
		return this;
	}

	public Tournament setSeed(int seed) {
		this.seed = seed;
		return this;
	}

	public Tournament setScore(int score) {
		this.score = score;
		return this;
	}

	public static int randomNum() {
		Random random = new Random();
		return random.nextInt(100);
	}

	public static void main(String[] arg) {
		// System.out.println(randomNum());
//		Team michigan = new Team("Michigan", 0);
//		Team wisconson = new Team("Wisconson", 0);
//		System.out.println(michigan.getName());
//		System.out.println(michigan.getSeed());
	}
}