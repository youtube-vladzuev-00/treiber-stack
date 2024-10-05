public final class ConcurrentStack<T> {
    private volatile Node<T> head;

    public void push(final T element) {
        throw new UnsupportedOperationException();
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
