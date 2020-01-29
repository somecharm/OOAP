/*
abstract class Queue<T>

        // конструктор
        // постусловие: создана пустая очередь
        public Queue<T> Queue();

        // команды

        // постусловие: в хвост очереди добавлен элемент
        public void enqueue(T value);

        // предусловие: очередь не пустая
        // постусловие: из головы очереди удален элемент
        public T dequeue();

        // постусловие:
        public void clear();

        // запросы
        public int size(); // количество элементов в очереди

        // предусловие: очередь не пустая
        public T peek();

        // запросы статусов (возможные значения статусов)
        public int get_dequeue_status(); // успешно; очередь пустая
        public int get_peek_status(); // успешно; очередь пустая
*/

public class Queue<T> {

    private Node<T> head;
    private Node<T> tail;
    private int count;
    private int peek_status;
    private int dequeue_status;

    public final int PEEK_OK = 1;
    public final int PEEK_ERR = 2;
    public final int DEQUEUE_OK = 1;
    public final int DEQUEUE_ERR = 2;

    public Queue() {
        clear();
    }

    // команды

    public void enqueue(T value) {
        Node<T> item = new Node<>(value);
        if (count == 0) {
            head = item;
        } else {
            item.prev = tail;
            item.prev.next = item;
        }
        tail = item;
        count++;
    }

    public T dequeue() {
        if (count > 0) {
            T value = peek();
            dequeue_status = DEQUEUE_OK;
            if (count == 1) {
                clear();
                return value;
            }
            head = head.next;
            head.prev = null;
            count--;
            return value;
        } else {
            dequeue_status = DEQUEUE_ERR;
            return null;
        }
    }

    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    // запросы
    public int size() {
        return count;
    }

    public T peek() {
        if (count > 0) {
            peek_status = PEEK_OK;
            return head.value;
        }
        peek_status = PEEK_ERR;
        return null;
    }

    // запросы статусов

    public int get_dequeue_status() {
        return dequeue_status;
    }

    public int get_peek_status() {
        return peek_status;
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
