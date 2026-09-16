package people.conductors;

import java.util.ArrayList;
import music.Composition;
import music.MusicScore;
import orchestra.Orchestra;
import people.Person;
import people.musicians.Cellist;
import people.musicians.Musician;
import people.musicians.Pianist;
import people.musicians.Violinist;
import utils.SoundSystem;

/**
 * A conductor's job is to arrange the musicians in the orchestra
 * and to give them their particular piece of the music to play.
 * They will instruct the musicians when to play each note.
 */
public class Conductor extends Person {

  private final Orchestra orchestra;
  private final ArrayList<Musician> band;
  private final SoundSystem soundSystem;

  /** Constructor.
   *
   * @param name the name of the conductor. In EcsBandAid simulation his name is John.
   * @param mySoundSystem the soundSystem object which will be playing all the midi sounds.
   */
  public Conductor(String name, SoundSystem mySoundSystem) {
    super(name);
    this.soundSystem = mySoundSystem;
    band = new ArrayList<>();
    orchestra = new Orchestra();
  }

  /** Adding a musician to the band and orchestra.
   *
   * @param myMusician the musician that is being registered.
   *
   The difference between a band and an orchestra is that the band is a local list in this class
   used to know everyone who is registered, while the orchestra 'sits them down' on a seat
   from 0 to 15 inclusive, and plays all their musicians' next note.
   */
  public void registerMusician(Musician myMusician) {
    band.add(myMusician);
    orchestra.sitDown(myMusician);
  }

  public void kickMusician(Musician myMusician) {
    band.remove(myMusician);
    orchestra.standUp(myMusician);
  }

  /** Plays a composition.
   *
   * @param myComposition the composition that is being played.
   *
   How this method works is that it creates 3 new empty 'band' ArrayLists, one for
   a band of pianists, band of cellists and band of violinists.
   It goes through the composition and reads all the music scores, (each music score
   being assigned to only one musician) and adds all the musicians to those bands depending
   on the instrument they play. (All violinists are added to the band of violinists).
   Then it goes through all the music scores and adds them to the right musician, depending on
   the instrument they play.
   */
  public void playComposition(Composition myComposition) {
    MusicScore[] scores;
    scores = myComposition.getScores();

    ArrayList<Pianist> bandPianist = new ArrayList<>();
    ArrayList<Cellist> bandCellist = new ArrayList<>();
    ArrayList<Violinist> bandViolinist = new ArrayList<>();

    for (Musician musician : band) {

      //So there aren't any "null" scores for any extra musicians that aren't playing anything.
      musician.readScore(new int[]{}, false);

      if (musician instanceof Pianist pianist) {
        bandPianist.add(pianist);
      } else if (musician instanceof Cellist cellist) {
        bandCellist.add(cellist);
      } else if (musician instanceof Violinist violinist) {
        bandViolinist.add(violinist);
      }
    }

    int currentPianist = 0;
    int currentCellist = 0;
    int currentViolinist = 0;
    int currentScore = 0;

    for (MusicScore score : scores) {

      //Goes through each score in the composition, and assigns it to the musicians.
      currentScore++;
      try {
        if ((score.getInstrumentID() == 1)) {
          bandPianist.get(currentPianist).readScore(score.getNotes(), score.isSoft());
          currentPianist++;
        }

        if ((score.getInstrumentID() == 43)) {
          bandCellist.get(currentCellist).readScore(score.getNotes(), score.isSoft());
          currentCellist++;
        }

        if ((score.getInstrumentID() == 41)) {
          bandViolinist.get(currentViolinist).readScore(score.getNotes(), score.isSoft());
          currentViolinist++;
        }
      } catch (IndexOutOfBoundsException e) {
        System.out.println("Not enough musicians for all the scores.");
        System.out.println("Skipping score...");
        System.out.println();
      }
    }

    for (int i = 0; i < myComposition.getLength(); i++) {
      orchestra.playNextNote();
      try {
        Thread.sleep(myComposition.getNoteLength());
      } catch (InterruptedException ignored) {
      }
    }
    soundSystem.init();
  }
}
