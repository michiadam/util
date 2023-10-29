package at.michaeladam.util.data;

import java.util.Optional;

public class Quad <L1,L2,R1,R2>{

    private final L1 left1;
    private final L2 left2;
    private final R1 right1;
    private final R2 right2;


    public Quad(L1 left1, L2 left2, R1 right1, R2 right2) {
        this.left1 = left1;
        this.left2 = left2;
        this.right1 = right1;
        this.right2 = right2;
    }

    public L1 getFirst() {
        return left1;
    }

    public L2 getSecond() {
        return left2;
    }

    public R1 getThird() {
        return right1;
    }

    public R2 getFourth() {
        return right2;
    }

    @Override
    public String toString() {
        return "Quad{" +
                "left1=" + left1 +
                ", left2=" + left2 +
                ", right1=" + right1 +
                ", right2=" + right2 +
                '}';
    }

    public static <L1,L2,R1,R2> Quad<L1,L2,R1,R2> of(L1 left1, L2 left2, R1 right1, R2 right2) {
        if (left1 == null || left2 == null || right1 == null || right2 == null) throw new IllegalArgumentException("quad values must not be null");
        return new Quad<>(left1, left2, right1, right2);
    }

    public static <L1,L2,R1,R2> Quad<L1,L2,R1,R2> of(Quad<L1,L2,R1,R2> quad) {
        return new Quad<>(quad.getFirst(), quad.getSecond(), quad.getThird(), quad.getFourth());
    }

    public static <L1,L2,R1,R2> Quad<Optional<L1>,Optional<L2>,Optional<R1>,Optional<R2>> ofNullable(L1 left1, L2 left2, R1 right1, R2 right2) {
        return new Quad<>(Optional.ofNullable(left1), Optional.ofNullable(left2), Optional.ofNullable(right1), Optional.ofNullable(right2));
    }
}


