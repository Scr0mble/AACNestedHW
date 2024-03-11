import structures.AssociativeArray;
import structures.KeyNotFoundException;
import structures.NullKeyException;

public class AACCategory {

  /* Fields */

  String name;
  private AssociativeArray<String, String> picTextPair;

  /* Constructors */

  public AACCategory(String name) {
    this.name = name;
    this.picTextPair = new AssociativeArray<String, String>();
  }

  /* Methods */

  /**
   * Adds the mapping of the imageLoc to the text to the category
   * @param imageLoc
   * @param text
   * @throws NullKeyException 
   */
  public void addItem(String imageLoc, String text) throws NullKeyException {
    this.picTextPair.set(imageLoc, text);
    return;
  } //addItem

  /**
   * Returns the name of the category
   * @return
   */
  public String getCategory() {
    return this.name;
  } //getCategory

  /**
   * Returns an array of all the images in the category
   * @return
   */
  public String[] getImages() {
    return this.picTextPair.keyes();
  } //getImages()

  /**
   * Returns the text associated with the given image loc in this category
   * @param imageLoc
   * @return
   * @throws KeyNotFoundException 
   */
  public String getText(String imageLoc) throws KeyNotFoundException {
    try {
      return this.picTextPair.get(imageLoc);
    } catch (Exception e) {
      new KeyNotFoundException();
    }
    return "You shouldn't see this";
  } //getText()

  /**
   * Determines if the provided images is stored in the category
   * @param imageLoc
   * @return
   */
  public boolean hasImage(String imageLoc) {
    return this.picTextPair.hasKey(imageLoc);
  }
}
