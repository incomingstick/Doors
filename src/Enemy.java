import java.io.IOException;

/**
 * The enemy class extends the abstract Actor class.
 * This is what the player fights during the Command.combat()
 * method.
 * @author Nick Gaulke Nov 14, 2014 FinalProject
 * 
 */
public class Enemy extends Actor {

	/**
	 * Int attributes of enemy class:<br>
	 * HP - The current HP of the enemy.<br>
	 * maxHP - The max HP of the enemy.<br>
	 * minDamage - The minimum damage the enemy can do.<br>
	 * maxDamage - The maximum damage the enemy can do.<br>
	 * weight - The number used to determine how difficult the enemy will be.
	 */
	private int HP, maxHP, minDamage, maxDamage, weight;

	/**
	 * String attributes of the enemy class:<br>
	 * prefix - The main difficulty of the enemy.<br>
	 * name - The type of enemy.<br>
	 * suffix - The additional difficulty of the enemy.
	 */
	private String prefix, name, suffix;

	private Player player;

	/**
	 * Generate the base enemy attributes based on the Player class
	 * given. Calls setPrefix(), setName(), and setSuffix() methods.
	 * @param player
	 */
	public Enemy(Player player) {
		maxHP = player.getMAX_HP();
		minDamage = player.minDamage();
		maxDamage = player.maxDamage();
		this.player = player;
		weight = 0;
		setPrefix(player);
		setName(player);
		setSuffix(player);
		HP = maxHP;
	}

	/**
	 * Sets the prefix of the enemies name. Possibilities are:<br>
	 * "Mythic"<br>
	 * "Heroic"<br>
	 * "Angry"<br>
	 * ""
	 * It then adds to the weight attribute of the class.
	 * @param player
	 */
	public void setPrefix(Player player) {
		int level = 0;
		if (player.getLevel() > 50)
			level = 60;
		if (player.r.nextInt(101 + level) - level >= 95) {
			prefix = "Mythic";
			maxHP += getMAX_HP() / 2;
			weight += 10;
		} else if (player.r.nextInt(101 + level) - level >= 80) {
			prefix = "Heroic";
			maxHP += getMAX_HP() / 5;
			weight += 5;
		} else if (player.r.nextInt(101 + level) - level >= 50) {
			prefix = "Angry";
			maxHP += getMAX_HP() / 10;
			weight += 2;
		} else if (player.r.nextInt(101 + level) - level >= 0) {
			prefix = "";
		}
	}

	public void setName(Player player) {
		if (player.r.nextInt(101) >= 80) {
			name = "Goblin";
		} else if (player.r.nextInt(101) >= 60) {
			name = "Orc";
			maxHP += getMAX_HP() / 10;
			weight += 5;
		} else if (player.r.nextInt(101) >= 40) {
			name = "Human";
		} else if (player.r.nextInt(101) >= 20) {
			name = "Half-Elf";
		} else if (player.r.nextInt(101) >= 0) {
			name = "Demon";
			maxHP += getMAX_HP() / 5;
			weight += 10;
		}
	}

	public void setSuffix(Player player) {
		int level = 0;
		if (player.getLevel() > 50)
			level = 50;
		if (player.r.nextInt(101 - level) + level >= 99) {
			suffix = "Boss";
			weight += 10;
		} else if (player.r.nextInt(101 - level) + level >= 95) {
			suffix = "Warlord";
			weight += 5;
		} else if (player.r.nextInt(101 - level) + level >= 80) {
			suffix = "Spellblade";
			weight += 2;
		} else if (player.r.nextInt(101 - level) + level >= 50) {
			suffix = "Warrior";
			weight += 1;
		} else if (player.r.nextInt(101 - level) + level >= 0) {
			suffix = "";
		}
	}

	public int weight() {
		return weight;
	}

	public boolean alive() {
		if (HP > 0)
			return true;
		else
			return false;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		String toString = "";
		if (!prefix.equals(""))
			toString += prefix + " ";
		toString += name;
		if (!suffix.equals(""))
			toString += " " + suffix;
		return toString;
	}

	/**
	 * @return the hP
	 */
	public int getHP() {
		return HP;
	}

	/**
	 * @param hP
	 *            the hP to set
	 */
	public void setHP(int hP) {
		HP = hP;
	}

	/**
	 * @return the maxHP
	 */
	public int getMAX_HP() {
		return maxHP;
	}

	/**
	 * @param maxHP
	 *            the maxHP to set
	 */
	public void setMAX_HP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getMod() {
		int mod = 0;
		switch (prefix) {
		case "Mythic":
			mod += 3;
			break;
		case "Heroic":
			mod += 2;
			break;
		case "Angry":
			mod += 1;
			break;
		}
		switch (suffix) {
		case "Boss":
			mod += 3;
			break;
		case "Warlord":
			mod += 2;
			break;
		case "Spellblade":
			mod += 1;
			break;
		case "Worrior":
			break;
		case "Ninja":
			break;
		}
		if (name.equalsIgnoreCase("Orc"))
			mod += 1;
		else if (name.equalsIgnoreCase("Demon"))
			mod += 3;
		mod *= player.getLevel();
		if (mod > maxDamage)
			mod = maxDamage;
		return mod;
	}

	@Override
	public int minDamage() {
		return minDamage + getMod();
	}

	@Override
	public int maxDamage() {
		return maxDamage + getMod();
	}

	@Override
	public Actor meleeAttack(Actor actor, boolean block)
			throws ClassNotFoundException, IOException, InterruptedException {
		Player player = (Player) actor;
		int damage = Math
				.abs((player.r.nextInt(maxDamage() - minDamage()) + minDamage())
						* (player.r.nextInt(maxDamage() + getMod())))
				/ (player.getLevel() + (getMod() / 5));
		if (damage <= 0) {
			return meleeAttack(actor, block);
		}
		if (!player.block(this, block)) {
			System.out.println(player.getName() + " took " + damage
					+ " melee damage.");
			player.setHP(player.getHP() - damage);
		} else {
			System.out.println("You blocked the enemies attack!");
		}
		return actor;
	}

	@Override
	public Actor magicAttack(Actor actor, boolean dodge)
			throws ClassNotFoundException, IOException, InterruptedException {
		Player player = (Player) actor;
		int damage = Math
				.abs((player.r.nextInt(maxDamage() - minDamage()) + minDamage())
						* (player.r.nextInt(maxDamage() + getMod())))
				/ (player.getLevel() + (getMod() / 5));
		if (damage <= 0) {
			return magicAttack(actor, dodge);
		}
		if (!player.block(this, dodge)) {
			System.out.println(player.getName() + " took " + damage
					+ " magic damage.");
			player.setHP(player.getHP() - damage);
		} else {
			System.out.println("You dodged the enemies attack!");
		}
		return actor;
	}

	@Override
	public boolean block(Actor actor, boolean ability) {
		Player player = (Player) actor;
		int blockChance = 90 - ((player.getDex() + getMod()) / 5);
		if (blockChance < 60)
			blockChance = 60;
		if (ability || player.r.nextInt(100) > blockChance)
			return true;
		return false;
	}

	@Override
	public boolean dodge(Actor actor, boolean ability) {
		Player player = (Player) actor;
		int dodgeChance = 90 - ((player.getDex() + getMod()) / 5);
		if (dodgeChance < 60)
			dodgeChance = 60;
		if (ability || ((Player) actor).r.nextInt(100) > dodgeChance)
			return true;
		return false;
	}
}
