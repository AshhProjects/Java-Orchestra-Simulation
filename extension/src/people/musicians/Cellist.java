package people.musicians;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import people.Person;
import utils.SoundSystem;

/**
 * Cellist class, used to play the Cello instrument.
 */
public class Cellist extends Person implements Musician {

  private final int instrumentID;
  private final List<Integer> notes = new ArrayList<>();
  private Iterator<Integer> nextNote;
  private final SoundSystem soundSystem;
  private Integer seat;
  private Integer loudness;

  /** Constructor.
   *
   * @param name name of the person.
   * @param soundSystem the soundSystem object used to play the midi sounds.
   */
  public Cellist(String name, SoundSystem soundSystem) {
    super(name);
    instrumentID = 43;
    this.soundSystem = soundSystem;
  }

  /** Another constructor, used to allocate specific seat.
   *
   * @param name name of the person.
   * @param soundSystem the soundSystem Object used to play the midi sounds.
   * @param seat the seat number, a number between 0 and 15 inclusive.
   */
  public Cellist(String name, SoundSystem soundSystem, int seat) {
    super(name);
    instrumentID = 43;
    this.soundSystem = soundSystem;
    setSeat(seat);
  }

  /** Method used to set the seat and instrument with the instrumentID to the soundSystem.
   *
   * @param seat the seat number, a number between 0 and 15 inclusive.
   */
  public void setSeat(int seat) {
    this.seat = seat;
    this.soundSystem.setInstrument(seat, instrumentID);
  }

  /**
   * Reads the score of the music that the cellist will play.
   *
   * @param notes the array of notes (integers).
   *
   * @param soft whether the cellist will play it loudly or softly.
   */
  public void readScore(int[] notes, boolean soft) {
    this.notes.clear();
    for (int num : notes) {
      this.notes.add(num);
    }
    if (soft) {
      loudness = 50;
    } else {
      loudness = 100;
    }
    nextNote = this.notes.iterator();
  }

  /**
   * Using the iterator from readScore, it will play the next note with the soundSystem.
   */
  public void playNextNote() {
    if (nextNote.hasNext()) {
      int note = nextNote.next();
      soundSystem.playNote(seat, note, loudness);
    }
  }
}
