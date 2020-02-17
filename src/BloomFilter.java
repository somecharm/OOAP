/*
abstract class BloomFilter<T>

  // конструктор
// постусловие: создан фильтр заданного размера
  public BloomFilter<T> BloomFilter(int size);

  // команды
// постусловие: в фильтр добавлено значение
  public void add(T value);

  // запросы
// постусловие: содержится ли значение в фильтре
  public bool is_value(T value);

*/

public class BloomFilter<T> {
    private int filter_len;
    private int array;
    private final int RND1 = 11;
    private final int RND2 = 17;
    private final int RND3 = 123;

    public BloomFilter(int filter_len) {
        this.filter_len = filter_len;
        array = 0;
    }

    private int hash(T value, int rnd) {
        int hash = Math.abs(value.hashCode() * rnd % filter_len);
        return (1 << hash);
    }

    public void add(T value) {
        array |= hash(value, RND1) | hash(value, RND2) | hash(value, RND3);
    }

    public boolean is_value(T value) {
        int mask = hash(value, RND1) | hash(value, RND2) | hash(value, RND3);
        return (array & mask) == mask;
    }
}
