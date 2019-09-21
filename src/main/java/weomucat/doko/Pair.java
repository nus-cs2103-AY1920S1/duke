package weomucat.doko;

public class Pair<K extends Comparable<K>, V> implements Comparable<Pair<K, V>> {
  private K key;
  private V value;

  public Pair(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public K key() {
    return key;
  }

  public V value() {
    return value;
  }

  @Override
  public int compareTo(Pair<K, V> pair) {
    return this.key.compareTo(pair.key);
  }
}
