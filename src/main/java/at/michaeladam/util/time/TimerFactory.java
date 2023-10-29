package at.michaeladam.util.time;

import java.time.temporal.ChronoUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class TimerFactory {


    Consumer<String> logger;
    ChronoUnit unit;

    public TimerFactory(Consumer<String> logger) {
        this(logger, ChronoUnit.MILLIS);
    }

    public TimerFactory(Consumer<String> logger, ChronoUnit unit) {
        this.logger = logger;
        this.unit = unit;
    }


    public <V, R> R time(Function<V, R> function) {

        long now = System.nanoTime();

        R result = function.apply(null);

        long duration = System.nanoTime() - now;

        this.log(duration);

        return result;
    }

    public <V> V time(Supplier<V> supplier) {

        long now = System.nanoTime();

        V result = supplier.get();

        long duration = System.nanoTime() - now;

        this.log(duration);

        return result;
    }

    public <V> void time(Consumer<V> consumer) {

        long now = System.nanoTime();

        consumer.accept(null);

        long duration = System.nanoTime() - now;

        this.log(duration);

    }

    public void time(Runnable runnable) {

        long now = System.nanoTime();

        runnable.run();

        long duration = System.nanoTime() - now;

        this.log(duration);

    }


    private void log(long duration) {
        logger.accept("Execution took " + getTime(duration) + " " + getUnitString() + ".");
    }

    private String getUnitString() {
        return switch (unit) {
            case NANOS -> "ns";
            case MICROS -> "µs";
            case MILLIS -> "ms";
            case SECONDS -> "s";
            case MINUTES -> "min";
            case HOURS -> "h";
            case DAYS -> "d";
            default -> throw new IllegalArgumentException("Unsupported unit: " + unit);
        };
    }

    private static final int PRECISION = 4;

    private String getTime(long duration) {

        double precisionNr = Math.pow(10, TimerFactory.PRECISION);

        return switch (unit) {
            case NANOS -> String.valueOf(duration);
            case MICROS -> String.valueOf(Math.round(precisionNr * (duration / 1000d)) / precisionNr);
            case MILLIS -> String.valueOf(Math.round(precisionNr * (duration / 1000000d)) / precisionNr);
            case SECONDS -> String.valueOf(Math.round(precisionNr * (duration / 1000000000d)) / precisionNr);
            case MINUTES -> String.valueOf(Math.round(precisionNr * (duration / 60000000000d)) / precisionNr);
            case HOURS -> String.valueOf(Math.round(precisionNr * (duration / 3600000000000d)) / precisionNr);
            case DAYS -> String.valueOf(Math.round(precisionNr * (duration / 86400000000000d)) / precisionNr);
            default -> throw new IllegalArgumentException("Unsupported unit: " + unit);
        };
    }


}
