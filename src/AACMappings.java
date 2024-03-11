import structures.AssociativeArray;
import structures.KeyNotFoundException;
import structures.NullKeyException;
import java.io.*;

public class AACMappings {
  /* Fields */

  String filename;
  AACCategory cur;
  AssociativeArray<String, AACCategory> cats;
  FileInputStream input;
  InputStreamReader reader;

  /* Constructors */

  public AACMappings(String filename) {
    this.filename = filename;
    this.cur = new AACCategory("");
    this.cats = new AssociativeArray<String, AACCategory>();
    //Tries to set the first AACCategory
    try {
      this.cats.set("home", this.cur);
    } catch (NullKeyException e) {
      e.printStackTrace();
    }
    //tries to open file
    try {
      input = new FileInputStream(filename);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    reader = new InputStreamReader(input);
    char curChar;
    String fileLocCombo = "";
    //maps all the stuff
    try {
      while ((curChar = (char) reader.read()) != (char) -1) {
        // this part constructs the string that is parsed to form the mappings
        if (curChar != '\n') {
          if (curChar != '>') {
            fileLocCombo = fileLocCombo + curChar;
          } //if
        } else {
          // this part actually does the mapping part
          String[] parsdLoc = fileLocCombo.split(" ", 2);
          fileLocCombo = "";
          this.add("AACNestedHW/" + parsdLoc[0], parsdLoc[1]);
          // this part is supposed to recognize categories by comparing their name to their location, does not work right
          if(parsdLoc[0].contains(parsdLoc[1])) {
            this.cats.set(parsdLoc[1], new AACCategory(parsdLoc[1]));
            this.cur = this.cats.get(parsdLoc[1]);
          } //if
        } // if else
      } // while
      reader.close();
      input.close();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (NullKeyException e) {
      e.printStackTrace();
    } catch (KeyNotFoundException e) {
      e.printStackTrace();
    }
  }

  /* Methods */
   /**
   * Adds the mapping to the current category (or the default category if that is the current category)
   * @param imageLoc
   * @param text
   * @throws NullKeyException 
   */
  public void add(String imageLoc, String text) {
    try {
      this.cur.addItem(imageLoc, text);
    } catch (NullKeyException e) {
      e.printStackTrace();
    }
    return;
  } //addItem

  /**
   * Gets the current category
   * @return
   */
  public String getCurrentCategory() {
    return this.cur.getCategory();
  } //getCategory

  /**
   * Provides an array of all the images in the current category
   * @return
   */
  public String[] getImageLocs() {
    return this.cur.getImages();
  } //getImages()

  /**
   * Given the image location selected, it determines the associated text with the image.
   * @param imageLoc
   * @return
   * @throws KeyNotFoundException 
   */
  public String getText(String imageLoc) {
    try {
      return this.cur.getText(imageLoc);
    } catch (KeyNotFoundException e) {
      e.printStackTrace();
      return "";
    }
  } //getText()

  /**
   * Determines if the image represents a category or text to speak
   * @param imageLoc
   * @return
   */
  public boolean isCategory(String imageLoc) {
    return true;
  } //isCategory()

  /**
   * Resets the current category of the AAC back to the default category
   */
  public void reset() {
    try {
      this.cur = this.cats.get("");
    } catch (KeyNotFoundException e) {
      e.printStackTrace();
    }
    return;
  }

  /**
   * Writes the AAC mappings stored to a file.
   * @param filename
   */
  public void writeToFile(String filename) {
    return;
  }
}
