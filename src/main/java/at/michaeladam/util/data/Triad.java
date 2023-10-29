package at.michaeladam.util.data;

import java.util.Optional;

public class Triad <L, M, R>{


        private final L left;
        private final M middle;
        private final R right;

        public Triad(L left, M middle, R right) {
            this.left = left;
            this.middle = middle;
            this.right = right;
        }

        public L getLeft() {
            return left;
        }

        public M getMiddle() {
            return middle;
        }

        public R getRight() {
            return right;
        }

        @Override
        public String toString() {
            return "Triad{" +
                    "left=" + left +
                    ", middle=" + middle +
                    ", right=" + right +
                    '}';
        }

        public static <L, M, R> Triad<L, M, R> of(L left, M middle, R right) {
            if (left == null || middle == null || right == null) throw new IllegalArgumentException("triad values must not be null");
            return new Triad<>(left, middle, right);
        }

        public static <L, M, R> Triad<L, M, R> of(Triad<L, M, R> triad) {
            return new Triad<>(triad.getLeft(), triad.getMiddle(), triad.getRight());
        }


        public static <L, M, R> Triad<Optional<L>, Optional<M>, Optional<R>> ofNullable(L left, M middle, R right) {
            return new Triad<>(Optional.ofNullable(left), Optional.ofNullable(middle), Optional.ofNullable(right));
        }
}
