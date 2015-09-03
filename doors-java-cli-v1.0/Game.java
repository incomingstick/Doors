import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * 
 * @author Nick Gaulke Nov 11, 2014 FinalProject
 * 
 */
public class Game {

	private static Player player;
	private static Scanner in;

	private static String[] argsInternal;
	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws InterruptedException,
			IOException, ClassNotFoundException {
		argsInternal = args;
		in = new Scanner(System.in);
		System.out.println("~~Welcome to Doors~~");
		player = Command.start(in, player);
		if (player == null) {
			System.out.println("Player is null. This should be impossible.");
			System.exit(1);
		}
		run();
	}
	
	public static void run() throws InterruptedException, ClassNotFoundException, IOException {
		while (true) {
			System.out.print("Loading Doors.");
			Thread.sleep(1000);
			System.out.print(".");
			Thread.sleep(1000);
			System.out.println(".");
			Thread.sleep(1000);
			Doors.show();
			in.reset();
			Command.doors(in, player);
		}
	}

	/**
	 * @return the player
	 */
	public static Player getPlayer() {
		return player;
	}

	/**
	 * Fix for nullPointer when setting the player
	 * 
	 * @param name
	 * @param command
	 * @throws InterruptedException
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void setPlayer(String name, Scanner command)
			throws InterruptedException, ClassNotFoundException, IOException {
		player = new Player(name, command);
	}

	/**
	 * Loads the player in from a saved .ser file
	 * 
	 * @param name
	 * @param command
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 */
	public static Player loadGame(String file) throws ClassNotFoundException,
			IOException, InterruptedException {
		File check = new File(System.getProperty("user.dir")+"/door_saves/" + file
				+ ".ser");
		if(!check.exists()) {
			System.out.println("No game file found or game is corrupted.");
			main(argsInternal);
		}
		FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir")+"/door_saves/" + file
				+ ".ser");
		ObjectInputStream input = new ObjectInputStream(fileIn);
		player = (Player) input.readObject();
		player.setScanner(in);
		setPlayer(player);
		player.resetRandom();
		input.close();
		fileIn.close();
		return player;
	}

	/**
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static Player pickSaveFile() throws ClassNotFoundException,
			IOException, InterruptedException {
		File filepath = new File(System.getProperty("user.dir")
				+ "/door_saves/");
		File[] saveGames = filepath.listFiles();
		if (saveGames != null && saveGames.length > 0) {
			System.out.println("Here are the save games to choose from: ");
			for (File save : saveGames) {
				System.out.println("     "
						+ save.getName().substring(0,
								save.getName().length() - 4));
			}
			System.out.println("Please select a game to load.");
			System.out.print("Command: ");
			if (in.hasNext()) {
				String input = in.next();
				for (File save : saveGames) {
					if (input.equalsIgnoreCase(save.getName().substring(0,
							save.getName().length() - 4))) {
						return loadGame(save.getName().substring(0,
								save.getName().length() - 4));
					}
				}
				if (input.equalsIgnoreCase("help")) {
					System.out
							.println("Please enter the name of the file you would like to play.");
					in.nextLine();
					return pickSaveFile();
				} else if (input.equalsIgnoreCase("exit")
						|| input.equalsIgnoreCase("quit")) {
					System.exit(0);
				}
			} else {
				in.nextLine();
				return pickSaveFile();
			}
		} else {
			System.out.println("There are no save games available!");
			return Command.start(in, player);
		}
		return player;
	}

	public static void saveGame() throws IOException {
		String filepath = System.getProperty("user.dir");
		if (!new File(filepath + "/door_saves").exists()) {
			if (new File(filepath + "/door_saves").mkdir()) {
				FileOutputStream fileOut = new FileOutputStream(filepath
						+ "/door_saves/" + player.getName() + ".ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(player);
				System.out.println("Game saved!");
				out.close();
				fileOut.close();
			} else
				System.out
						.println("Error creating save folder! Game not saved.");
		} else {
			FileOutputStream fileOut = new FileOutputStream(filepath
					+ "/door_saves/" + player.getName() + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(player);
			System.out.println("Game saved!");
			out.close();
			fileOut.close();
		}
	}

	/**
	 * @param player
	 *            the player to set
	 */
	public static void setPlayer(Player player) {
		Game.player = player;
	}
}
