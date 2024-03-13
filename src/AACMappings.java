import structures.AssociativeArray;
import structures.KeyNotFoundException;
import structures.NullKeyException;
import java.io.*;
import java.util.Scanner;

public class AACMappings {
  /* Fields */

  String filename;
  AACCategory home;
  AACCategory cur;
  AssociativeArray<String, AACCategory> cats;

  /* Constructors */

  public AACMappings(String filename) {
    File txtfile = new File(filename);
    this.filename = filename;
    this.cur = new AACCategory("");
    this.home = this.cur;
    this.cats = new AssociativeArray<String, AACCategory>();
    // Tries to set the first AACCategory
    try {
      this.cats.set("", this.cur);
    } catch (NullKeyException e) {
      e.printStackTrace();
    }
    String fileLocCombo;
    // tries to map all the stuff
    try {
      Scanner lreader = new Scanner(txtfile);
      //while the file has not fully been read
      while (lreader.hasNextLine()) {
        //sets this string to the current line, jumps to the next line, and maps the filename and associated text to a category
        fileLocCombo = lreader.nextLine();
        String[] parsdLoc = fileLocCombo.split(" ", 2);
        if (!parsdLoc[0].contains(">")) {
          this.cats.set(parsdLoc[1], new AACCategory(parsdLoc[1]));
          this.cur = this.home;
          this.add(parsdLoc[0], parsdLoc[1]);
          this.cur = this.cats.get(parsdLoc[1]);
        } else {
          this.add(parsdLoc[0], parsdLoc[1]);
        } // if else
      } // while
      lreader.close();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (NullKeyException e) {
      e.printStackTrace();
    } catch (KeyNotFoundException e) {
      e.printStackTrace();
    }
    this.cur = this.home;
  }

  /* Methods */

  /**
   * Adds the mapping to the current category (or the default category if that is the current
   * category)
   * 
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
  } // addItem

  /**
   * Gets the current category
   * 
   * @return
   */
  public String getCurrentCategory() {
    return this.cur.getCategory();
  } // getCategory

  /**
   * Provides an array of all the images in the current category
   * 
   * @return
   */
  public String[] getImageLocs() {
    return this.cur.getImages();
  } // getImages()

  /**
   * Given the image location selected, it determines the associated text with the image.
   * 
   * @param imageLoc
   * @return
   * @throws KeyNotFoundException
   */
  public String getText(String imageLoc) {
    if(this.getCurrentCategory().equals("")) {
      try {
        this.cur = this.cats.get(imageLoc);
      } catch (KeyNotFoundException e) {
        e.printStackTrace();
      }
      return "";
    } else {
      try {
        return this.cur.getText(imageLoc);
      } catch (KeyNotFoundException e) {
        e.printStackTrace();
        return "";
      }
    }
  } // getText()

  /**
   * Determines if the image represents a category or text to speak
   * 
   * @param imageLoc
   * @return
   */
  public boolean isCategory(String imageLoc) {
    return true;
  } // isCategory()

  /**
   * Resets the current category of the AAC back to the default category
   */
  public void reset() {
    this.cur = this.home;
    return;
  }

  /**
   * Writes the AAC mappings stored to a file.
   * 
   * @param filename
   */
  public void writeToFile(String filename) {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
      for(int i = 0; i < this.cats.size(); i++) {
        
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return;
  }
}
