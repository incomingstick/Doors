import java.io.Serializable;

/**
 * 
 * @author Nick Gaulke Nov 19, 2014 FinalProject
 * 
 */
public class Item implements Serializable {

	/**
	 * The serialID for serialization to save the game.
	 */
	private static final long serialVersionUID = 2060182944871205151L;

	private String rarity, prefix, name, suffix, type;

	private int strMain = 0, intMain = 0, dexMain = 0, apOff = 0, spOff = 0,
			critOff = 0, healing = 0, sellPrice = 0, itemLevel = 0;

	/**
	 * The current player
	 */
	private Player player;

	/**
	 * Creates item based on the current players attributes
	 * and the weight passed through from the dead enemy.
	 * Calls setRarity, setName, setPrefix, setStats, and
	 * setItemLevel methods.
	 * @param player
	 * @param weight
	 */
	public Item(Player player, int weight) {
		// saved in class instance for use of rng
		this.player = player;

		// sets the rarity of the item generated
		setRarity(weight);

		// set name of item based on rarity
		setName(weight);

		// sets the prefix based on rarity
		setPrefix();

		// sets the stats of the item
		setStats();

		// sets the item level of this object
		setItemLevel();
	}
	
	public Item(String name) {
		System.out.println("Congratulations on crafting "+name+"!\nNothing can stop you now bave hero.");
		this.name = name;
		rarity = "Legendary";
		type = "Sword";
	}

	/**
	 * @return the rarity
	 */
	public String getRarity() {
		return rarity;
	}

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @return the suffix
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the strMain
	 */
	public int getStrMain() {
		if(name.equalsIgnoreCase("Runetitan, Sword of the One"))
			return player.getLevel() + 10;
		return strMain;
	}

	/**
	 * @return the intMain
	 */
	public int getIntMain() {
		if(name.equalsIgnoreCase("Runetitan, Sword of the One"))
			return player.getLevel() + 10;
		return intMain;
	}

	/**
	 * @return the dexMain
	 */
	public int getDexMain() {
		if(name.equalsIgnoreCase("Runetitan, Sword of the One"))
			return player.getLevel() + 10;
		return dexMain;
	}

	/**
	 * @return the apOff
	 */
	public int getApOff() {
		if(name.equalsIgnoreCase("Runetitan, Sword of the One"))
			return player.getLevel() + 5;
		return apOff;
	}

	/**
	 * @return the spOff
	 */
	public int getSpOff() {
		if(name.equalsIgnoreCase("Runetitan, Sword of the One"))
			return player.getLevel() + 5;
		return spOff;
	}

	/**
	 * @return the critOff
	 */
	public int getCritOff() {
		if(name.equalsIgnoreCase("Runetitan, Sword of the One"))
			return player.getLevel() + 5;
		return critOff;
	}

	/**
	 * @return the healing
	 */
	public int getHealing() {
		return healing;
	}

	/**
	 * @return the sellPrice
	 */
	public int getSellPrice() {
		return sellPrice;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	private void setRarity(int weight) {
		int rare = player.r.nextInt(101);
		if (rare >= 60 + weight)
			rarity = "Common";
		else if (rare >= 30 + weight)
			rarity = "Uncommon";
		else if (rare >= 10 + weight)
			rarity = "Rare";
		else if (rare >= 1 + weight)
			rarity = "Epic";
		else
			rarity = "Legendary";
	}

	private void setPrefix() {
		switch (rarity.toLowerCase()) {
		case "common":
			prefix = null;
			break;
		case "uncommon":
			prefix = "Improved";
			break;
		case "rare":
			prefix = "Refined";
			break;
		case "epic":
			prefix = "Perfect";
			break;
		case "legendary":
			prefix = null;
			break;
		default:
			System.out.println("Gamebreaking error, getting out now!!! 1 "
					+ rarity);
			System.exit(1);
		}
	}

	private void setName(int weight) {
		switch (rarity.toLowerCase()) {
		case "common":
		case "uncommon":
		case "rare":
		case "epic":
			switch (player.r.nextInt(5) + 1) {
			case 1:
				type = "Helm";
				name = "Greathelm";
				break;
			case 2:
				type = "Chest";
				name = "Chestplate";
				break;
			case 3:
				type = "Staff";
				name = "Battlestaff";
				break;
			case 4:
				type = "Sword";
				name = "Greatsword";
				break;
			case 5:
				type = "Potion";
				name = "Potion";
				break;
			default:
			}
			break;
		case "legendary":
			type = "Quest Item";
			switch (player.r.nextInt(3)) {
			case 0:
				name = "Rune of Power";
				for (Item leg1 : player.getInventory()) {
					if (leg1.getName().equalsIgnoreCase("rune of power"))
						if (player.r.nextBoolean()) {
							name = "Rune of Courage";
							for (Item leg2 : player.getInventory())
								if (leg2.getName().equalsIgnoreCase(
										"rune of courage")) {
									name = "Rune of Wisdom";
									for (Item leg3 : player.getInventory())
										if (leg3.getName().equalsIgnoreCase(
												"rune of wisdom")) {
											setRarity(weight);
											setName(weight);
										}
								}
						} else {
							name = "Rune of Wisdom";
							for (Item leg2 : player.getInventory())
								if (leg2.getName().equalsIgnoreCase(
										"rune of wisdom")) {
									name = "Rune of Courage";
									for (Item leg3 : player.getInventory())
										if (leg3.getName().equalsIgnoreCase(
												"rune of courage")) {
											setRarity(weight);
											setName(weight);
										}
								}
						}
				}
				break;
			case 1:
				name = "Rune of Courage";
				for (Item leg1 : player.getInventory()) {
					if (leg1.getName().equalsIgnoreCase("rune of courage"))
						if (player.r.nextBoolean()) {
							name = "Rune of Wisdom";
							for (Item leg2 : player.getInventory())
								if (leg2.getName().equalsIgnoreCase(
										"rune of wisdom")) {
									name = "Rune of Power";
									for (Item leg3 : player.getInventory())
										if (leg3.getName().equalsIgnoreCase(
												"rune of power")) {
											setRarity(weight);
											setName(weight);
										}
								}
						} else {
							name = "Rune of Power";
							for (Item leg2 : player.getInventory())
								if (leg2.getName().equalsIgnoreCase(
										"rune of power")) {
									name = "Rune of Wisdom";
									for (Item leg3 : player.getInventory())
										if (leg3.getName().equalsIgnoreCase(
												"rune of wisdom")) {
											setRarity(weight);
											setName(weight);
										}
								}
						}
				}
				break;
			case 2:
				name = "Rune of Wisdom";
				for (Item leg1 : player.getInventory()) {
					if (leg1.getName().equalsIgnoreCase("rune of wisdom"))
						if (player.r.nextBoolean()) {
							name = "Rune of Courage";
							for (Item leg2 : player.getInventory())
								if (leg2.getName().equalsIgnoreCase(
										"rune of courage")) {
									name = "Rune of Power";
									for (Item leg3 : player.getInventory())
										if (leg3.getName().equalsIgnoreCase(
												"rune of Power")) {
											setRarity(weight);
											setName(weight);
										}
								}
						} else {
							name = "Rune of Power";
							for (Item leg2 : player.getInventory())
								if (leg2.getName().equalsIgnoreCase(
										"rune of power")) {
									name = "Rune of Courage";
									for (Item leg3 : player.getInventory())
										if (leg3.getName().equalsIgnoreCase(
												"rune of courage")) {
											setRarity(weight);
											setName(weight);
										}
								}
						}
				}
			}
			break;
		default:
			System.out.println("Gamebreaking error, getting out now!!! 2 "
					+ rarity);
			System.exit(1);
		}
	}

	private void setStats() {
		// decides the main stat
		// 1 = strength
		// 2 = intelligence
		// 3 = dexterity
		int ms1 = player.r.nextInt(3) + 1;
		int ms2 = player.r.nextInt(3) + 1;

		// decides the main stat
		// 1 = attack power
		// 2 = spell power
		// 3 = critical hit chance
		int os1 = player.r.nextInt(3) + 1;
		int os2 = player.r.nextInt(3) + 1;
		
		switch (rarity.toLowerCase()) {
		case "common":
			switch (type.toLowerCase()) {
			case "helm":
			case "chest":
				if (ms1 == 1) {
					strMain += player.getLevel() + 1;
					suffix = " of Strength";
				} else if (ms1 == 2) {
					intMain += player.getLevel() + 1;
					suffix = " of Intellect";
				} else if (ms1 == 3) {
					dexMain += player.getLevel() + 1;
					suffix = " of Dexterity";
				}
				break;
			case "staff":
				if (ms1 == 1) {
					intMain += player.getLevel() + 1;
					suffix = " of Intellect";
				} else if (ms1 == 2) {
					intMain += player.getLevel() + 1;
					suffix = " of Intellect";
				} else if (ms1 == 3) {
					dexMain += player.getLevel() + 1;
					suffix = " of Dexterity";
				}
				break;
			case "sword":
				if (ms1 == 1) {
					strMain += player.getLevel() + 1;
					suffix = " of Strength";
				} else if (ms1 == 2) {
					strMain += player.getLevel() + 1;
					suffix = " of Strength";
				} else if (ms1 == 3) {
					dexMain += player.getLevel() + 1;
					suffix = " of Dexterity";
				}
				break;
			case "potion":
				healing = (int) (((double) player.getLevel() / (double) player
						.getMAX_HP()) * Math.pow(10,
						String.valueOf(player.getMAX_HP()).length()));
				break;
			default:
				System.out.println("Gamebreaking error, getting out now!!! 3 "
						+ type);
				System.exit(1);
			}
			break;
		case "uncommon":
			switch (type.toLowerCase()) {
			case "helm":
			case "chest":
				if (os1 == 1) {
					apOff += player.getLevel();
					suffix = " of Vicious ";
				} else if (os1 == 2) {
					spOff += player.getLevel();
					suffix = " of Magical ";
				} else if (os1 == 3) {
					critOff += 1;
					suffix = " of Accurate ";
				}
				if (ms1 == 1) {
					strMain += player.getLevel() + 2;
					suffix += "Strength";
				} else if (ms1 == 2) {
					intMain += player.getLevel() + 2;
					suffix += "Intellect";
				} else if (ms1 == 3) {
					dexMain += player.getLevel() + 2;
					suffix += "Dexterity";
				}
				break;
			case "staff":
				if (os1 == 1) {
					spOff += player.getLevel();
					suffix = " of Magical ";
				} else if (os1 == 2) {
					spOff += player.getLevel();
					suffix = " of Magical ";
				} else if (os1 == 3) {
					critOff += 2;
					suffix = " of Accurate ";
				}
				if (ms1 == 1) {
					intMain += player.getLevel() + 2;
					suffix += "Intellect";
				} else if (ms1 == 2) {
					intMain += player.getLevel() + 2;
					suffix += "Intellect";
				} else if (ms1 == 3) {
					dexMain += player.getLevel() + 2;
					suffix += "Dexterity";
				}
				break;
			case "sword":
				if (os1 == 1) {
					apOff += player.getLevel();
					suffix = " of Vicious ";
				} else if (os1 == 2) {
					apOff += player.getLevel();
					suffix = " of Vicious ";
				} else if (os1 == 3) {
					critOff += 2;
					suffix = " of Accurate ";
				}
				if (ms1 == 1) {
					strMain += player.getLevel() + 2;
					suffix += "Strength";
				} else if (ms1 == 2) {
					strMain += player.getLevel() + 2;
					suffix += "Strength";
				} else if (ms1 == 3) {
					dexMain += player.getLevel() + 2;
					suffix += "Dexterity";
				}
				break;
			case "potion":
				healing = (int) (((double) (player.getLevel() + 1) / (double) player
						.getMAX_HP()) * Math.pow(10,
						String.valueOf(player.getMAX_HP()).length()));
				break;
			default:
				System.out.println("Gamebreaking error, getting out now!!! 4 "
						+ type);
				System.exit(1);
			}
			break;
		case "rare":
			switch (type.toLowerCase()) {
			case "helm":
			case "chest":
				if (os1 == 1) {
					apOff += player.getLevel() + 1;
					suffix = " of Vicious ";
				} else if (os1 == 2) {
					spOff += player.getLevel() + 1;
					suffix = " of Magical ";
				} else if (os1 == 3) {
					critOff += 2;
					suffix = " of Accurate ";
				}
				if (ms1 == 1) {
					strMain += player.getLevel() + 3;
					if (ms2 == 1) {
						suffix += "Power";
						strMain -= player.getLevel();
					} else {
						suffix += "Strength";
					}
				} else if (ms1 == 2) {
					intMain += player.getLevel() + 3;
					if (ms2 == 2) {
						suffix += "Wisdom";
						intMain -= player.getLevel();
					} else {
						suffix += "Intellect";
					}
				} else if (ms1 == 3) {
					dexMain += player.getLevel() + 3;
					if (ms2 == 3) {
						dexMain -= player.getLevel();
						suffix += "Courage";
					} else {
						suffix += "Dexterity";
					}
				}
				if (ms2 == 1)
					strMain += player.getLevel() + 2;
				else if (ms2 == 2)
					intMain += player.getLevel() + 2;
				else if (ms2 == 3)
					dexMain += player.getLevel() + 2;
				break;
			case "staff":
				if (os1 == 1) {
					spOff += player.getLevel() + 1;
					suffix = " of Magical ";
				} else if (os1 == 2) {
					spOff += player.getLevel() + 1;
					suffix = " of Magical ";
				} else if (os1 == 3) {
					critOff += 2;
					suffix = " of Accurate ";
				}
				if (ms1 == 1) {
					intMain += player.getLevel() + 3;
					if (ms2 == 1) {
						suffix += "Wisdom";
						intMain -= player.getLevel();
					} else {
						suffix += "Intellect";
					}
				} else if (ms1 == 2) {
					intMain += player.getLevel() + 3;
					if (ms2 == 2) {
						suffix += "Wisdom";
						intMain -= player.getLevel();
					} else {
						suffix += "Intellect";
					}
				} else if (ms1 == 3) {
					dexMain += player.getLevel() + 3;
					if (ms2 == 3) {
						dexMain -= player.getLevel();
						suffix += "Courage";
					} else {
						suffix += "Dexterity";
					}
				}
				if (ms2 == 1)
					strMain += player.getLevel() + 2;
				else if (ms2 == 2) {
					intMain += player.getLevel() + 2;
				} else if (ms2 == 3) {
					dexMain += player.getLevel() + 2;
				}
				break;
			case "sword":
				if (os1 == 1) {
					apOff += player.getLevel() + 1;
					suffix = " of Vicious ";
				} else if (os1 == 2) {
					apOff += player.getLevel() + 1;
					suffix = " of Vicious ";
				} else if (os1 == 3) {
					critOff += 2;
					suffix = " of Accurate ";
				}
				if (ms1 == 1) {
					strMain += player.getLevel() + 3;
					if (ms2 == 1) {
						suffix += "Power";
						strMain -= player.getLevel();
					} else {
						suffix += "Strength";
					}
				} else if (ms1 == 2) {
					strMain += player.getLevel() + 3;
					if (ms2 == 1) {
						suffix += "Power";
						strMain -= player.getLevel();
					} else {
						suffix += "Strength";
					};
				} else if (ms1 == 3) {
					dexMain += player.getLevel() + 3;
					if (ms2 == 3) {
						suffix += "Courage";
						dexMain -= player.getLevel();
					} else {
						suffix += "Dexterity";
					}
				}
				if (ms2 == 1) {
					strMain += player.getLevel() + 2;
				} else if (ms2 == 2)
					intMain += player.getLevel() + 2;
				else if (ms2 == 3)
					dexMain += player.getLevel() + 2;
				break;
			case "potion":
				healing = (int) (((double) (player.getLevel() + 2) / (double) player
						.getMAX_HP()) * Math.pow(10,
						String.valueOf(player.getMAX_HP()).length()));
				break;
			default:
				System.out.println("Gamebreaking error, getting out now!!! 5 "
						+ type);
				System.exit(1);
			}
			break;
		case "epic":
			switch (type.toLowerCase()) {
			case "helm":
			case "chest":
				if (os1 == 1) {
					apOff += player.getLevel() + 3;
					if (os2 == 1) {
						suffix = " of Brutal ";
						apOff -= player.getLevel();
					} else {
						suffix = " of Vicious ";
					}
				} else if (os1 == 2) {
					spOff += player.getLevel() + 3;
					if (os2 == 2) {
						suffix = " of Mystical ";
						spOff -= player.getLevel();
					} else {
						suffix = " of Magical ";
					}
				} else if (os1 == 3) {
					critOff += 5;
					if (os2 == 3) {
						suffix = " of True ";
						critOff -= player.getLevel();
					} else {
						suffix = " of Accurate ";
					}
				}
				if (os2 == 1) {
					apOff += player.getLevel() + 1;
				} else if (os2 == 2) {
					spOff += player.getLevel() + 1;
				} else if (os2 == 3) {
					critOff += 2;
				}
				if (ms1 == 1) {
					strMain += player.getLevel() + 6;
					if (ms2 == 1) {
						suffix += "Power";
						strMain -= player.getLevel();
					} else {
						suffix += "Strength";
					}
				} else if (ms1 == 2) {
					intMain += player.getLevel() + 6;
					if (ms2 == 2) {
						suffix += "Wisdom";
						intMain -= player.getLevel();
					} else {
						suffix += "Intellect";
					}
				} else if (ms1 == 3) {
					dexMain += player.getLevel() + 6;
					if (ms2 == 1) {
						suffix += "Courage";
						dexMain -= player.getLevel();
					} else {
						suffix += "Dexterity";
					}
				}
				if (ms2 == 1) {
					strMain += player.getLevel() + 4;
				} else if (ms2 == 2) {
					intMain += player.getLevel() + 4;
				} else if (ms2 == 3) {
					dexMain += player.getLevel() + 4;
				}
				break;
			case "staff":
				if (os1 == 1) {
					spOff += player.getLevel() + 3;
					if (os2 == 1) {
						suffix = " of Mystical ";
						spOff -= player.getLevel();
					} else {
						suffix = " of Magical ";
					}
				} else if (os1 == 2) {
					spOff += player.getLevel() + 3;
					if (os2 == 2) {
						suffix = " of Mystical ";
						spOff -= player.getLevel();
					} else {
						suffix = " of Magical ";
					}
				} else if (os1 == 3) {
					critOff += 5;
					if (os2 == 3) {
						suffix = " of True ";
						critOff -= player.getLevel();
					} else {
						suffix = " of Accurate ";
					}
				}
				if (os2 == 1) {
					spOff += player.getLevel() + 1;
				} else if (os2 == 2) {
					spOff += player.getLevel() + 1;
				} else if (os2 == 3) {
					critOff += 2;
				}
				if (ms1 == 1) {
					intMain += player.getLevel() + 6;
					if (ms2 == 1) {
						suffix += "Wisdom";
						strMain -= player.getLevel();
					} else {
						suffix += "Intellect";
					}
				} else if (ms1 == 2) {
					intMain += player.getLevel() + 6;
					if (ms2 == 2) {
						suffix += "Wisdom";
						strMain -= player.getLevel();
					} else {
						suffix += "Intellect";
					}
				} else if (ms1 == 3) {
					dexMain = player.getLevel() + 6;
					if (ms2 == 3) {
						suffix += "Courage";
						strMain -= player.getLevel();
					} else {
						suffix += "Dexterity";
					}
				}
				if (ms2 == 1) {
					intMain += player.getLevel() + 3;
				} else if (ms2 == 2) {
					intMain += player.getLevel() + 3;
				} else if (ms2 == 3) {
					dexMain += player.getLevel() + 3;
				}
				break;
			case "sword":
				if (os1 == 1) {
					apOff += player.getLevel() + 3;
					if (os2 == 1) {
						suffix = " of Brutal ";
						apOff -= player.getLevel();
					} else {
						suffix = " of Vicious ";
					}
				} else if (os1 == 2) {
					apOff += player.getLevel() + 3;
					if (os2 == 1) {
						suffix = " of Brutal ";
						apOff -= player.getLevel();
					} else {
						suffix = " of Vicious ";
					}
				} else if (os1 == 3) {
					critOff = 5;
					if (os2 == 3) {
						suffix = " of True ";
					} else {
						suffix = " of Accurate ";
					}
				}
				if (os2 == 1) {
					apOff += player.getLevel() + 1;
				} else if (os2 == 2) {
					apOff += player.getLevel() + 1;
				} else if (os2 == 3) {
					critOff += 2;
				}
				if (ms1 == 1) {
					strMain += player.getLevel() + 6;
					if (ms2 == 1) {
						suffix += "Power";
						strMain -= player.getLevel();
					} else {
						suffix += "Strength";
					}
				} else if (ms1 == 2) {
					strMain += player.getLevel() + 6;
					if (ms2 == 1) {
						suffix += "Power";
						strMain -= player.getLevel();
					} else {
						suffix += "Strength";
					}
				} else if (ms1 == 3) {
					dexMain += player.getLevel() + 6;
					if (ms2 == 3) {
						suffix += "Courage";
						strMain -= player.getLevel();
					} else {
						suffix += "Dexterity";
					}
				}
				if (ms2 == 1) {
					strMain += player.getLevel() + 3;
				} else if (ms2 == 2) {
					strMain += player.getLevel() + 3;
				} else if (ms2 == 3) {
					dexMain += player.getLevel() + 3;
				}
				break;
			case "potion":
				healing = (int) (((double) (player.getLevel() + 5) / (double) player
						.getMAX_HP()) * Math.pow(10,
						String.valueOf(player.getMAX_HP()).length()));
				break;
			default:
				System.out.println("Gamebreaking error, getting out now!!! 6 "
						+ type);
				System.exit(1);
			}
			break;
		case "legendary":
			break;
		default:
			System.out.println("Gamebreaking error, getting out now!!! 7 "
					+ rarity);
			System.exit(1);
		}
	}

	public void setItemLevel() {
		int ilvl = 0;
		switch (rarity.toLowerCase()) {
		case "legendary":
			ilvl += 10;
		case "epic":
			ilvl += 5;
		case "rare":
			ilvl += 3;
		case "uncommon":
			ilvl += 2;
		case "common":
			break;
		default:
			System.out.println("Gamebreaking error, getting out now!!! 7 "
					+ rarity);
			System.exit(1);
		}
		itemLevel = (player.getLevel() * 10) + ilvl;
	}

	public int getItemLevel() {
		if(name.equalsIgnoreCase("Runetitan, Sword of the One"))
			return (player.getLevel() * 15) + 10;
		return itemLevel;
	}

	public String getName() {
		String toString = "";
		if (prefix != null)
			toString += prefix + " ";
		toString += name;
		if (suffix != null)
			toString += suffix;
		return toString;
	}

	public boolean equals(Object obj) {
		Item item = (Item) obj;
		if (getName().equals(item.getName()))
			if (getStrMain() == item.getStrMain())
				if (getIntMain() == item.getIntMain())
					if (getDexMain() == item.getDexMain())
						if (getApOff() == item.getApOff())
							if (getSpOff() == item.getSpOff())
								if (getCritOff() == item.getCritOff())
									if (getHealing() == item.getHealing())
										if (getItemLevel() == item.getItemLevel())
											return true;
		return false;
	}

	public String toString() {
		String toString = "";
		toString += getName();
		toString += "\n";
		toString += rarity + " " + type + "\n";
		if (strMain > 0)
			toString += "+" + getStrMain() + " Strength\n";
		if (intMain > 0)
			toString += "+" + getIntMain() + " Intelligence\n";
		if (dexMain > 0)
			toString += "+" + getDexMain() + " Dexterity\n";
		if (apOff > 0)
			toString += "+" + getApOff() + " Attack Power\n";
		if (spOff > 0)
			toString += "+" + getSpOff() + " Spell Power\n";
		if (critOff > 0)
			toString += "+" + getCritOff() + "% Crit Chance\n";
		if(healing > 0)
			toString += "Heals you for "+healing+" HP\n";
		toString += "Item Level "+itemLevel;
		return toString;
	}
}
