/*
abstract class NativeDictionary<String, T>

  // конструктор
// постусловие: создан пустой словарь заданного размера
  public NativeDictionary<String, T> NativeDictionary(int size);

  // команды
// предусловие: в словаре есть свободное место
// постусловие: в словарь добавлено новое значение
  public int put(String key, T value);

// предусловие: словарь не пустой
// постусловие: из словаря удалено заданное значение
  public void remove(String key);

  // запросы
// предусловие: словарь не пустой
// постусловие: возвращает значение по заданному ключу
  public T get(String key);

  public int size(); // количество элементов в словаре

  public boolean is_key(String key); // содержится ли заданный ключ в словаре

  // запросы статусов (возможные значения статусов)
  public int get_put_status(); // успешно/ словарь заполнен; не удалось разместить значение
  public int get_remove_status(); // успешно/ словарь пуст; значения нет в словаре
  public int get_get_status(); // успешно/ словарь пуст; значения нет в словаре

*/

import java.lang.reflect.Array;

public class NativeDictionary<String, T> {
    private int size;
    private int counter;
    private String[] slots;
    private T[] values;
    private Class clazz;
    private final int STEP;

    private int put_status;
    private int remove_status;
    private int get_status;

    public final int PUT_OK = 1;
    public final int PUT_ERR = 2;
    public final int REMOVE_OK = 1;
    public final int REMOVE_ERR = 2;
    public final int GET_OK = 1;
    public final int GET_ERR = 2;

    public NativeDictionary(Class clazz, int size) {
        this.clazz = clazz;
        this.size = size;
        counter = 0;
        STEP = 3;
        slots = (String[]) Array.newInstance(java.lang.String.class, size);
        values = (T[]) Array.newInstance(this.clazz, size);
    }

    private int hash_func(String key) {
        return Math.abs(key.hashCode() % size);
    }

    private int seekSlot(String key) {
        int slot = hash_func(key);
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

    public void put(String key, T value) {
        if (counter < size && !is_key(key)) {
            int slot = seekSlot(key);
            if (slot > -1) {
                slots[slot] = key;
                values[slot] = value;
                counter++;
                put_status = PUT_OK;
                return;
            }
        }
        put_status = PUT_ERR;
    }

    public void remove(String key) {
        if (counter > 0) {
            int slot = find(key);
            if (slot > -1) {
                slots[slot] = null;
                values[slot] = null;
                counter--;
                remove_status = REMOVE_OK;
                return;
            }
        }
        remove_status = REMOVE_ERR;
    }

    public boolean is_key(String key) {
        return counter > 0 && get(key) != null;
    }

    public T get(String key) {
        if (counter > 0) {
            int slot = find(key);
            if (slot > -1) {
                get_status = GET_OK;
                return values[slot];
            }
        }
        get_status = GET_ERR;
        return null;
    }

    private int find(String key) {
        int slot = hash_func(key);
        for (int i = 0; i < size; i++) {
            if (slots[slot] == null) {
                return -1;
            } else if (slots[slot].equals(key)) {
                return slot;
            }
            slot = slot + STEP;
            if (slot >= size) {
                slot = slot - size;
            }
        }
        return -1;
    }

    public int size() {
        return counter;
    }

    public int get_put_status() {
        return put_status;
    }

    public int get_remove_status() {
        return remove_status;
    }

    public int get_get_status() {
        return get_status;
    }

}
