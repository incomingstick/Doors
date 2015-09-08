//import java.util.ArrayList;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Nick Gaulke Nov 11, 2014 FinalProject
 * 
 */
public class Player extends Actor implements Serializable {

	private static final long serialVersionUID = -8667929380879185780L;

	private int Strength, Intelligence, Dexterity, AttackPower, SpellPower,
			CURRENT_EXP, MAX_EXP, HP, MAX_HP, Level;

	public Random r;

	private double crit;

	private ArrayList<Item> inventory;
	private ArrayList<Item> equip;

	private String name;

	transient private Scanner in;

	public Player(String name, Scanner in) throws InterruptedException,
			ClassNotFoundException, IOException {
		this.name = name;
		this.in = in;
		r = new Random();
		crit = 10;
		inventory = new ArrayList<Item>();
		equip = new ArrayList<Item>();
		MAX_EXP = 0;
		setCURRENT_EXP(0);
	}
	
	public void resetRandom() {
		r = new Random();
	}

	public void checkLevelUp() throws InterruptedException,
			ClassNotFoundException, IOException {
		if (getCURRENT_EXP() >= getMAX_EXP() && getLevel() <= 60)
			LevelUp();
	}

	public void LevelUp() throws InterruptedException, ClassNotFoundException,
			IOException {
		if (getLevel() > 0 && getLevel() < 60)
			System.out.println("You leveled up!");
		if (getLevel() >= 60) {
			System.out.println("You grow in power!");
		} else {
			setLevel(getLevel() + 1);
		}
		int pointsToSpend;
		if (getLevel() % 10 == 0 && getLevel() < 60)
			pointsToSpend = 10;
		else if (getLevel() != 1 || getLevel() == 60)
			pointsToSpend = 5;
		else {
			pointsToSpend = 7;
			setStr(getStr() + 1);
			setInt(getInt() + 1);
			setDex(getDex() + 1);
		}
		Command.levelUp(in, pointsToSpend, this);
		System.out.println("Level: " + getLevel());
		System.out.println("Strength: " + getStr());
		System.out.println("Intelligence: " + getInt());
		System.out.println("Dexterity: " + getDex());
		if (getLevel() == 1)
			MAX_EXP = 50;
		else
			MAX_EXP += (int)(((getMAX_EXP() * ((double)getLevel() / 5))
					+ (getMAX_EXP() * (2 / (double)getLevel()))
					+ (getMAX_EXP() / getLevel()) + getMAX_EXP())/ (double)getLevel());
		getMAX_HP();
		setHP(MAX_HP);
		setCURRENT_EXP(0);
		if(getLevel() > 1)
			Game.saveGame();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the strength
	 */
	public int getStr() {
		int mod = 0;
		for (Item item : equip)
			mod += item.getStrMain();
		return Strength + mod;
	}

	/**
	 * @param strength
	 *            the strength to set
	 */
	public void setStr(int strength) {
		Strength = strength;
	}

	/**
	 * @return the intelligence
	 */
	public int getInt() {
		int mod = 0;
		for (Item item : equip)
			mod += item.getIntMain();
		return Intelligence + mod;
	}

	/**
	 * @param intelligence
	 *            the intelligence to set
	 */
	public void setInt(int intelligence) {
		Intelligence = intelligence;
	}

	/**
	 * @return the dexterity
	 */
	public int getDex() {
		int mod = 0;
		for (Item item : equip)
			mod += item.getDexMain();
		return Dexterity + mod;
	}

	/**
	 * @param dexterity
	 *            the dexterity to set
	 */
	public void setDex(int dexterity) {
		Dexterity = dexterity;
	}

	/**
	 * @return the attackPower
	 */
	public int getAttackPower() {
		int mod = 0;
		for (Item item : equip)
			mod += item.getApOff();
		return AttackPower + mod;
	}

	/**
	 * @param attackPower
	 *            the attackPower to set
	 */
	public void setAttackPower(int attackPower) {
		AttackPower = attackPower;
	}

	/**
	 * @return the spellPower
	 */
	public int getSpellPower() {
		int mod = 0;
		for (Item item : equip)
			mod += item.getSpOff();
		return SpellPower + mod;
	}

	/**
	 * @param spellPower
	 *            the spellPower to set
	 */
	public void setSpellPower(int spellPower) {
		SpellPower = spellPower;
	}

	/**
	 * @return the crit
	 */
	public double getCrit() {
		int mod = 0;
		for (Item item : equip)
			mod += item.getCritOff();
		return crit + mod;
	}

	/**
	 * @param crit
	 *            the crit to set
	 */
	public void setCrit(double crit) {
		this.crit = crit;
	}

	/**
	 * @return the hp
	 */
	public int getHP() {
		return HP;
	}

	/**
	 * @param hp
	 *            the hp to set
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 */
	public void setHP(int hp) throws ClassNotFoundException, IOException,
			InterruptedException {
		if (hp <= 0) {
			System.out.println("You lose! Loading in last save game.");
			Game.setPlayer(Game.loadGame(this.name));
			Game.run();
		} else {
			if (hp > getMAX_HP()) {
				int overheal = hp - getMAX_HP();
				hp = getMAX_HP();
				System.out.println("You over healed for " + overheal + " HP.");
			}
			HP = hp;
		}
	}

	/**
	 * @return the max hp
	 */
	public int getMAX_HP() {
		return MAX_HP = (10 * getLevel()) + (getDex() * 3) + (getInt() * 2)
				+ getStr();
	}

	/**
	 * @return the current experience points
	 */
	public int getCURRENT_EXP() {
		return CURRENT_EXP;
	}

	/**
	 * @param CURRENT_EXP
	 *            the EXP to set
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void setCURRENT_EXP(int CURRENT_EXP) throws InterruptedException,
			ClassNotFoundException, IOException {
		this.CURRENT_EXP = CURRENT_EXP;
		checkLevelUp();
	}

	/**
	 * @return the EXP needed to level
	 */
	public int getMAX_EXP() {
		return MAX_EXP;
	}

	/**
	 * @return the level
	 */
        public int getLevel() {
		return Level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		if(level > 60) {
			level = 60;
			System.out.println("you have reached the maximum level! Congratulations hero!");
		}
		Level = level;
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	@Override
	public int minDamage() {
		return getLevel() * 2;
	}

	@Override
	public int maxDamage() {
		return getLevel() * 5;
	}

	public void setScanner(Scanner toSet) {
		in = toSet;
	}

	public ArrayList<Item> getEquip() {
		return equip;
	}

	public void equipItem(Item item) {
		for (Item inInv : inventory)
			if (inInv.equals(item)) {
				for (int i = 0; i < equip.size(); i++)
					if (inInv.getType().equals(equip.get(i).getType())
							|| (inInv.getType().equalsIgnoreCase("sword") && equip
									.get(i).getType().equalsIgnoreCase("staff"))
							|| (inInv.getType().equalsIgnoreCase("staff") && equip
									.get(i).getType().equalsIgnoreCase("sword"))) {
						System.out.println(inInv.getName() + " was equipped.");
						System.out.println(equip.get(i).getName()
								+ " is no longer equipped.");
						equip.set(i, inInv);
						return;
					}
				System.out.println(inInv.getName() + " was equipped.");
				equip.add(inInv);
				return;
			}
		System.out.println("That item is not in your inventory.");
	}

	@Override
	public Actor meleeAttack(Actor actor, boolean block) {
		Enemy enemy = (Enemy) actor;
		int damage = Math
				.abs((r.nextInt((maxDamage() + getAttackPower())
						- (minDamage() + getAttackPower())) + (minDamage() + getAttackPower()))
						* getStr()) / (getLevel() + 1);
		if (!enemy.dodge(this, block)) {
			if (r.nextInt(101) < getCrit()) {
				damage *= 2.5;
				System.out.println("Critical hit!");
			}
			enemy.setHP(enemy.getHP() - damage);
			System.out.println(enemy.getName() + " took " + damage
					+ " melee damage.");
		} else {
			System.out.println(enemy.getName() + " blocked your attack.");
		}
		return enemy;
	}

	@Override
	public Actor magicAttack(Actor actor, boolean dodge) {
		Enemy enemy = (Enemy) actor;
		int damage = Math.abs((r.nextInt((maxDamage() + getSpellPower())
				- minDamage()) + minDamage())
				* getInt()) / (getLevel() + 1);
		if (!enemy.dodge(this, dodge)) {
			if (r.nextInt(101) < getCrit()) {
				damage *= 2.5;
				System.out.println("Critical hit!");
			}
			enemy.setHP(enemy.getHP() - damage);
			System.out.println(enemy.getName() + " took " + damage
					+ " magic damage.");
		} else {
			System.out.println(enemy.getName() + " dodged your attack.");
		}
		return enemy;
	}

	@Override
	public boolean block(Actor actor, boolean ability) {
		int blockChance = 70 - (getDex() / 5);
		if (blockChance < 50)
			blockChance = 50;
		if (ability || r.nextInt(100) > blockChance) {
			return true;
		}
		return false;
	}

	@Override
	public boolean dodge(Actor actor, boolean ability) {
		int dodgeChance = 70 - (getDex() / 5);
		if (dodgeChance < 50)
			dodgeChance = 50;
		if (ability || r.nextInt(100) > dodgeChance) {
			return true;
		}
		return false;
	}

	public void addItem(Item item) {
		for (Item inInv : inventory)
			if (item.equals(inInv))
				return;
		System.out.println("You gained new loot! Check your inventory!");
		int lowest = 2147483647, index = inventory.size();
		if (inventory.size() >= 13)
			for (int i = 0; i < inventory.size(); i++) {
				if (inventory.get(i).getItemLevel() < lowest
						&& !inventory.get(i).getType()
								.equalsIgnoreCase("legendary")) {
					lowest = inventory.get(i).getItemLevel();
					index = i;
				}
			}
		else if (index == inventory.size())
			inventory.add(item);
		else {
			for (int j = 0; j < equip.size(); j++) {
				if (inventory.get(index).equals(equip.get(j))) {
					equip.remove(index);
				}
			}
			inventory.set(index, item);
		}
	}

	public void setItem(int index, Item item) {
		System.out.println("You gained new loot! Check your inventory!");
		inventory.set(index, item);
	}

	public void removeItem(int index) {
		inventory.remove(index);
	}

	public String toString() {
		return "I exist";
	}
}
