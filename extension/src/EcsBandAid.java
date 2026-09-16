import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javax.sound.midi.MidiUnavailableException;
import music.Composition;
import music.MusicSheet;
import people.Person;
import people.conductors.Conductor;
import people.musicians.Cellist;
import people.musicians.Musician;
import people.musicians.Pianist;
import people.musicians.Violinist;
import utils.BadFileException;
import utils.SoundSystem;

/**
 * Class runs the orchestra simulation, given command-line arguments input.
 * Arguments are "musicianFileName, CompositionFileName, Years"
 */
public class EcsBandAid {

  /**
   * Creates SoundSystem instance, used to play the MIDI notes.
   * Conductor is to arrange musicians in orchestra + tell them what to play.
   */
  private final SoundSystem mySoundSystem;
  private final Conductor myConductor;
  private final List<Musician> allMusicians;
  private List<Musician> invitedMusicians;
  private final List<Composition> allCompositions;
  /**
   * The arrayList of integers below is used to store the line
   * number of when each composition in the composition file starts.
   * This is set in the setCompAmount method.
   */
  private final List<Integer> whenCompStartsInFile = new ArrayList<>();

  private SaveAndLoad saveAndLoadSystem;

  private final ReadFileSystem readFileSystem;

  /**
   * Gets filenames of musician names and compositions and years from command-line 'args'.
   */
  public static void main(final String[] args) throws MidiUnavailableException {

    try {

      String musicianNameFile = args[0];
      if (!musicianNameFile.endsWith(".txt")) {
        throw new BadFileException("Musician file not .txt.");
      }

      String compConfigFile = args[1];
      if (!(compConfigFile.endsWith(".txt") || compConfigFile.endsWith(".ba"))) {
        throw new BadFileException("Composition file isn't .txt / .ba");
      }

      int years = Integer.parseInt(args[2]);
      if (years < 1) {
        throw new ArithmeticException("Can't have less than 1 year.");
      }

      SoundSystem mySoundSystem = new SoundSystem();

      List<Musician> allMusicians = new ArrayList<>();
      List<Composition> allCompositions = new ArrayList<>();

      EcsBandAid ecs = new EcsBandAid(mySoundSystem, allMusicians, allCompositions);


      if (!ecs.fileReader(musicianNameFile)) {
        throw new BadFileException("Musician file not found.");
      }
      if (!ecs.fileReader(compConfigFile)) {
        throw new BadFileException("Composition file not found.");
      }

      /*
      Reads list of musicians from the file, into the ecs object
      and adds them to the "allMusicians" list.
      */
      System.out.println("Adding musicians...");
      ecs.addMusiciansFromFile(musicianNameFile);

      /*
       * Creates new ArrayList<Musician> object, assigns it to "invitedMusicians".
       */
      ecs.createInvitedMusicians();

      /*
       * Reads list of compositions from the file into the ecs object
       * and adds them to the "allCompositions" list.
       */
      System.out.println("Adding compConfig...");
      ecs.createCompositionFromFile(compConfigFile);

      ecs.createSaveLoadSystem(musicianNameFile, compConfigFile, years,
          ecs.getAllMusicians(), ecs.getAllCompositions());
      boolean loaded = ecs.loadFile();

      if (!loaded) {
        /*
         * Runs simulation for specified number of years.
         */
        for (int i = 0; i < years; i++) {
          ecs.saveCurrentYear(i);
          System.out.println("\nPerforming year " + (i + 1) + "\n");
          ecs.performForAYear(false);
        }
      } else {
        System.out.println("\nPerforming year " + (ecs.getCurrentYear() + 1) + "\n");
        ecs.performForAYear(true);
        for (int i = ecs.getCurrentYear() + 1; i < ecs.getTotalYears(); i++) {
          ecs.saveCurrentYear(i);
          System.out.println("\nPerforming year " + (i + 1) + "\n");
          ecs.performForAYear(false);
        }
      }

    } catch (BadFileException | ArithmeticException err) {
      System.out.println(err.getMessage());

    } catch (NumberFormatException err) {
      System.out.println("Amount of years must be an integer greater than 0.");
    }
  }

  /**Used only in the main method.
   *
   * @return
  Returns if the user loads in a file.
   */
  public Boolean loadFile() {
    return saveAndLoadSystem.loadFile();
  }

  /**Used only in the main method.
   *
   * @return
   Gets the current year from a save file.
  */
  public int getCurrentYear() {
    return saveAndLoadSystem.getCurrentYear();
  }

  /**Used only in the main method.
   *
   * @return
   *
   Gets total amount of years from a save file.
   */
  public int getTotalYears() {
    return saveAndLoadSystem.getTotalYears();
  }

  /** Used only in the main method for the save/load system.
   *
   * @return
   Returns allMusicians list.
   */
  public List<Musician> getAllMusicians() {
    return allMusicians;
  }

  /**Used only in the main method for the save/load system.
   *
   * @return
   Returns allCompositions list.
   */
  public List<Composition> getAllCompositions() {
    return allCompositions;
  }

  /**
   Used only in the main method for file checking.
   *
   * @return
   Used to see if the filename is valid.
   */
  public boolean fileReader(final String filename) {
    return readFileSystem.fileReader(filename);
  }

  /** Used only in the main method for the save/load system.
   Used to keep track of the current year for the save file.
   */
  public void saveCurrentYear(final int year) {
    saveAndLoadSystem.setCurrentYear(year);
  }

  /**
   * Used in the main method only. Used to instantiate the SaveAndLoad class.
   */
  public void createSaveLoadSystem(final String musicianNameFile, final String compConfigFile,
      final int years, List<Musician> allMusicians, List<Composition> allCompositions) {
    saveAndLoadSystem = new SaveAndLoad(musicianNameFile, compConfigFile,
        years, allMusicians, allCompositions);
  }

  /**
   * Constructor. Takes in the soundSystem, musicians list and compositions list from main method.
   */
  public EcsBandAid(SoundSystem mySoundSystem, List<Musician> allMusicians,
      List<Composition> allCompositions) {
    this.mySoundSystem = mySoundSystem;
    this.allMusicians = allMusicians;
    this.allCompositions = allCompositions;

    /*
     * Creates a conductor, named John.
     * This conductor will be used to manage all the musicians.
     */
    myConductor = new Conductor("John", mySoundSystem);
    readFileSystem = new ReadFileSystem();
  }

  /**
   * Performs simulation for a single year.
   * The 'loaded' boolean is to know if it has to load musicians/compositions, or generate them.
   */
  public void performForAYear(boolean loaded) {

    /*
     * This method is used to choose and perform 3 random compositions, and invite the musicians in.
     * chosen from the read in compositions file, and chooses musicians from the read in musicians
     * file.
     */

    Person person; //This is used to get any musician's name.

    // step 1: choose 3 compositions randomly to perform.

    Random rand = new Random();

    Composition[] chosenCompositions = new Composition[3];

    /*
     * Repeats 3 times:
     * Gets a random integer from 0 (inclusive) to however many compositions there are (exclusive).
     * Adds that composition at that random index, to the chosenCompositions array.
     */
    for (int i = 0; i < 3; i++) {

      if (!loaded) {
        int compositionIndex =  rand.nextInt(allCompositions.size());
        chosenCompositions[i] = (allCompositions.get(compositionIndex));
        System.out.println("Chosen compositions: " + chosenCompositions[i].getName());

        saveAndLoadSystem.setCurrentChosenComps(i, compositionIndex);
        //used to keep track of chosen comps, if user decides to save
      } else {

        saveAndLoadSystem.setCurrentChosenCompsIfLoaded();
        //used to keep track of chosen comps, if user decides to save
        chosenCompositions[i] = saveAndLoadSystem.getChosenCompositions().get(i);
        System.out.println("Chosen compositions: " + chosenCompositions[i].getName());
      }
    }

    //step 2: invite the musicians.

    //Calculates optimum amount of pianists, cellists and violinists to invite.

    int[] pianistsAmount = new int[3];
    int[] cellistAmount = new int[3];
    int[] violinistAmount = new int[3];

    /*
     * finds out how many pianists, cellists, and violinists are required for each composition.
     * After for loop, it uses largest amount of pianists, cellists and violinists required for the
     * year's performance, and invites the required amount of pianists, cellists and violinists.
     */

    //do this 3 times (3 compositions).
    for (int i = 0; i < 3; i++) {
      //repeat this by however many scores there are in the composition 'i'.
      for (int i2 = 0; i2 < chosenCompositions[i].getScores().length; i2++) {

        //if score at composition 'i' belongs to instrument (e.g. piano),
        //add 1 to instrumentAmount (e.g. pianoAmount) array at position i.
        int instrumentID = chosenCompositions[i].getScores()[i2].getInstrumentID();
        if (instrumentID == 41) { //if score belongs to a violinist
          violinistAmount[i]++;
        } else if (instrumentID == 43) { //if score belongs to a cellist
          cellistAmount[i]++;
        } else if (instrumentID == 1) { //if score belongs to a pianist
          pianistsAmount[i]++;
        }
      }
    }

    Arrays.sort(pianistsAmount);
    Arrays.sort(cellistAmount);
    Arrays.sort(violinistAmount);

    int largestPianistAmount;
    int largestCellistAmount;
    int largestViolinistAmount;

    //gets largest value in arrays (at position index 2) to store in appropriate variables.
    largestPianistAmount = pianistsAmount[2];
    largestCellistAmount = cellistAmount[2];
    largestViolinistAmount = violinistAmount[2];

    if (loaded) {
      for (Musician mus : saveAndLoadSystem.getInvitedMusicians()) {
        inviteMusician(mus);
      }
    }
    /*
     * Goes through each musician that's already invited,
     * subtracts that from the 'required amount of musicians' integers
     * as they're already counted for.
     */
    for (Musician musician : invitedMusicians) {
      if (Objects.requireNonNull(musician) instanceof Pianist) {
        largestPianistAmount--;
      } else if (musician instanceof Cellist) {
        largestCellistAmount--;
      } else if (musician instanceof Violinist) {
        largestViolinistAmount--;
      }
    }

    /*
     * registers all required musicians to the conductor's band and adds to invitedMusicians list.
     * it randomizes the allMusicians list so that the order of musicians is different.
     */
    final List<Musician> randomizedAllMusicians = new ArrayList<>(allMusicians);
    Collections.shuffle(randomizedAllMusicians);

    for (Musician musician : randomizedAllMusicians) {
      if (!invitedMusicians.contains(musician)) { //If the musician is not already invited...
        if ((musician instanceof Pianist) && largestPianistAmount > 0)  {

          inviteMusician(musician);
          largestPianistAmount--;

        } else if ((musician instanceof Cellist) && largestCellistAmount > 0)  {

          inviteMusician(musician);
          largestCellistAmount--;

        } else if ((musician instanceof Violinist) && largestViolinistAmount > 0)  {

          inviteMusician(musician);
          largestViolinistAmount--;

        } else if (largestPianistAmount < 1 && largestCellistAmount < 1 && largestViolinistAmount < 1) {

          /*
           * If we've already invited all those who needed to be invited, then break the loop early.
           */
          break;
        }
      }
    }

    //part 3: perform chosen compositions.

    saveAndLoadSystem.askToQuit(myConductor.getSeatingOrder());

    for (Composition comp : chosenCompositions) {

      System.out.println("\nPlaying... " + comp.getName() + "\n");
      myConductor.playComposition(comp);

      try {
        Thread.sleep(1000);
      } catch (InterruptedException ignored) {
      } //Adds pause between each composition.

    }

    //part 4: each musician has 50% chance of leaving the band.

    //using an iterator to iterate over list of invited musicians.
    Iterator<Musician> iterator = invitedMusicians.iterator();
    while (iterator.hasNext()) {
      Musician musician = iterator.next();

      //use the iterators remove method to safely remove element from list.
      int chanceOfLeaving = rand.nextInt(2);
      if (chanceOfLeaving ==  0) {
        myConductor.kickMusician(musician);
        iterator.remove();
        person  = (Person) musician;
        System.out.println(person.getName() + " left the band.");
      }
    }
  }

  //Adds the musician to the invitedMusicians list and registers them with the conductor.
  private void inviteMusician(Musician musician) {
    Person person = (Person) musician;
    invitedMusicians.add(musician);
    myConductor.registerMusician(musician);
    System.out.println("registered: " + person.getName());
  }

  //this is its own method because then it allocates memory for the ArrayList only when needed.
  private void createInvitedMusicians() {
    invitedMusicians = new ArrayList<>();
  }

  /**
   * Reads musician file and adds all the musicians in the file to the allMusicians list.
   */
  private void addMusiciansFromFile(String filename) throws BadFileException {

    readFileSystem.fileReader(filename);

    String eachLine;
    for (int i = 1; i <= readFileSystem.getNoLines(); i++) {

      try {
        eachLine = readFileSystem.getLine(false);
        String[] nameAndInstrument = eachLine.split("\\(");
        String musicianName = nameAndInstrument[0];
        String instrument = nameAndInstrument[1].replaceAll("[()]", "");
        if (instrument.equals("Piano")) {
          allMusicians.add(new Pianist(musicianName, mySoundSystem));
        } else if (instrument.equals("Cello")) {
          allMusicians.add(new Cellist(musicianName, mySoundSystem));
        } else if (instrument.equals("Violin")) {
          allMusicians.add(new Violinist(musicianName, mySoundSystem));
        } else {
          throw new BadFileException("Invalid musicians file.");
        }
      } catch (Exception err) {
        throw new BadFileException("Invalid musicians file");
      }

    }
  }

  private void createCompositionFromFile(String filename) throws BadFileException {

    String eachLine;
    int pointerStartOfInstruments;
    int pointerEndOfInstruments;
    int currentComp = 0;
    int currentLine;
    readFileSystem.fileReader(filename); //Creates BufferedReader which reads from compConfigFile.
    setCompAmount(); //adds which lines the compositions start, to the whenCompStartsInFile list
    readFileSystem.fileReader(filename); //Reads file again to read from start again.

    if (whenCompStartsInFile.size() < 2) {
      throw new BadFileException("Invalid composition file.");
    }

    //Repeat this by the amount of compositions there are in the file.
    for (int i = 0; i < whenCompStartsInFile.size() - 1; i++) {

      //first Line (name)

      eachLine = readFileSystem.getLine(false);
      String[] firstLine = eachLine.split("\\: ");
      String compName = firstLine[1];

      //second Line (tempo)

      eachLine = readFileSystem.getLine(true);
      String[] secondLine = eachLine.split("\\:");
      String compTempo = secondLine[1];

      //third Line (Length)

      eachLine = readFileSystem.getLine(true);
      String[] thirdLine = eachLine.split("\\:");
      int compLength = Integer.parseInt(thirdLine[1]);

      allCompositions.add(new MusicSheet(compName, compTempo, compLength));

      //Uses 'queue' to store top and bottom line numbers of all the scores to loop through.
      pointerStartOfInstruments = whenCompStartsInFile.get(currentComp) + 3;
      pointerEndOfInstruments = whenCompStartsInFile.get(currentComp + 1);

      //Repeat this by however many instruments there are in the composition.
      for (currentLine = pointerStartOfInstruments - 1; currentLine < pointerEndOfInstruments - 1;) {

        //each line includes instrument, soft(true)/loud(false), and all the notes.
        eachLine = readFileSystem.getLine(true);
        currentLine++;

        String[] myLine = eachLine.replaceAll("[\\s{}]", "").split(",");

        String instrument = myLine[0];
        String softOrLoud = myLine[1];
        boolean isSoft = softOrLoud.equals("soft");

        List<String> notes = new ArrayList<>(Arrays.asList(myLine).subList(2, myLine.length));

        allCompositions.get(currentComp).addScore(instrument, notes, isSoft);
      }
      currentComp++;
    }
  }

  /**
   * The method below is used to store the line number of when each
   * composition in the composition file starts.
   */
  private void setCompAmount() {
    String eachLine;
    for (int i = 1; i <= readFileSystem.getNoLines(); i++) {
      eachLine = readFileSystem.getLine(true);
      if (eachLine.startsWith("Name:")) {
        whenCompStartsInFile.add(i);
      }
    }
    whenCompStartsInFile.add(readFileSystem.getNoLines() + 1);
  }
}
