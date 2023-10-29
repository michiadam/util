package at.michaeladam.util.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

 class PairTest {


    @Test
    void testPair() {
        Pair<String, Integer> pair = Pair.of("test", 1);

        Assertions.assertEquals("test", pair.getLeft());
        Assertions.assertEquals(1, pair.getRight().intValue());


        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Pair.of(null, 1);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Pair.of("test", null);
        });


        Pair<Optional<Object>, Optional<Integer>> optionalOptionalPair = Pair.ofNullable(null, 1);

        Assertions.assertFalse(optionalOptionalPair.getLeft().isPresent());

        Assertions.assertTrue(optionalOptionalPair.getRight().isPresent());

    }

    @Test
    void testTriad() {
        Triad<String, Integer, Boolean> triad = Triad.of("test", 1, true);

        Assertions.assertEquals("test", triad.getLeft());
        Assertions.assertEquals(1, triad.getMiddle().intValue());
        Assertions.assertEquals(true, triad.getRight());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Triad.of(null, 1, true);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Triad.of("test", null, true);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Triad.of("test", 1, null);
        });

        Triad<Optional<Object>, Optional<Integer>, Optional<Boolean>> optionalOptionalTriad = Triad.ofNullable(null, 1, true);

        Assertions.assertFalse(optionalOptionalTriad.getLeft().isPresent());
        Assertions.assertTrue(optionalOptionalTriad.getMiddle().isPresent());
        Assertions.assertTrue(optionalOptionalTriad.getRight().isPresent());
    }


    @Test
    void testQuad() {
        Quad<String, Integer, Boolean, Double> quad = Quad.of("test", 1, true, 1.0);

        Assertions.assertEquals("test", quad.getFirst());
        Assertions.assertEquals(1, quad.getSecond().intValue());
        Assertions.assertEquals(true, quad.getThird());
        Assertions.assertEquals(1.0, quad.getFourth());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Quad.of(null, 1, true, 1.0);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Quad.of("test", null, true, 1.0);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Quad.of("test", 1, null, 1.0);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Quad.of("test", 1, true, null);
        });

        Quad<Optional<Object>, Optional<Integer>, Optional<Boolean>, Optional<Double>> optionalOptionalQuad = Quad.ofNullable(null, 1, true, 1.0);

        Assertions.assertFalse(optionalOptionalQuad.getFirst().isPresent());
        Assertions.assertTrue(optionalOptionalQuad.getSecond().isPresent());
        Assertions.assertTrue(optionalOptionalQuad.getThird().isPresent());
        Assertions.assertTrue(optionalOptionalQuad.getFourth().isPresent());
    }

}
