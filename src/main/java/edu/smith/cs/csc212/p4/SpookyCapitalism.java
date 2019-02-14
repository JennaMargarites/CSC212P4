package edu.smith.cs.csc212.p4;

import java.util.HashMap;
import java.util.Map;

/**
 * SpookyCapitalism, a true story
 * @author jmargarites
 * credit jjfoley for skeleton code
 *
 */
public class SpookyCapitalism implements GameWorld {
	private Map<String, Place> places = new HashMap<>();
	
	/**
	 * Where should the player start?
	 */
	@Override
	public String getStart() {
		return "bourgeoisieLiberalArtsCollege";
	}
	/**
	 * This constructor builds our SpookyCapitalism game.
	 */
	public SpookyCapitalism() {
		
	Place bourgeoisieLiberalArtsCollege = insert(
			Place.create("bourgeoisieLiberalArtsCollege", "You are at a prestigious liberal arts college.\n"
					+ "Everyone around you drives a BMW and goes skiing in the Alps over winter break.\n"+
					"How did you get here?"));
	bourgeoisieLiberalArtsCollege.addExit(new Exit("library", "Straight in front of you is a large building that looks like a library."));
	bourgeoisieLiberalArtsCollege.addExit(new Exit("offCampus", "You look to your left and see a road that leads off-campus.\n" +
			"Where could it go?"));
	bourgeoisieLiberalArtsCollege.addExit(new Exit("collegeDorm", "On your right there is a path that smells like marijuana."));
	
	Place library = insert(Place.create("library", "It's surpisingly busy here!\n"+
			"Students are furiously studying. A few are crying."));
	library.addExit(new Exit("circulationDesk", "Maybe someone at the circulation desk can help you?"));
	library.addExit(new Exit("stairwell", "There is a door leading to a stairwell."));
	library.addExit(new Exit("bourgeoisieLiberalArtsCollege", "Turn back the way you came."));
	
	Place circulationDesk = insert(Place.create("circulationDesk", "You walk up to the circulation desk.\n" +
			"There's no one there to help you. It looks like the person left in a hurry."));
	circulationDesk.addExit(new Exit("stairwell", "There is a door leading to a stairwell."));
	circulationDesk.addExit(new Exit("bourgeoisieLiberalArtsCollege", "Turn back the way you came."));
	
	Place stairwell = insert(Place.create("stairwell", "You open the door and see stairs leading up.\n"+
			"Will you go up?"));
	stairwell.addExit(new Exit("library", "Turn around"));
	stairwell.addExit(new Exit("specialCollections", "Go up the stairs"));
	
	
	
	}

	/**
	 * This helper method saves us a lot of typing. We always want to map from p.id
	 * to p.
	 * 
	 * @param p - the place.
	 * @return the place you gave us, so that you can store it in a variable.
	 */
	private Place insert(Place p) {
		places.put(p.getId(), p);
		return p;
	}

	/**
	 * I like this method for checking to make sure that my graph makes sense!
	 */
	private void checkAllExitsGoSomewhere() {
		boolean missing = false;
		// For every place:
		for (Place p : places.values()) {
			// For every exit from that place:
			for (Exit x : p.getVisibleExits()) {
				// That exit goes to somewhere that exists!
				if (!places.containsKey(x.getTarget())) {
					// Don't leave immediately, but check everything all at once.
					missing = true;
					// Print every exit with a missing place:
					System.err.println("Found exit pointing at " + x.getTarget() + " which does not exist as a place.");
				}
			}
		}
	
		// Now that we've checked every exit for every place, crash if we printed any errors.
		if (missing) {
			throw new RuntimeException("You have some exits to nowhere!");
		}
}

	/**
	 * Get a Place object by name.
	 */
	public Place getPlace(String id) {
		return this.places.get(id);		
}
}
