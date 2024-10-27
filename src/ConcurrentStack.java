import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Optional.empty;

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

    public Optional<T> pop() {
        while (true) {
            final Node<T> previousHead = head.get();
            if (previousHead == null) {
                return empty();
            }
            if (head.compareAndSet(previousHead, previousHead.next)) {
                return Optional.of(previousHead.value);
            }
        }
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
