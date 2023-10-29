package at.michaeladam.util.data;

import java.util.Optional;

public class Pair <L, R> {


    private final L left;
    private final R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }

    public static <L, R> Pair<L, R> of(L left, R right) {
        if (left == null || right == null) throw new IllegalArgumentException("pair values must not be null");
        return new Pair<>(left, right);
    }

    public static <L, R> Pair<L, R> of(Pair<L, R> pair) {
        return new Pair<>(pair.getLeft(), pair.getRight());
    }


    public static <L, R> Pair<Optional<L>, Optional<R>> ofNullable(L left, R right) {
        return new Pair<>(Optional.ofNullable(left), Optional.ofNullable(right));
    }




}
