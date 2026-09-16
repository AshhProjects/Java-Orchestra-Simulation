import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import music.Composition;
import people.musicians.Musician;
import utils.BadFileException;

/**
 * This object is used for all the saving and loading. It saves the invited musicians
 * and chosen compositions as well as other information such as the total amount of years
 * and the filenames, into a save file which can then be loaded in later by the user to proceed
 * with a simulation.
 */
public class SaveAndLoad {

  /**
   * defines variables to keep the filenames of the musicians, compositions, amount of years,
   * current years, the fileName that the user will type in for the save, and a readFileSystem
   * object to read files in with.
   */
  private final String musicianFileName;
  private final String compFileName;
  private int currentYear;
  Scanner userInput;
  private String fileName;
  private ReadFileSystem readFileSystem;
  List<Musician> allMusicians;
  List<Composition> allCompositions;
  List<Musician> invMus;
  List<Composition> choseComp;
  int totalYears;
  private final int[] indexesOfChosenCompsFromAllComps;

  /** Constructor.
   *
   * @param musicianFileName the file name of the file that contains all the musicians.
   * @param compFileName the file name of the file that contains all the compositions
   * @param years the amount of years that the user decides the simulation should be run.
   */
  public SaveAndLoad(String musicianFileName, String compFileName, int years,
      List<Musician> allMusicians, List<Composition> allCompositions) {
    this.musicianFileName = musicianFileName;
    this.compFileName = compFileName;
    this.totalYears = years;
    this.allMusicians = allMusicians;
    this.allCompositions = allCompositions;
    currentYear = 0;
    indexesOfChosenCompsFromAllComps = new int[]{0, 0, 0};
    userInput = new Scanner(System.in);
  }

  /** Prompt to save.
   *
   * @param seatingPos list of all the invited musicians, in seating order.
   The params are used to find and save to the file the indexes of the invited musicians,
   relative to where the musicians are located in the allMusicians list.
   */
  public void askToQuit(List<Musician> seatingPos) {
    System.out.println("Do you wish to quit? (Y/N)");
    String userChoice = userInput.nextLine();
    if (userChoice.equalsIgnoreCase("y")) {
      saveFile(seatingPos);
      System.exit(0);
    } else if (!userChoice.equalsIgnoreCase("n")) {
      System.out.println("Invalid input, continuing the simulation.");
    }
  }

  private void saveFile( List<Musician> seatingPos) {

    System.out.println("Do you wish to save the current state of the simulation to a file? (Y/N)");
    String userChoice = userInput.nextLine();
    if (userChoice.equalsIgnoreCase("y")) {
      setFileName();
      System.out.println("Wrote to " + fileName + ".save");
      writeToFile(fileName + ".save", seatingPos);
    } else if (!userChoice.equalsIgnoreCase("n")) {
      System.out.println("Invalid input, not saving the simulation...");
    }
  }

  /** Prompt to load a file.
   *
   * @return If save file is not located, or the user doesn't want to load, then it returns false.
   Otherwise, it returns true.
   */
  public boolean loadFile() {

    readFileSystem = new ReadFileSystem();

    System.out.println("Do you wish to load a save? (Y/N)");
    String userChoice = userInput.nextLine();
    if (userChoice.equalsIgnoreCase("y")) {

      System.out.println("Type in the filename to load: ");
      try {
        String[] filename = userInput.nextLine().split("\\.");
        fileName = filename[0];
        if (!readFileSystem.fileReader(fileName + ".save")) {
          throw new BadFileException("save file not found.");
        }
        readFile(fileName);
      } catch (BadFileException err) {
        System.out.println(err.getMessage());
        System.out.println("Skipping load file...");
        fileName = null;
        return false;
      }
      return true;
    } else if (!userChoice.equalsIgnoreCase("n")) {
      System.out.println("Invalid input, ignoring load file...");
      return false;
    } else {
      return false;
    }
  }

  /** Actually reads a file in and saves to variables.
   *
   * @param filename the filename of the file that the user wants to load in.
   * @throws BadFileException In case the save file's musician or composition file
   isn't the same as the one that was loaded in, it throws an exception.
   */
  public void readFile(String filename) throws BadFileException {

    readFileSystem.fileReader(filename);
    System.out.println("Loaded " + filename + ".save");
    String eachLine;

    /*
     * check musician filename
     */
    eachLine = readFileSystem.getLine(false);
    String[] loadFile = eachLine.split("\\: ");
    String readMusicianFileName = loadFile[1];
    if (!(Objects.equals(readMusicianFileName, musicianFileName))) {
      throw new BadFileException("Wrong musician file read in.");
    }

    /*
     * check composition filename
     */
    eachLine = readFileSystem.getLine(false);
    loadFile = eachLine.split("\\: ");
    String readCompFileName = loadFile[1];
    if (!(Objects.equals(readCompFileName, compFileName))) {
      throw new BadFileException("Wrong composition file read in.");
    }

    /*
     * get total years
     */
    eachLine = readFileSystem.getLine(false);
    loadFile = eachLine.split("\\: ");
    totalYears = Integer.parseInt(loadFile[1]);

    /*
     * get chosen compositions
     */
    int[] chosenComps = new int[3];
    for (int i = 0; i < 3; i++) {
      eachLine = readFileSystem.getLine(false);
      loadFile = eachLine.split("\\: ");
      int chosenCompIndex = Integer.parseInt(loadFile[1]);
      chosenComps[i] = chosenCompIndex;
    }

    /*
     * get current year
     */
    try {
    eachLine = readFileSystem.getLine(false);
    loadFile = eachLine.split("\\: ");
    currentYear = Integer.parseInt(loadFile[1]);

    /*
     * Amount of invited
     */
    eachLine = readFileSystem.getLine(false);
    loadFile = eachLine.split("\\: ");
    int amountOfInvited = Integer.parseInt(loadFile[1]);

    List<Integer> invitedMusiciansIndex = new ArrayList<>();

    /*
      invitedMusicians
    */
      for (int i = 0; i < amountOfInvited; i++) {
        eachLine = readFileSystem.getLine(false);
        loadFile = eachLine.split("\\: ");
        int index = Integer.parseInt(loadFile[1]);
        invitedMusiciansIndex.add(index);


        invMus = genInvitedMusicians(invitedMusiciansIndex);

        choseComp = genChosenComps(chosenComps);
      }

    } catch (Exception err) {
      throw new BadFileException("invalid save file.");
    }

  }

  public List<Musician> getInvitedMusicians() {
    return invMus;
  }

  public int getCurrentYear() {
    return currentYear;
  }

  public int getTotalYears() {
    return totalYears;
  }

  public List<Composition> getChosenCompositions() {
    return choseComp;
  }

  /** Creates an array list of all the invited musicians, from the indexes from a save file.
   *
   * @param invMusIndex list of indexes of invited musicians, read from a save file.
   * @return returns the list of musicians, generated from a save file.
   */
  private List<Musician> genInvitedMusicians(List<Integer> invMusIndex) {
    List<Musician> invMus = new ArrayList<>();

    for (int index : invMusIndex) {
      for (int i = 0; i < allMusicians.size(); i++) {
        if (i == index) {
          invMus.add(allMusicians.get(i));
        }
      }
    }
    return invMus;
  }

  /**Creates an array list of all the chosen compositions, from the indexes from a save file.
   *
   * @param chosenComps list of indexes of chosen compositions, read from a save file.
   * @return returns the array of compositions, generated from a save file.
   */
  private List<Composition> genChosenComps(int[] chosenComps) {
    List<Composition> choseComp = new ArrayList<>();

    for (int index : chosenComps) {
      for (int i = 0; i < allCompositions.size(); i++) {
        if (i == index) {
          choseComp.add(allCompositions.get(i));
        }
      }
    }
    return choseComp;
  }

  /**
   * Sets the filename to save to, depending on the user input. Only takes the string before a "."
   * So if the user types in something.save, it will ignore the .save so it doesn't save the file
   * as something.save.save.
   */
  public void setFileName() {
    System.out.println("enter save file name:");
    String[] filename = userInput.nextLine().split("\\.");
    fileName = filename[0];
  }

  /**Writes the actual save file.
   *
   * @param filename name of the save file.
   * @param seatingPos list of all the invited/registered musicians at that time, in seating order.
   */
  private void writeToFile(String filename, List<Musician> seatingPos) {
    try {
      FileWriter myWriter = new FileWriter(filename);
      myWriter.write("musicianFileName: " + musicianFileName);
      myWriter.write("\ncompFileName: " + compFileName);
      myWriter.write("\ntotal years: " + totalYears);

      for (int compIndex : indexesOfChosenCompsFromAllComps) {
        myWriter.write("\nchosen comps: " + compIndex);
      }

      myWriter.write("\ncurrent year: " + currentYear);
      myWriter.write("\nAmount of invited: " + seatingPos.size());

      /*
       * Goes through the allMusicians list iteratively, sees the ones that are part of the 'seatingPos',
       * (seating values from orchestra) and writes their index to the save file.
       */
      for (Musician musician : seatingPos) {
        for (int i = 0; i < allMusicians.size(); i++) {
          if (allMusicians.get(i).equals(musician)) {
            myWriter.write("\nInvited: " + i);
            break;
          }
        }
      }

      myWriter.close();
    } catch (IOException e) {
      System.out.println("error occurred.");
    }
  }

  /**
   * When a file is loaded for the first time, the "choseComps" is populated with the chosen
   * compositions, but the "indexesOfChosenCompsFromAllComps" is NOT populated with the indexes of
   * the chosen compositions. The indexes of the chosen compositions is what gets saved.
   * This means that if the user saves right after loading without playing the first composition
   * that's loaded, it will save 0,0,0. The method below is here to fix that by being run in
   * EcsBandAid when a file is first loaded. The method goes through all the compositions, sees
   * if they match the chosen compositions, and puts the indexes of the ones that match in the
   * "indexesOfChosenCompsFromAllComps" array.
   */
  public void setCurrentChosenCompsIfLoaded() {
    for (int i = 0; i < 3; i++) {
      for (int i2 = 0; i2 < allCompositions.size(); i2++) {

        if (allCompositions.get(i2).equals(choseComp.get(i))) {
          indexesOfChosenCompsFromAllComps[i] = i2;
          break;
        }
      }
    }
  }

  public void setCurrentChosenComps(int currentIndex, int chosenCompIndex) {
    indexesOfChosenCompsFromAllComps[currentIndex] = chosenCompIndex;
  }

  public void setCurrentYear(int year) {
    this.currentYear = year;
  }
}