import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * This is the ReadFileSystem class. This is used to read in any files, such as the
 * file containing the list of musicians, or file the containing the compositions.
 */
public class ReadFileSystem {

  private BufferedReader reader; //The BufferedReader object is used to read files in.
  private int noLines = 0; //stores how many lines in a file. Set in the 'setLineAmount' method.

  /** Sees if file is a valid file.
   *
   * @param filename takes in an external file's name.
   * @return returns whether the file exists or not.
   */
  public boolean fileReader(String filename) {
    try {
      reader = new BufferedReader(new FileReader(filename));
      setLineAmount(filename);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private void setLineAmount(String filename) {
    try (Stream<String> fileStream = Files.lines(Paths.get(filename))) {
      noLines = (int) fileStream.count();
    } catch (Exception ignored) {
    }
  }

  /** reads and returns next line it reads.
   *
   * @param removeSpaces this is used to decide whether to remove spaces in the line it reads in.
   * @return If it can't read file, returns null. Otherwise, returns the next line.
   */
  public String getLine(boolean removeSpaces) {
    try {
      if (removeSpaces) {

        return reader.readLine().replaceAll("\\s", "");
      } else {
        return reader.readLine();
      }
    } catch (Exception e) {
      System.out.println("Error reading file: " + e.getMessage());
      return null;
    }
  }

  public int getNoLines() {
    return noLines;
  }
}
