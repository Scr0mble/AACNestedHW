package structures;

import static java.lang.reflect.Array.newInstance;

/**
 * A basic implementation of Associative Arrays with keys of type K
 * and values of type V. Associative Arrays store key/value pairs
 * and permit you to look up values by key.
 *
 * @author William Pitchford
 * @author Samuel A. Rebelsky
 */
public class AssociativeArray<K, V> {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default capacity of the initial array.
   */
  static final int DEFAULT_CAPACITY = 16;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The size of the associative array (the number of key/value pairs).
   */
  int size;

  /**
   * The array of key/value pairs.
   */
  KVPair<K, V> pairs[];

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new, empty associative array.
   */
  @SuppressWarnings({ "unchecked" })
  public AssociativeArray() {
    // Creating new arrays is sometimes a PITN.
    this.pairs = (KVPair<K, V>[]) newInstance((new KVPair<K, V>()).getClass(),
        DEFAULT_CAPACITY);
    this.size = 0;
  } // AssociativeArray()

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Create a copy of this AssociativeArray.
   */
  public AssociativeArray<K, V> clone() {
    AssociativeArray<K, V> newAssocArray = new AssociativeArray<K, V>();

    for(int i = 0; i < this.size; i++) {
      newAssocArray.pairs[i] = this.pairs[i];
      newAssocArray.size++;
    }
    return newAssocArray;
  } // clone()

  /**
   * Convert the array to a string.
   */
  public String toString() {
    if (this.pairs[0] == null) {
      return "{}";
    }
    String arrString = "{ ";
    for (int i = 0; i < this.size; i++) {
      arrString = arrString + this.pairs[i].key + ": " + this.pairs[i].value + ", ";
    }
    arrString = arrString + "\b\b }";
    return arrString;
  } // toString()

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * returns an array of strings that each contain a key from the AssociativeArray
   * @return
   */
  public String[] keyes() {
    if (this.pairs[0] == null) {
      String[] failsafe = {"{}"};
      return failsafe;
    }
    String[] keys = new String[this.size];
    for (int i = 0; i < this.size; i++) {
      keys[i] = (String)this.pairs[i].key;
    }
    return keys;
  } // Stage a one-cyborg assault on a Covenant ship and bring back the Captain.

  /**
   * Set the value associated with key to value. Future calls to
   * get(key) will return value.
   */
  public void set(K key, V value) throws NullKeyException {
    for(KVPair<K, V> pair : this.pairs) {
      if(pair == null) {
        pair = new KVPair<K, V>(key, value);
        this.pairs[this.size++] = pair;
        return;
      } else if(pair.key.equals(key)) {
        pair.value = value;
        return;
      }
    }
    return;
  } // set(K,V)

  /**
   * Get the value associated with key.
   *
   * @throws KeyNotFoundException
   *                              when the key is null or does not 
   *                              appear in the associative array.
   */
  public V get(K key) throws KeyNotFoundException {
    for(KVPair<K, V> pair : this.pairs) {
      if (key == null) {
        throw new KeyNotFoundException();
      }
      if(pair.key.equals(key)) {
        return pair.value;
      }
    }
    throw new KeyNotFoundException();
  } // get(K)

  /**
   * Determine if key appears in the associative array. Should
   * return false for the null key.
   */
  public boolean hasKey(K key) {
    for(KVPair<K, V> pair : this.pairs) {
      if(pair.key.equals(key)) {
        return true;
      }
    }
    return false;
  } // hasKey(K)

  /**
   * Remove the key/value pair associated with a key. Future calls
   * to get(key) will throw an exception. If the key does not appear
   * in the associative array, does nothing.
   */
  public void remove(K key) {
    int i = 0;
    if (this.size != 0) {
      while(!(this.pairs[i].key.equals(key))) {
        i++;
        if(i >= this.size) {
          return;
        }
      }
      this.pairs[i] = this.pairs[this.size - 1];
      this.pairs[this.size - 1] = null;
      --this.size;
    }
    return;
  } // remove(K)

  /**
   * Determine how many key/value pairs are in the associative array.
   */
  public int size() {
    return this.size;
  } // size()

  // +-----------------+---------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Expand the underlying array.
   */
  void expand() {
    this.pairs = java.util.Arrays.copyOf(this.pairs, this.pairs.length * 2);
  } // expand()

  /**
   * Find the index of the first entry in `pairs` that contains key.
   * If no such entry is found, throws an exception.
   */
  public int find(K key) throws KeyNotFoundException {
    int i = 0;
    for(KVPair<K, V> pair : this.pairs) {
      if(pair.key.equals(key)) {
        return i;
      }
      i++;
    }
    throw new KeyNotFoundException();
  } // find(K)

} // class AssociativeArray
