package orchestra;

import java.util.HashMap;
import java.util.Iterator;
import people.musicians.Musician;

/**
 * The orchestra class is where all the musicians are added. The orchestra keeps track of each
 * of the musicians' seating using a hashmap named seating. They also play each of the musicians
 * next note through the playNextNote method.
 */
public class Orchestra {

  HashMap<Integer, Musician> seating;

  /**Constructor.
   *
   */
  public Orchestra() {
    seating = new HashMap<>();
  }

  /** Returns next free seat position.

   This method finds the next free available seat by going through the hashmap incrementally from
   0 to 15 inclusively, and seeing which one doesn't have a value yet. (value being a musician).

   * @return it returns the next free seat position, or -1 if there isn't a free seat position.
   */
  public int freeSeatsAvailable() {
    for (int i = 0; i <= 15; i++) {
      if (!seating.containsKey(i)) {
        return i;
      }
    }
    return -1;
  }

  /**Sits down a musician in the next free seat position.
   *
   * @param myMusician The musician to sit down.
   * @return returns 2 if they're already sat down, 1 if there are no free seats,
   and 0 they've successfully been seated
   */
  public int sitDown(Musician myMusician) {
    if (isSeated(myMusician)) {
      return 2;
    } else if (freeSeatsAvailable() == -1) {
      return 1;
    } else {
      myMusician.setSeat(freeSeatsAvailable());
      seating.put(freeSeatsAvailable(), myMusician);
      return 0;
    }
  }

  public boolean isSeated(Musician myMusician) {
    return seating.containsValue(myMusician);
  }

  /**Removes given musician from the hashmap.
   *
   * @param myMusician the musician to remove.
   Uses an iterator because it manipulates the list it's looping through, in the loop.
   */
  public void standUp(Musician myMusician) {
    Iterator<HashMap.Entry<Integer, Musician>> iterator = seating.entrySet().iterator();

    while (iterator.hasNext()) {
      if (myMusician.equals(iterator.next().getValue())) {
        iterator.remove();
        break;
      }
    }
  }

  /**
   * Plays the next note by going through each of the musician that is sat down, and running their
   * "playNextNote" method.
   */

  public void playNextNote() {
    for (Musician myMusician : seating.values()) {
      myMusician.playNextNote();
    }
  }
}
