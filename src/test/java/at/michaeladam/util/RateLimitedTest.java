package at.michaeladam.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;


class RateLimitedTest {


    @Test
    void testRateLimited() {
        RateLimited<Void, Integer> voidIntegerRateLimited = RateLimited.rateLimit(() -> 1, 1000L);
        {
            Optional<Integer> integer = voidIntegerRateLimited.tryExecute();

            Assertions.assertTrue(integer.isPresent());
        }
        {
            Optional<Integer> integer = voidIntegerRateLimited.tryExecute();

            Assertions.assertFalse(integer.isPresent());
        }
        {
            Integer integer = null;
            try {
                integer = voidIntegerRateLimited.awaitExecute();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Assertions.fail();
            }
            Assertions.assertEquals(1, integer.intValue());
        }
    }

}
