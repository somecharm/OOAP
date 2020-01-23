/*
abstract class ParentList <T>

        // конструктор
        // постусловие: создан новый пустой список
        public ParentList <T> ParentList ();

        // команды
        // предусловие: список не пуст;
        // постусловие: курсор установлен на первый узел в списке
        public void head();

        // предусловие: список не пуст;
        // постусловие: курсор установлен на последний узел в списке
        public void tail();

        // предусловие: правее курсора есть элемент;
        // постусловие: курсор сдвинут на один узел вправо
        public void right();

        // предусловие: список не пуст;
        // постусловие: следом за текущим узлом добавлен
        // новый узел с заданным значением
        public void put_right(T value);

        // предусловие: список не пуст;
        // постусловие: перед текущим узлом добавлен
        // новый узел с заданным значением
        public void put_left(T value);

        // предусловие: список не пуст;
        // постусловие: текущий узел удалён,
        // курсор смещён к правому соседу, если он есть,
        // в противном случае курсор смещён к левому соседу,
        // если он есть
        public void remove();

        // постусловие: список очищен от всех элементов
        public void clear();

        // постусловие: новый узел добавлен в хвост списка
        public void add_tail(T value);

        // постусловие: в списке удалены все узлы с заданным значением
        public void remove_all(T value);

        // предусловие: список не пуст;
        // постусловие: значение текущего узла заменено на новое
        public void replace(T value);

        // постусловие: курсор установлен на следующий узел
        // с искомым значением, если такой узел найден
        public void find(T value);

        // запросы
        public T get(); // предусловие: список не пуст

        public bool is_head();

        public bool is_tail();

        public bool is_value();

        public int size();

        // запросы статусов (возможные значения статусов)
        public int get_head_status(); // успешно; список пуст

        public int get_tail_status(); // успешно; список пуст

        public int get_right_status(); // успешно; правее нет элемента

        public int get_put_right_status(); // успешно; список пуст

        public int get_put_left_status(); // успешно; список пуст

        public int get_remove_status(); // успешно; список пуст

        public int get_replace_status(); // успешно; список пуст

        public int get_find_status(); // следующий найден;

        // следующий не найден; список пуст
        public int get_get_status(); // успешно; список пуст


abstract class LinkedList <T> extends ParentList

abstract class TwoWayList <T> extends ParentList
        // предусловие: левее курсора есть элемент;
        // постусловие: курсор сдвинут на один узел влево
        public void left();

        public int get_left_status(); // успешно; левее нет элемента

 */

class ParentList<T> {
    private Node<T> head;
    private Node<T> tail;
    private Node<T> pointer;
    private int size;
    private int head_status;
    private int tail_status;
    private int right_status;
    private int put_right_status;
    private int put_left_status;
    private int remove_status;
    private int replace_status;
    private int find_status;
    private int get_status;

    public final int NIL = 0; // еще не устанавливался
    public final int HEAD_OK = 1; // курсор корректно установлен
    public final int HEAD_ERR = 2; // список пуст
    public final int TAIL_OK = 1; // курсор корректно установлен
    public final int TAIL_ERR = 2; // список пуст
    public final int RIGHT_OK = 1; // курсор корректно установлен
    public final int RIGHT_ERR = 2; // правее нет элемента
    public final int PUT_RIGHT_OK = 1; // успешно
    public final int PUT_RIGHT_ERR = 2;// список пуст
    public final int PUT_LEFT_OK = 1; // успешно
    public final int PUT_LEFT_ERR = 2;// список пуст
    public final int REMOVE_OK = 1; // успешно
    public final int REMOVE_ERR = 2;// список пуст
    public final int REPLACE_OK = 1; // успешно
    public final int REPLACE_ERR = 2;// список пуст
    public final int FIND_OK = 1; // успешно
    public final int FIND_ERR = 2;// список пуст/ следующий не найден
    public final int GET_OK = 1; // успешно
    public final int GET_ERR = 2;// список пуст


    public ParentList() {
        clear();
    }

    public void head() {
        if (size() > 0) {
            pointer = head;
            head_status = HEAD_OK;
        } else {
            head_status = HEAD_ERR;
        }
    }

    public void tail() {
        if (size() > 0) {
            pointer = tail;
            tail_status = TAIL_OK;
        } else {
            tail_status = TAIL_ERR;
        }
    }

    public void right() {
        if (is_value() && pointer.next != null) {
            pointer = pointer.next;
            right_status = RIGHT_OK;
        } else {
            right_status = RIGHT_ERR;
        }
    }

    public void put_right(T value) {
        if (is_value()) {
            Node<T> node = new Node<>(value);
            if (pointer.next != null) {
                node.next = pointer.next;
                pointer.next.prev = node;
            } else {
                tail = node;
            }
            pointer.next = node;
            node.prev = pointer;
            size++;
            put_right_status = PUT_RIGHT_OK;
        } else {
            put_right_status = PUT_RIGHT_ERR;
        }
    }

    public void put_left(T value) {
        if (is_value()) {
            Node<T> node = new Node<>(value);
            if (pointer.prev != null) {
                node.prev.next = node;
                node.prev = pointer;
            } else {
                head = node;
            }
            pointer.prev = node;
            node.next = pointer;
            size++;
            put_left_status = PUT_LEFT_OK;
        } else {
            put_left_status = PUT_LEFT_ERR;
        }
    }

    public void remove() {
        if (is_value()) {
            if (is_head() && is_tail()) {
                pointer = null;
                head = null;
                tail = null;
            } else if (is_head()) {
                head = pointer.next;
                pointer = head;
                pointer.prev = null;
            } else if (is_tail()) {
                tail = pointer.prev;
                pointer = tail;
                pointer.next = null;
            } else {
                pointer.prev.next = pointer.next;
                pointer.next.prev = pointer.prev;
                pointer = pointer.next;
            }
            size--;
            remove_status = REMOVE_OK;
        } else {
            remove_status = REMOVE_ERR;
        }
    }

    public void clear() {
        head = null;
        tail = null;
        pointer = null;
        size = 0;

        head_status = NIL;
        tail_status = NIL;
        right_status = NIL;
        put_right_status = NIL;
        put_left_status = NIL;
        remove_status = NIL;
        replace_status = NIL;
        find_status = NIL;
    }

    public void add_tail(T value) {
        Node<T> node = new Node<>(value);
        node.prev = tail;
        tail = node;
        size++;
        if (is_value()) {
            node.prev.next = node;
        } else {
            head = node;
            pointer = node;
        }
    }

    public void remove_all(T value) {
        if (is_value()) {
            head();
            while (is_value()) {
                if (pointer.value == value) {
                    remove();
                } else {
                    right();
                }
                if (is_tail()) {
                    if (pointer.value == value) {
                        remove();
                    }
                    return;
                }
            }
        }
    }

    public void replace(T value) {
        if (is_value()) {
            pointer.value = value;
            replace_status = REPLACE_OK;
        } else {
            replace_status = REPLACE_ERR;
        }
    }

    public void find(T value) {
        while (is_value()) {
            if (pointer.value == value) {
                find_status = FIND_OK;
                return;
            }
            if (is_tail()) {
                find_status = FIND_ERR;
                return;
            }
            right();
        }
        find_status = FIND_ERR;
    }

    // запросы
    public T get() {
        if (is_value()) {
            get_status = GET_OK;
            return pointer.value;
        } else {
            get_status = GET_ERR;
            return null;
        }
    }

    public boolean is_head() {
        return is_value() && pointer == head;
    }

    public boolean is_tail() {
        return is_value() && pointer == tail;
    }

    public boolean is_value() {
        return pointer != null;
    }

    public int size() {
        return size;
    }

    // запросы статусов
    public int get_head_status() {
        return head_status;
    }

    public int get_tail_status() {
        return tail_status;
    }

    public int get_right_status() {
        return right_status;
    }

    public int get_put_right_status() {
        return put_right_status;
    }

    public int get_put_left_status() {
        return put_left_status;
    }

    public int get_remove_status() {
        return remove_status;
    }

    public int get_replace_status() {
        return replace_status;
    }

    public int get_find_status() {
        return find_status;
    }

    public int get_get_status() {
        return get_status;
    }

    public Node<T> get_pointer() {
        return pointer;
    }

    public void set_pointer(Node<T> node) {
        pointer = node;
    }

    class Node<T> {
        public T value;
        public Node<T> next;
        public Node<T> prev;

        public Node(T value) {
            this.value = value;
            next = null;
            prev = null;
        }
    }
}

class LinkedList<T> extends ParentList<T> {
}

class TwoWayList<T> extends ParentList<T> {
    private int left_status;

    public final int LEFT_OK = 1; // курсор корректно установлен
    public final int LEFT_ERR = 2; // левее нет элемента

    public void left() {
        if (is_value() && get_pointer().prev != null) {
            set_pointer(get_pointer().prev);
            left_status = LEFT_OK;
        } else {
            left_status = LEFT_ERR;
        }
    }

    public int get_left_status() {
        return left_status;
    }
}

