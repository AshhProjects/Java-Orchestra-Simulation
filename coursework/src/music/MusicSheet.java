package music;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This is an implementation of the Composition class.
 * This class also converts a string representation of notes into its MIDI equivalent.
 * It also specifies the length of a note according to a tempo marking.
 */
public class MusicSheet implements Composition {

  private final String name;
  private final String tempo;
  private final int length;
  private final ArrayList<MusicScore> allMusicScores;
  private final HashMap<String, Integer> notesToMidi;

  /** Constructor.
   *
   * @param name the name of the Music Sheet. This will be the name of a song.
   * @param tempo The tempo of the music sheet. Can either be Larghissimo, Lento, Andante, Moderato,
   Allegro, or Presto. This is converted to the note length (in ms) in the getNoteLength method.
   * @param length Length of the song. This is given in when reading the composition file.
   */
  public MusicSheet(String name, String tempo, int length) {
    this.name = name;
    this.tempo = tempo;
    this.length = length;
    notesToMidi = new HashMap<>();
    populatesStringToNoteHashMap();
    allMusicScores = new ArrayList<>();
  }

  private void populatesStringToNoteHashMap() {

    int i = 106;

    notesToMidi.put("none", 0);

    notesToMidi.put("Bb7", 106);
    notesToMidi.put("Ab7", 104);
    notesToMidi.put("Gb7", 102);
    notesToMidi.put("Eb7", 99);
    notesToMidi.put("Db7", 97);
    notesToMidi.put("Bb6", 94);
    notesToMidi.put("Ab6", 92);
    notesToMidi.put("Gb6", 90);
    notesToMidi.put("Eb6", 87);
    notesToMidi.put("Db6", 85);
    notesToMidi.put("Bb5", 82);
    notesToMidi.put("Ab5", 80);
    notesToMidi.put("Gb5", 78);
    notesToMidi.put("Eb5", 75);
    notesToMidi.put("Db5", 73);
    notesToMidi.put("Bb4", 70);
    notesToMidi.put("Ab4", 68);
    notesToMidi.put("Gb4", 66);
    notesToMidi.put("Eb4", 63);
    notesToMidi.put("Db4", 61);
    notesToMidi.put("Bb3", 58);
    notesToMidi.put("Ab3", 56);
    notesToMidi.put("Gb3", 54);
    notesToMidi.put("Eb3", 51);
    notesToMidi.put("Db3", 49);

    String[] allNotes = new String[]{"A#7", "A7", "G#7", "G7", "F#7", "F7", "E7", "D#7", "D7",
        "C#7", "C#7", "C7", "B6", "A#6", "A6", "G#6", "G6", "F#6", "F6", "E6", "D#6", "D6",
        "C#6", "C6", "B5", "A#5", "A5", "G#5", "G5", "F#5", "F5", "E5", "D#5", "D5", "C#5",
        "C5", "B4", "A#4", "A4", "G#4", "G4", "F#4", "F4", "E4", "D#4", "D4", "C#4", "C4",
        "B3", "A#3", "A3", "G#3", "G3", "F#3", "F3", "E3", "D#3", "D3", "C#3", "C3", "B2",
        "A#2", "A2", "G#2", "G2", "F#2", "F2", "E2", "D#2", "D2", "C#2", "C2"};
    for (String note : allNotes) {
      notesToMidi.put(note, i);
      i--;
    }
  }

  private int[] midiConversion(List<String> notes) {

    int [] myNotes = new int[notes.size()];
    List<String> sanitisedNotes = new ArrayList<>();

    for (String note : notes) {
      sanitisedNotes.add(note.replaceAll("\\s+", ""));
    }

    for (int i = 0; i < sanitisedNotes.size(); i++) {
      myNotes[i] = notesToMidi.get(sanitisedNotes.get(i));
    }

    return myNotes;
  }

  /**Returns the correct note length (in ms) depending on the tempo markings.

   * @return returns note length (in ms)
   */
  public int getNoteLength() {
    switch (tempo) {
      case ("Larghissimo") ->  {
        return 500;
      }
      case ("Lento") ->  {
        return 350;
      }
      case ("Andante") ->  {
        return 250;
      }
      case ("Moderato") ->  {
        return 175;
      }
      case ("Allegro") ->  {
        return 125;
      }
      case ("Presto") ->  {
        return 75;
      }
      default -> {
        return 0;
      }
    }
  }

  @Override
  public String getName() {
    return name;
  }

  /** Adds a score (in string notes format) to the composition.
   *
   * @param instrumentName the name of the instrument.
   * @param notes the list of the notes to add to a score in the composition.
   * @param soft if whether it will play it softly or loudly (true or false).
   */
  public void addScore(String instrumentName, List<String> notes, boolean soft) {
    int[] myNotes = midiConversion(notes);
    allMusicScores.add(new MusicScore(instrumentName, myNotes, soft));
  }

  @Override
  public MusicScore[] getScores() {
    return allMusicScores.toArray(new MusicScore[0]);
  }

  public int getLength() {
    return length;
  }
}
