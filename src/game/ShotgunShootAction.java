package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;

/**
 * Action class that uses the direction given to shoot the Shotgun.
 * Scans the area in the direction selected in a range of 3 squares
 * in the selected direction, the direction to the left and the 
 * direction to the right and any location in between. Damages 
 * every Actor within the range and destroys any terrain that 
 * blocks thrown objects.
 * 
 * @author Daniel Yuen
 *
 */
public class ShotgunShootAction extends Action {
	
	private Exit direction;
	ArrayList<Exit> area = new ArrayList<Exit>();
	
	/**
	 * Constructor.
	 * 
	 * @param direction Exit in the chosen direction to shoot the shotgun
	 */
	public ShotgunShootAction(Exit direction) {
		this.direction = direction;
	}
	
	/**
	 * Uses the location of the Actor shooting as a starting point.
	 * Loops through the exits of the Actor and obtains the Exit which
	 * has the same direction as the direction chosen. Uses the exit
	 * with the index to the left and the right to obtain the other two
	 * directions needed. 
	 * Uses internal methods to obtain the adjacent directions outside
	 * of the 3 squares, and scans those areas accordingly.
	 * Adds all exits chosen to area ArrayList and damages any Actors
	 * and destroys any terrain within the area.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = "";
		String name = direction.getName();
		List<Exit> exits = map.locationOf(actor).getExits();
		
		// Loops over scan area 3 times to get area in range of 3
		for (int i = 0; i < 3; i++) {
			// Loops over all exits 
			for (int j = 0; j < exits.size(); j++) {
				if (exits.get(j).getName() == name) {
					// If the exit is in the direction the shotgun is facing,
					// get the left and right exits by using index
					int left = j - 1;
					if (left < 0) {
						left = exits.size() - 1;
					}
					int right = (j + 1) % (exits.size() - 1);
					Exit middleExit = exits.get(j);
					Exit leftExit = exits.get(left);
					Exit rightExit = exits.get(right);
					String leftDirection = getAdjacentDirection(middleExit, leftExit);
					String rightDirection = getAdjacentDirection(middleExit, rightExit);
					// In 2nd and 3rd loop, adds further area outside of the 3x3 area
					for (int k = 0; k < i; k++) {
						leftExit = addAdjacentArea(leftExit, leftDirection);
						rightExit = addAdjacentArea(rightExit, rightDirection);
					}
					area.add(leftExit);
					area.add(rightExit);
					area.add(middleExit);
					// Sets the middle exit as the new exit to start checking from
					exits = exits.get(j).getDestination().getExits();
				}
			}
		}
		// Does all required actions to the Ground or Actors in the area affected
		for (Exit area : area) {
			if (area.getDestination().getGround().blocksThrownObjects()) {
				area.getDestination().setGround(new Path());
			}
			if (area.getDestination().containsAnActor()) {
				Actor target = area.getDestination().getActor();
				ShotgunAttackAction attackAction = new ShotgunAttackAction(target);
				result += System.lineSeparator() + attackAction.execute(actor, map);
			}
		}
		return result;
	}
	
	/**
	 * Internal method to obtain the direction the adjacent Exit is
	 * facing from the middle Exit.
	 * 
	 * @param middle Middle Exit to be compared from
	 * @param adjacent Adjacent Exit to be compared to 
	 * @return String direction the adjacent Exit from the middle Exit
	 */
	private String getAdjacentDirection(Exit middle, Exit adjacent) {
		for (Exit exit : middle.getDestination().getExits()) {
			if (exit.getDestination() == adjacent.getDestination()) {
				return exit.getName();
			}
		}
		return "";
	}
	
	/**
	 * Internal method to add the Exits adjacent to the selected Exit
	 * in the direction chosen to the ArrayList.
	 * 
	 * @param e Exit to start from
	 * @param direction Direction to add the next Exit
	 * @return The next Exit, null if no exit found
	 */
	private Exit addAdjacentArea(Exit e, String direction) {
		for (Exit exit : e.getDestination().getExits()) {
			if (exit.getName() == direction) {
				area.add(e);
				return exit;
			}
		}
		return null;
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return actor + " shoots " + direction.getName(); 
	}
}
