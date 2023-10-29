package at.michaeladam.util.time;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;
import java.time.temporal.ChronoUnit;

class TimerTest {

    @Test
     void loggerTest() {

        {
            StringWriter writer = new StringWriter();

            TimerFactory timerFactory = new TimerFactory(writer::write);

            timerFactory.time(() -> {
                System.out.println("test");
            });

            System.out.println(writer);
            Assertions.assertTrue(writer.toString().contains("ms"));
        }

        {
            StringWriter writer = new StringWriter();

            TimerFactory timerFactory = new TimerFactory(writer::write, ChronoUnit.MINUTES);

            timerFactory.time(() -> {
                System.out.println("test");
            });
            Assertions.assertFalse(writer.toString().contains("ms"));
        }

    }
}
