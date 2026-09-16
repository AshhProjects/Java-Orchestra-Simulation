package music;

import java.util.List;

/**
 * Each composition is made up of several music scores for different instruments.
 * The compositions are coordinated by the conductor.
 * This is the interface for a typical composition. A composition is actually implemented
 * by the MusicSheet class.
 */
public interface Composition {

  String getName();

  void addScore(String instrumentName, List<String> notes, boolean soft);

  MusicScore[] getScores();

  int getLength();

  int getNoteLength();

}
