/*
abstract class DynArray<T>

        // конструктор
        public DynArray<T> DynArray();

        // команды
        // постусловие: в конец массива добавлен новый элемент;
        // в случае превышения размера буфера все элементы скопированы
        // в новый буфер увеличенной вдвое ёмкости
        public void append(T item);

        // предусловие: индекс находится в границах массива
        // постусловие:  в i-ю позицию массива добавлен новый элемент,
        // все последующие элементы сдвинуты вперед;
        // в случае превышения размера буфера все элементы скопированы
        // в новый буфер увеличенной вдвое ёмкости
        public void insert(T item, int i);

        // предусловие: массив не пустой; индекс находится в границах массива
        // постусловие:  из i-й позиции массива удалён элемент,
        // все последующие элементы сдвинуты назад;
        // буфер сжат, если его размер в полтора раза меньше размера массива
        public void remove(int i);

        // постусловие:  удалены все элементы массива, буфер сжат до минимального размера
        public void clear();

        // запросы
        // предусловие: массив не пустой; индекс находится в границах массива
        public T get_item(int i);

        public int size();

        // запросы статусов
        public int get_insert_status();
        public int get_remove_status();
        public int get_get_status();
*/

import java.lang.reflect.Array;

public class DynArray<T> {

    private T[] array; // буфер
    private int count; // текущее количество элементов в массиве
    private int capacity; // текущая ёмкость буфера
    private final int MIN_CAPACITY = 16; // минимально допустимая ёмкость
    private Class clazz;
    private int insert_status;
    private int remove_status;
    private int get_status;

    public final int INSERT_OK = 1; // успешно
    public final int INSERT_ERR = 2; // индекс за границами массива
    public final int REMOVE_OK = 1; // успешно
    public final int REMOVE_ERR = 2; // массив пустой; индекс за границами массива
    public final int GET_OK = 1; // успешно
    public final int GET_ERR = 2; // массив пустой; индекс за границами массива

    public DynArray(Class clazz) {
        this.clazz = clazz;
        count = 0;
        make_array(MIN_CAPACITY);
    }

    // команды
    private void make_array(int new_capacity) {
        if (array == null) {
            array = (T[]) Array.newInstance(this.clazz, new_capacity);
        } else {
            T[] newArray = (T[]) Array.newInstance(this.clazz, new_capacity);
            System.arraycopy(array, 0, newArray, 0, count);
            array = newArray;
        }
        capacity = new_capacity;
    }

    public void append(T item) {
        if (count >= capacity) {
            make_array(capacity * 2);
        }
        array[count] = item;
        count++;
    }

    public void insert(T item, int i) {
        if (i == count) {
            append(item);
            insert_status = INSERT_OK;
        } else if (in_bounds(i)) {
            append(item);
            int index = count - 1;
            while (i != index) {
                array[index] = array[index - 1];
                index--;
            }
            array[i] = item;
            insert_status = INSERT_OK;
        } else {
            insert_status = INSERT_ERR;
        }
    }

    public void remove(int i) {
        if (count > 0 && in_bounds(i)) {
            int index = i;
            while (index < count - 1) {
                array[index] = array[index + 1];
                index++;
            }
            array[count - 1] = null;
            count--;

            if (count < (int) (capacity * 0.5) && capacity > MIN_CAPACITY) {
                make_array(Math.max((int) (capacity / 1.5), MIN_CAPACITY));
            }
            remove_status = REMOVE_OK;
        } else {
            remove_status = REMOVE_ERR;
        }
    }

    public void clear() {
        array = null;
        count = 0;
        make_array(MIN_CAPACITY);
    }

    // запросы

    public T get_item(int i) {
        if (count > 0 && in_bounds(i)) {
            get_status = GET_OK;
            return array[i];
        } else {
            get_status = GET_ERR;
            return null;
        }
    }

    public int size() {
        return count;
    }

    private boolean in_bounds(int i) {
        return (i >= 0 && i < count);
    }

    public int get_insert_status() {
        return insert_status;
    }

    public int get_remove_status() {
        return remove_status;
    }

    public int get_get_status() {
        return get_status;
    }
}
