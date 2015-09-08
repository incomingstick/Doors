import java.io.IOException;

/**
 * 
 * @author Nick Gaulke Nov 11, 2014 FinalProject
 * 
 */
public abstract class Actor {

	/**
	 * Default constructor for Actor class.
	 */
	public Actor() {
	}

	/**
	 * The minimum amount of damage this actor can deal.
	 * 
	 * @return the minimum damage
	 */
	abstract public int minDamage();

	/**
	 * The maximum amount of damage this actor can deal.
	 * 
	 * @return the maximum damage
	 */
	abstract public int maxDamage();

	/**
	 * Performs a melee attack against the actor. Attack Power will augment this
	 * damage.
	 * 
	 * @param actor
	 *            the actor to damage
	 * @return the damaged actor
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws InterruptedException 
	 */
	abstract public Actor meleeAttack(Actor actor, boolean block) throws ClassNotFoundException, IOException, InterruptedException;

	/**
	 * Performs a magic attack against the actor Magic Power will augment this
	 * damage.
	 * 
	 * @param actor
	 *            the actor to damage
	 * @return the damaged actor
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws InterruptedException 
	 */
	abstract public Actor magicAttack(Actor actor, boolean dodge) throws ClassNotFoundException, IOException, InterruptedException;

	/**
	 * Checks to see if the actor blocked the attack. Higher chance to block a
	 * melee attack.
	 * 
	 * @param actor
	 *            the actor to check against
	 * @param ability
	 * 			  true if used as ability
	 * 			  false if used as a check
	 * @return
	 */
	abstract public boolean block(Actor actor, boolean ability);

	/**
	 * checks to see if the actor dodge the attack. Higher chance to dodge a
	 * magic attack
	 * 
	 * @param actor
	 *            the actor to check against
	 * @param ability
	 * 			  true if used as ability
	 * 			  false if used as a check
	 * @return
	 */
	abstract public boolean dodge(Actor actor, boolean ability);
}
