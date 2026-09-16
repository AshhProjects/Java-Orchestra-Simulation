package music;

/**
 * the MusicScore class keeps the information about a piece of music for an individual
 * instrument. Information stored in music score include info about the instrument, notes
 * that represent the music score, and whether the music should be played softly.
 */

public class MusicScore {

  private final String instrumentName;
  private final int[] notes;
  private final boolean soft;

  /** Constructor.
   *
   * @param instrumentName the name of the instrument, can either be Violin, Cello, or Piano.
   * @param notes the list of notes to keep in the music score which will be played by a Musician.
   * @param soft Whether to play the score softly or loudly.
   */
  public MusicScore(String instrumentName, int[] notes, boolean soft) {
    this.instrumentName = instrumentName;
    this.notes = notes;
    this.soft = soft;
  }

  /** Converts the instrument name to an instrument id.
   *
   * @return returns the instrument id. 0 if it's not Violin, Cello or Piano.
   */
  public int getInstrumentID() {
    return switch (instrumentName) {
      case "Violin" ->  41;
      case "Cello" ->  43;
      case "Piano" ->  1;
      default -> 0;
    };
  }

  public int[] getNotes() {
    return notes;
  }

  public boolean isSoft() {
    return soft;
  }
}
