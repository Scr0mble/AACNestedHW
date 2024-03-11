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
    this.cur = new AACCategory("Default");
    this.cats = new AssociativeArray<String, AACCategory>();
    try {
      this.cats.set("home", this.cur);
    } catch (NullKeyException e) {
      e.printStackTrace();
    }
    try {
      input = new FileInputStream(filename);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    reader = new InputStreamReader(input);
    int c;
    char curChar;
    String fileLocCombo = "";
    try {
      while ((curChar = (char) reader.read()) != (char) -1) {
        if (curChar != '\n') {
          if (curChar != '>') {
            fileLocCombo = fileLocCombo + curChar;
          }
        } else {
          String[] parsdLoc = fileLocCombo.split(" ", 2);
          fileLocCombo = "";
          this.add("AACNestedHW/" + parsdLoc[0], parsdLoc[1]);
          if(parsdLoc[0].contains(parsdLoc[1])) {
            this.cats.set(parsdLoc[1], new AACCategory(parsdLoc[1]));
            this.cur = this.cats.get(parsdLoc[1]);
          }
        }
      }
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
  public void add(String imageLoc, String text) throws NullKeyException {
    this.cur.addItem(imageLoc, text);
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
  public String getText(String imageLoc) throws KeyNotFoundException {
    return this.cur.getText(imageLoc);
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
