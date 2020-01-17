/*
abstract class BoundedStack<T>

    public const int POP_NIL = 0;
    public const int POP_OK = 1;
    public const int POP_ERR = 2;
    public const int PEEK_NIL = 0;
    public const int PEEK_OK = 1;
    public const int PEEK_ERR = 2;
    public const int PUSH_NIL = 0;
    public const int PUSH_OK = 1;
    public const int PUSH_ERR = 2;

    // конструктор
    // постусловие: создан новый пустой стек; максимально допустимое количество элементов равно заданному или равно 32.
    public BoundedStack<T> BoundedStack(int value)

    // команды
    // предусловие: размер стека меньше максимально допустимого количества элементов
    // постусловие: в стек добавлено новое значение
    public void push(T value)

    // предусловие: стек не пустой;
    // постусловие: из стека удалён верхний элемент
    public void pop()

    // постусловие: из стека удалятся все значения
    public void clear()

    // запросы
    // предусловие: стек не пустой
    public T peek()

    public int size()

    // запросы статусов
    public int get_pop_status()
    public int get_peek_status()
    public int get_push_status()

*/


import java.util.LinkedList;

public class BoundedStack<T> {

    // скрытые поля
    private LinkedList<T> boundedStack;
    private int bounds = 32;
    private int peek_status;
    private int pop_status;
    private int push_status;

    // интерфейс класса, реализующий АТД BoundedStack
    public final int POP_NIL = 0;
    public final int POP_OK = 1;
    public final int POP_ERR = 2;
    public final int PEEK_NIL = 0;
    public final int PEEK_OK = 1;
    public final int PEEK_ERR = 2;
    public final int PUSH_NIL = 0;
    public final int PUSH_OK = 1;
    public final int PUSH_ERR = 2;

    public BoundedStack() {
        boundedStack = new LinkedList<>();
        clear();
    }

    public BoundedStack(int bounds) {
        boundedStack = new LinkedList<>();
        clear();
        this.bounds = bounds;
    }

    public void push(T value) {
        if (bounds > size()) {
            boundedStack.addLast(value);
            push_status = PUSH_OK;
        } else {
            push_status = PUSH_ERR;
        }
    }

    public void pop() {
        if (size() > 0) {
            boundedStack.removeLast();
            pop_status = POP_OK;
        } else {
            pop_status = POP_ERR;
        }
    }

    public void clear() {
        if (size() > 0) {
            boundedStack.clear();
        }

        // начальные статусы для предусловий peek(), pop(), push()
        peek_status = PEEK_NIL;
        pop_status = POP_NIL;
        push_status = PUSH_NIL;
    }

    public T peek() {
        T result = null;
        if (size() > 0) {
            result = boundedStack.peekLast();
            peek_status = PEEK_OK;
        } else {
            peek_status = PEEK_ERR;
        }
        return result;
    }

    public int size() {
        return boundedStack.size();
    }

    // запросы статусов
    public int get_pop_status() {
        return pop_status;
    }

    public int get_peek_status() {
        return peek_status;
    }

    public int get_push_status() {
        return push_status;
    }
}



