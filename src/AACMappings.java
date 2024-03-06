import structures.KeyNotFoundException;
import structures.NullKeyException;

public class AACMappings {
  /* Fields */

  String filename;
  AACCategory cat;

  /* Constructors */

  public AACMappings(String filename) {
    this.filename = filename;
    this.cat = new AACCategory("Default");
  }

  /* Methods */
   /**
   * Adds the mapping to the current category (or the default category if that is the current category)
   * @param imageLoc
   * @param text
   * @throws NullKeyException 
   */
  public void add(String imageLoc, String text) throws NullKeyException {
    this.cat.addItem(imageLoc, text);
    return;
  } //addItem

  /**
   * Gets the current category
   * @return
   */
  public String getCurrentCategory() {
    return this.cat.name;
  } //getCategory

  /**
   * Provides an array of all the images in the current category
   * @return
   */
  public String[] getImageLocs() {
    return this.cat.getImages();
  } //getImages()

  /**
   * Given the image location selected, it determines the associated text with the image.
   * @param imageLoc
   * @return
   * @throws KeyNotFoundException 
   */
  public String getText(String imageLoc) throws KeyNotFoundException {
    return this.cat.getText(imageLoc);
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
