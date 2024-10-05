import java.util.concurrent.atomic.AtomicReference;

public final class ConcurrentStack<T> {
    private final AtomicReference<Node<T>> head = new AtomicReference<>();

    public void push(final T element) {
        while (true) {
            final Node<T> previousHead = head.get();
            final Node<T> newNode = new Node<>(element, previousHead);
            if (head.compareAndSet(previousHead, newNode)) {
                return;
            }
        }
    }

    public T pop() {
        throw new UnsupportedOperationException();
    }

    private static final class Node<T> {
        private final T value;
        private final Node<T> next;

        public Node(final T value, final Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
