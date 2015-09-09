/**
 * This is only its own class for the purpose
 * of keeping the Command class from being cluttered.
 * @author Nick Gaulke
 * Nov 12, 2014
 * FinalProject
 *
 */
public class Doors {
	
	static String doorsImage = "------------  ------------  ------------\n" +
						       "|          |  |          |  |          |\n" +
						       "|   Door   |  |   Door   |  |   Door   |\n" +
						       "|    1     |  |    2     |  |    3     |\n" +
						       "|          |  |          |  |          |\n" +
						       "------------  ------------  ------------";
	
	/**
	 * Prints the doors.
	 */
	public static void show() {
		System.out.println(doorsImage);
	}
}
