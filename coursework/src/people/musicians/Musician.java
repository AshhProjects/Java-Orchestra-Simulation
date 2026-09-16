package people.musicians;

/**
 * Interface for a basic musician class. What all musicians will have in common.
 */
public interface Musician {

  void setSeat(int seat);

  void readScore(int[] notes, boolean soft);

  void playNextNote();

}
