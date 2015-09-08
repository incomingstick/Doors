import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author Nick Gaulke Nov 11, 2014 FinalProject
 * 
 */
public class Command {

	/**
	 * The opening menu used to select a new game or to load a saved file.
	 * Takes "new", "load", "exit", or "quit" as game commands.
	 * New starts a new game. Load will allow the player to return to a saved game.
	 * Exit and Quit will close the application.
	 * @param command
	 * @param player
	 * @return
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static Player start(Scanner command, Player player)
			throws InterruptedException, ClassNotFoundException, IOException {
		System.out
				.println("Would you like to start a new game or load an old game?");
		System.out.print("Command: ");
		if (command.hasNext())
			switch (command.next().toLowerCase()) {
			case "new":
				System.out.print("Enter name: ");
				String name = "";
				if (command.hasNext())
					name = command.next();
				return new Player(name, command);
			case "load":
				return Game.pickSaveFile();
			case "exit":
			case "quit":
				System.exit(0);
			default:
				System.out
						.println("That is an invalid selection.\nType either 'new' or 'load' to continue.");
				command.nextLine();
				return start(command, player);
			}
		else
			return start(command, player);
	}

	/**
	 * Performs level up action on the player class.
	 * The player distributes points across their Strength
	 * Intelligence, and Dexterity attributes to make their
	 * character more powerful. Takes each stat as a command,
	 * along with: str, int, dex, get, show, help, exit, quit.<br>
	 * str = Strength<br>
	 * int = Intelligence<br>
	 * dex = Dexterity<br>
	 * get and show will show the current stat distribution<br>
	 * help shows all available commands <br>
	 * exit and quit will leave the game
	 * 
	 * @param command
	 * @param points
	 * @param player
	 * @throws InterruptedException
	 */
	public static void levelUp(Scanner command, int points, Player player)
			throws InterruptedException {
		if (points <= 0)
			return;
		System.out.println("You have " + points + " points to spend.");
		System.out.println("Where would you like to spend these?");
		System.out.print("Command: ");
		int spent = 0;
		if (command.hasNext()) {
			switch (command.next().toLowerCase()) {
			case "strength":
			case "str":
				if (command.hasNextInt()) {
					spent = command.nextInt();
					if (spent > points)
						spent = points;
					player.setStr(player.getStr() + spent);
				} else {
					System.out
							.println("That is an invalid input.\nType 'help' for more options.");
					command.nextLine();
					levelUp(command, points - spent, player);
					return;
				}
				break;
			case "intelligence":
			case "int":
				if (command.hasNextInt()) {
					spent = command.nextInt();
					if (spent > points)
						spent = points;
					player.setInt(player.getInt() + spent);
				} else {
					System.out
							.println("That is an invalid input.\nType 'help' for more options.");
					command.nextLine();
					levelUp(command, points - spent, player);
					return;
				}
				break;
			case "dexterity":
			case "dex":
				if (command.hasNextInt()) {
					spent = command.nextInt();
					if (spent > points)
						spent = points;
					player.setDex(player.getDex() + spent);
				} else {
					System.out
							.println("That is an invalid input.\nType 'help' for more options.");
					command.nextLine();
					levelUp(command, points - spent, player);
					return;
				}
				break;
			case "get":
			case "show":
				if (command.hasNext())
					switch (command.next().toLowerCase()) {
					case "stats":
					case "statistics":
						System.out.println();
						System.out.println("Name: " + player.getName());
						System.out.println("Level: " + player.getLevel());
						System.out
								.println("Current Health: "
										+ player.getHP()
										+ "/"
										+ player.getMAX_HP()
										+ " ("
										+ Math.round((((double) player.getHP() / (double) player
												.getMAX_HP()) * 100)) + "%)");
						System.out.println("Strength: " + player.getStr());
						System.out.println("Intelligence: " + player.getInt());
						System.out.println("Dexterity: " + player.getDex());
						System.out.println("Attack Power: "
								+ player.getAttackPower());
						System.out.println("Spell Power: "
								+ player.getSpellPower());
						System.out.println("Critical Hit Chance: "
								+ player.getCrit() + "%");
						int ilvl = 0;
						for (Item item : player.getEquip())
							ilvl += item.getItemLevel();
						System.out.println("Average Item Level: " + ilvl);
						System.out
								.println("Your current EXP to level is "
										+ player.getCURRENT_EXP()
										+ "/"
										+ player.getMAX_EXP()
										+ " ("
										+ (int) Math.round((player
												.getCURRENT_EXP() / (double) player
												.getMAX_EXP()) * 100) + "%)\n");
						Thread.sleep(5000);
						command.nextLine();
						levelUp(command, points - spent, player);
						return;
					default:
						System.out
								.println("That is an invalid input.\nType 'help' for more options.");
						command.nextLine();
						levelUp(command, points - spent, player);
						return;
					}
				break;
			case "help":
				System.out.println("Available commands: ");
				System.out.println("  Strength (str) [points to add]");
				System.out.println("  Intelligence (int) [points to add]");
				System.out.println("  Dexterity (dex) [points to add]");
				System.out.println("  Show statistics");
				System.out.println("EXAMPLE -- Strength 1");
				levelUp(command, points - spent, player);
				return;
			case "exit":
			case "quit":
				System.exit(0);
			default:
				System.out
						.println("That is an invalid selection.\nType 'help' for more options.");
				command.nextLine();
				levelUp(command, points - spent, player);
				return;
			}
		} else
			levelUp(command, points - spent, player);
		levelUp(command, points - spent, player);
	}

	/**
	 * The main game. After display of doors it takes user input
	 * to select the door they wish to pass through. Use help
	 * command to see all available commands.
	 * @param command
	 * @param player
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void doors(Scanner command, Player player)
			throws InterruptedException, IOException, ClassNotFoundException {
		System.out.print("Command: ");
		if (command.hasNext())
			switch (command.next().toLowerCase()) {
			case "choose":
			case "select":
				if (command.hasNext())
					switch (command.next().toLowerCase()) {
					case "door":
						if (command.hasNextInt())
							switch (command.nextInt()) {
							case 1:
							case 2:
							case 3:
								switch (player.r.nextInt(3) + 1) {
								// lucky player - EXP earned
								case 1:
									int EXP = Math
											.abs((player.r.nextInt((int) (player
													.getMAX_EXP() / (double) player
													.getLevel())
													- (int) (player
															.getMAX_EXP() / ((double) player
															.getLevel() + 5))) + (int) (player
													.getMAX_EXP() / ((double) player
													.getLevel() + 5))));
									System.out.println("You gained " + EXP
											+ " EXP.");
									player.setCURRENT_EXP(player
											.getCURRENT_EXP() + EXP);
									System.out
											.println("Your current EXP to level is "
													+ player.getCURRENT_EXP()
													+ "/"
													+ player.getMAX_EXP()
													+ " ("
													+ (int) Math.round((player
															.getCURRENT_EXP() / (double) player
															.getMAX_EXP()) * 100)
													+ "%)");
									break;
								// unlucky player - damage taken
								case 2:
									int damage = player.r.nextInt((player
											.getMAX_HP() / 8)
											- (player.getMAX_HP() / 12))
											+ (player.getMAX_HP() / 12);
									System.out
											.println("You sprung a trap and took "
													+ damage + " damage.");
									player.setHP(player.getHP() - damage);
									System.out
											.println("You have "
													+ player.getHP()
													+ "/"
													+ player.getMAX_HP()
													+ " ("
													+ (int) Math.round((player
															.getHP() / (double) player
															.getMAX_HP()) * 100)
													+ "%) HP.");
									break;
								// Battle time!!
								case 3:
									Enemy enemy = new Enemy(Game.getPlayer());
									System.out
											.println("You have encountered an "
													+ enemy.getName());
									combat(command, player, enemy);
									return;
								default:
									System.out
											.println("Gamebreaking error, getting out now!!!");
									System.exit(1);
								}
								break;
							default:
								System.out
										.println("That is an invalid input.\nType 'help' for more options.");
								command.nextLine();
								doors(command, player);
								return;
							}
						break;
					default:
						System.out
								.println("That is an invalid input.\nType 'help' for more options.");
						command.nextLine();
						doors(command, player);
						return;
					}
				break;
			case "get":
			case "show":
				if (command.hasNext())
					switch (command.next().toLowerCase()) {
					case "inventory":
					case "inv":
						inventory(command, player);
						return;
					case "stats":
					case "statistics":
						System.out.println();
						System.out.println("Name: " + player.getName());
						System.out.println("Level: " + player.getLevel());
						System.out
								.println("Current Health: "
										+ player.getHP()
										+ "/"
										+ player.getMAX_HP()
										+ " ("
										+ Math.round((((double) player.getHP() / (double) player
												.getMAX_HP()) * 100)) + "%)");
						System.out.println("Strength: " + player.getStr());
						System.out.println("Intelligence: " + player.getInt());
						System.out.println("Dexterity: " + player.getDex());
						System.out.println("Attack Power: "
								+ player.getAttackPower());
						System.out.println("Spell Power: "
								+ player.getSpellPower());
						System.out.println("Critical Hit Chance: "
								+ player.getCrit() + "%");
						int ilvl = 0;
						for (Item item : player.getEquip())
							ilvl += item.getItemLevel();
						System.out.println("Average Item Level: " + ilvl);
						System.out
								.println("Your current EXP to level is "
										+ player.getCURRENT_EXP()
										+ "/"
										+ player.getMAX_EXP()
										+ " ("
										+ (int) Math.round((player
												.getCURRENT_EXP() / (double) player
												.getMAX_EXP()) * 100) + "%)\n");
						Thread.sleep(5000);
						return;
					default:
					}
				break;
			case "exit":
			case "quit":
				Game.saveGame();
				System.exit(0);
			case "save":
				Game.saveGame();
				break;
			case "help":
				System.out.println("Available commands: ");
				System.out.println("  Select (choose) [door to select]");
				System.out.println("  Get (show) inventory (inv)");
				System.out.println("  Get (show) statistics (stats)");
				System.out.println("  Exit (quit)");
				System.out.println("  Save");
				System.out.println("EXAMPLE -- Select door 1");
				break;
			default:
				System.out
						.println("That is an invalid input.\nType 'help' for more options.");
				command.nextLine();
				doors(command, player);
				return;
			}
		else {
			command.nextLine();
			doors(command, player);
			return;
		}
	}

	public static void combat(Scanner command, Player player, Enemy enemy)
			throws InterruptedException, ClassNotFoundException, IOException {
		System.out.println("Your HP: "
				+ player.getHP()
				+ "/"
				+ player.getMAX_HP()
				+ " ("
				+ Math.round((((double) player.getHP() / (double) player
						.getMAX_HP()) * 100)) + "%)");
		System.out.println(enemy.getName()
				+ " HP: "
				+ enemy.getHP()
				+ "/"
				+ enemy.getMAX_HP()
				+ " ("
				+ Math.round((((double) enemy.getHP() / (double) enemy
						.getMAX_HP()) * 100)) + "%)");
		System.out.print("Command: ");
		boolean playerBlock = false, playerDodge = false;
		boolean enemyAvoidance = false;
		if (command.hasNext()) {
			if (player.r.nextInt(4) >= 2)
				enemyAvoidance = player.r.nextBoolean();
			switch (command.next().toLowerCase()) {
			case "attack":
				if (command.hasNext()) {
					switch (command.next().toLowerCase()) {
					case "melee":
						enemy = (Enemy) player.meleeAttack(enemy,
								enemyAvoidance);
						break;
					case "spell":
					case "magic":
						enemy = (Enemy) player.magicAttack(enemy,
								enemyAvoidance);
						break;
					case "help":
						System.out.println("Available commands: ");
						System.out.println("  Attack melee");
						System.out.println("  Attack spell (magic)");
						System.out.println("  Block");
						System.out.println("  Dodge");
						System.out.println("  Exit (quit)");
						System.out.println("EXAMPLE -- attack melee");
						command.nextLine();
						combat(command, player, enemy);
						return;
					default:
						System.out
								.println("That is an invalid input.\nType 'help' for more options.");
						command.nextLine();
						combat(command, player, enemy);
						return;
					}
				}
				break;
			case "block":
				playerBlock = true;
				break;
			case "dodge":
				playerDodge = true;
				break;
			case "exit":
			case "quit":
				System.exit(0);
			case "help":
				System.out.println("Available commands: ");
				System.out.println("  Attack melee");
				System.out.println("  Attack spell (magic)");
				System.out.println("  Block");
				System.out.println("  Dodge");
				System.out.println("  Exit (quit)");
				System.out.println("EXAMPLE -- Attack melee");
				command.nextLine();
				combat(command, player, enemy);
				return;
			default:
				System.out
						.println("That is an invalid input.\nType 'help' for more options.");
				command.nextLine();
				combat(command, player, enemy);
				return;
			}
		} else {
			command.nextLine();
			combat(command, player, enemy);
			return;
		}
		if (enemy.alive()) {
			if (!enemyAvoidance) {
				if (player.r.nextBoolean())
					enemy.meleeAttack(player, playerBlock);
				else
					enemy.magicAttack(player, playerDodge);
			}
			combat(command, player, enemy);
		} else {
			System.out.println("You killed " + enemy.getName());
			int EXP = Math
					.abs((player.r.nextInt((int) (player.getMAX_EXP() / (double) player
							.getLevel())
							- (int) (player.getMAX_EXP() / ((double) player
									.getLevel() + 5))) + (int) (player
							.getMAX_EXP() / ((double) player.getLevel() + 5))));
			if (enemy.getMod() > 0)
				EXP += EXP / (enemy.getMod() / player.getLevel());
			System.out.println("You gained " + EXP + " EXP.");
			player.setCURRENT_EXP(player.getCURRENT_EXP() + EXP);
			System.out
					.println("Your current EXP to level is "
							+ player.getCURRENT_EXP()
							+ "/"
							+ player.getMAX_EXP()
							+ " ("
							+ (int) Math.round((player.getCURRENT_EXP() / (double) player
									.getMAX_EXP()) * 100) + "%)");
			if (player.r.nextInt(101) > 60 - player.getLevel()) {
				Item item = new Item(player, enemy.weight());
				if (player.getInventory().isEmpty()) {
					player.addItem(item);
					return;
				}
				for (int i = 0; i < player.getInventory().size(); i++) {
					if (i == player.getInventory().size() - 1) {
						if (!item.getName().equalsIgnoreCase(
								player.getInventory().get(i).getName())) {
							player.addItem(item);
						}
					} else if (item.getName().equalsIgnoreCase(
							player.getInventory().get(i).getName())) {
						player.setItem(i, item);
					}
				}
			}
			return;
		}
	}

	public static void inventory(Scanner command, Player player)
			throws InterruptedException, ClassNotFoundException, IOException {
		if (player.getInventory().size() == 0) {
			System.out.println("You have no inventory!");
			return;
		}
		System.out.println("Your inventory: ");
		int inv = 1;
		for (Item item : player.getInventory()) {
			System.out.print(inv + ") " + item.getName());
			for (Item equipped : player.getEquip())
				if (equipped.equals(item))
					System.out.print(" - Equipped");
			System.out.println();
			inv++;
		}
		System.out.print("Command: ");
		if (command.hasNext()) {
			switch (command.next().toLowerCase()) {
			case "show":
			case "select":
				if (command.hasNextInt()) {
					int selected = command.nextInt() - 1;
					for (int i = 0; i < player.getInventory().size(); i++)
						if (i == selected) {
							System.out.println();
							System.out.println(player.getInventory()
									.get(selected).toString()
									+ "\n");
							command.nextLine();
							Thread.sleep(5000);
							command.reset();
							inventory(command, player);
							return;
						}
				} else if (command.hasNext()) {
					String next = command.next().toLowerCase();
					if (next.equalsIgnoreCase("equipped")) {
						System.out.println("Your equipped items: ");
						for (Item item : player.getEquip())
							System.out.print(item.getName() + "\n");
						Thread.sleep(5000);
						command.reset();
						inventory(command, player);
						return;
					} else {
						for (Item item : player.getInventory())
							if (item.getName().equalsIgnoreCase(next)) {
								System.out.println(item.toString());
								Thread.sleep(5000);
								command.reset();
								inventory(command, player);
								return;
							}
					}
				}
				break;
			case "equip":
				if (command.hasNextInt()) {
					int selected = command.nextInt() - 1;
					if (selected >= 0
							&& selected < player.getInventory().size()) {
						if (player.getInventory().get(selected).getType()
								.equalsIgnoreCase("potion")) {
							System.out
									.println("That is not a valid selection.");
							command.nextLine();
							inventory(command, player);
							return;
						}
						for (Item item : player.getEquip()) {
							if (item.equals(player.getInventory().get(selected))) {
								System.out
										.println("That item is already equipped!");
								Thread.sleep(1000);
								command.reset();
								command.nextLine();
								inventory(command, player);
								return;
							}
						}
						player.equipItem(player.getInventory().get(selected));
						Thread.sleep(1000);
						command.reset();
						command.nextLine();
						inventory(command, player);
						return;
					} else {
						System.out.println("That is not a valid selection.");
						command.nextLine();
						inventory(command, player);
						return;
					}
				} else {
					System.out.println("Item not equipped.");
					command.nextLine();
					inventory(command, player);
					return;
				}
			case "use":
				if (command.hasNextInt()) {
					int selected = command.nextInt();
					if (selected - 1 >= 0
							&& selected - 1 < player.getInventory().size()) {
						for (int i = 0; i < player.getInventory().size(); i++) {
							if (player.getInventory().get(i).getType()
									.equalsIgnoreCase("potion")) {
								System.out.println("You healed for "
										+ player.getInventory().get(i)
												.getHealing());
								player.setHP(player.getHP()
										+ player.getInventory().get(i)
												.getHealing());
								player.removeItem(i);
								command.nextLine();
								inventory(command, player);
								return;
							}
						}

					} else
						return;
				} else if (command.hasNext()) {
					String input = command.next().toLowerCase();
					if (input.equalsIgnoreCase("rune")
							|| input.equalsIgnoreCase("runes")) {
						int runes = 0;
						for (Item item : player.getInventory())
							if (item.getType().contains("Rune")) {
								runes++;
								if (runes == 3)
									break;
							}
						if (runes == 3) {
							for (int i = 0; i < player.getInventory().size(); i++)
								if (player.getInventory().get(i).getType()
										.contains("Rune")) {
									player.removeItem(i);
									i--;
								}
							Item item = new Item("Runetitan, Sword of the One");
							player.addItem(item);
						} else {
							System.out
									.println("You don't have the required items to do that.");
						}
					} else {
						System.out
								.println("That is an invalid input.\nType 'help' for more options.");
						command.nextLine();
						inventory(command, player);
						return;
					}
				}
			case "back":
			case "exit":
				return;
			case "help":
				System.out.println("Available commands: ");
				System.out
						.println("  Show (select) [number of item to select]");
				System.out.println("  Show (select) equipped");
				System.out.println("  Equip [number of item to equip]");
				System.out.println("  Use [number of potion to use]");
				System.out
						.println("  Use rune (runes) -- Use only when you have collected three runes");
				System.out.println("  Exit (back)");
				System.out.println("EXAMPLE -- Equip 1");
				command.nextLine();
				inventory(command, player);
				return;
			default:
				System.out
						.println("That is an invalid input.\nType 'help' for more options.");
				command.nextLine();
				inventory(command, player);
			}
		} else {
			command.nextLine();
			inventory(command, player);
			return;
		}
	}
}
