package weomucat.duke;

public class Pair<K extends Comparable<K>, V> implements Comparable<Pair<K, V>> {
  private K key;
  private V value;

  public Pair(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public K getKey() {
    return key;
  }

  public V getValue() {
    return value;
  }

  @Override
  public int compareTo(Pair<K, V> pair) {
    return this.key.compareTo(pair.key);
  }
}
