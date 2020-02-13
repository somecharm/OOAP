/*
abstract class HashTable<T>

  // конструктор
// постусловие: создана пустая хэш-таблица заданного размера
  public HashTable<T> HashTable(int size);

    // команды
// предусловие: в хэш-таблице есть свободное место
// постусловие: в хэш-таблицу добавлено новое уникальное значение
  public int put(T value);

// предусловие: хэш-таблица не пуста
// постусловие: из хэш-таблицы удалено заданное значение
  public void remove(T value);

    // запросы
// предусловие: хэш-таблица не пуста
// постусловие: возвращается признак, принадлежит ли значение хэш-таблице
  public bool get(T value);

  public int size();

    // запросы статусов (возможные значения статусов)
  public int get_put_status(); // успешно/ хэш-таблица заполнена; не удалось разместить значение
  public int get_remove_status(); // успешно/ хэш-таблица пуста; значения нет в хэш-таблице
  public int get_find_status(); // успешно/ хэш-таблица пуста; значения нет в хэш-таблице

abstract class PowerSet<T>: HashTable<T>

    // запросы
  public PowerSet<T> intersection(PowerSet<T> set); // возвращается пересечение множеств

  public PowerSet<T> union(PowerSet<T> set); // возвращается объединение множеств

  public PowerSet<T> difference(PowerSet<T> set); // возвращается подмножество текущего множества из элементов, которые не входят в множество-параметр

  public boolean is_subset(PowerSet<T> set); //  входят ли все элементы множества-параметра в текущее множество

*/

import java.lang.reflect.Array;

public class PowerSet<T> extends HashTable<T> {
    public PowerSet(Class clazz, int size) {
        super(clazz, size);
    }

    public PowerSet<T> intersection(PowerSet<T> set) {
        PowerSet<T> result = new PowerSet<T>(getClazz(), size());
        for (int i = 0; i < set.size(); i++) {
            if (set.getSlots()[i] != null && get(set.getSlots()[i])) {
                result.put(set.getSlots()[i]);
            }
        }
        return result;
    }

    public PowerSet<T> union(PowerSet<T> set) {
        PowerSet<T> result = new PowerSet<T>(getClazz(), size() + set.size());
        for (int i = 0; i < size(); i++) {
            if (getSlots()[i] != null) {
                result.put(getSlots()[i]);
            }
        }
        for (int i = 0; i < set.size(); i++) {
            if (set.getSlots()[i] != null) {
                result.put(set.getSlots()[i]);
            }
        }
        return result;
    }

    public PowerSet<T> difference(PowerSet<T> set) {
        PowerSet<T> result = new PowerSet<T>(getClazz(), size());
        for (int i = 0; i < set.size(); i++) {
            if (getSlots()[i] != null && !set.get(getSlots()[i])) {
                result.put(getSlots()[i]);
            }
        }
        return result;
    }

    public boolean is_subset(PowerSet<T> set) {
        for (int i = 0; i < set.size(); i++) {
            if (set.getSlots()[i] != null && !get(set.getSlots()[i])) {
                return false;
            }
        }
        return true;
    }
}

class HashTable<T> {
    private int size;
    private int counter;
    private T[] slots;
    private Class clazz;
    private final int STEP;

    private int put_status;
    private int remove_status;
    private int find_status;

    public final int PUT_OK = 1;
    public final int PUT_ERR = 2;
    public final int REMOVE_OK = 1;
    public final int REMOVE_ERR = 2;
    public final int FIND_OK = 1;
    public final int FIND_ERR = 2;

    public HashTable(Class clazz, int size) {
        this.clazz = clazz;
        this.size = size;
        counter = 0;
        STEP = 3;
        slots = (T[]) Array.newInstance(this.clazz, size);
    }

    private int hash_func(T value) {
        return Math.abs(value.hashCode() % size);
    }

    private int seekSlot(T value) {
        int slot = hash_func(value);
        for (int i = 0; i < size; i++) {
            if (slots[slot] == null) {
                return slot;
            }
            slot = slot + STEP;
            if (slot >= size) {
                slot = slot - size;
            }
        }
        return -1;
    }

    public void put(T value) {
        if (counter < size && !get(value)) {
            int slot = seekSlot(value);
            if (slot > -1) {
                slots[slot] = value;
                counter++;
                put_status = PUT_OK;
                return;
            }
        }
        put_status = PUT_ERR;
    }

    public void remove(T value) {
        if (counter > 0) {
            int slot = find(value);
            if (slot > -1) {
                slots[slot] = null;
                counter--;
                remove_status = REMOVE_OK;
                return;
            }
        }
        remove_status = REMOVE_ERR;
    }

    public boolean get(T value) {
        if (counter > 0 && find(value) > -1) {
            find_status = FIND_OK;
            return true;
        }
        find_status = FIND_ERR;
        return false;
    }

    public int size() {
        return counter;
    }

    private int find(T value) {
        int slot = hash_func(value);
        for (int i = 0; i < size; i++) {
            if (slots[slot] == null) {
                return -1;
            } else if (slots[slot].equals(value)) {
                return slot;
            }
            slot = slot + STEP;
            if (slot >= size) {
                slot = slot - size;
            }
        }
        return -1;
    }

    public T[] getSlots() {
        return slots;
    }

    public Class getClazz() {
        return clazz;
    }

    public int get_put_status() {
        return put_status;
    }

    public int get_remove_status() {
        return remove_status;
    }

    public int get_find_status() {
        return find_status;
    }
}