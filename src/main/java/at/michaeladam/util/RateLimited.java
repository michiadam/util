package at.michaeladam.util;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class RateLimited<T, R> {


    private final Function<T, R> function;


    private long lastExecution = 0;
    private final long cooldown;


    private RateLimited(long cooldown, Function<T, R> function) {
        this.cooldown = cooldown;
        this.function = function;
    }


    private RateLimited(long cooldown, Consumer<T> function) {
        this(cooldown, t -> {
            function.accept(t);
            return null;
        });
    }

    private RateLimited(long cooldown, Supplier<R> supplier) {
        this(cooldown, (T t) -> supplier.get());
    }


    public boolean isReady() {
        return System.currentTimeMillis() - lastExecution > cooldown;
    }


    public Optional<R> tryExecute() {
        return tryExecute(null);
    }

    public Optional<R> tryExecute(T t) {
        if (isReady()) {
            lastExecution = System.currentTimeMillis();
            return Optional.ofNullable(function.apply(t));
        }
        return Optional.empty();
    }

    public R awaitExecute() throws InterruptedException {
        return awaitExecute(null);
    }

    public synchronized R awaitExecute(T t) throws InterruptedException {
        if (!isReady()) {
           Thread.sleep(cooldown - (System.currentTimeMillis() - lastExecution));
        }
        R applied = function.apply(t);
        lastExecution = System.currentTimeMillis();
        return applied;
    }


    public long readyIn() {
        return Math.max(0, cooldown - (System.currentTimeMillis() - lastExecution));
    }


    public static <T, R> RateLimited<T, R> rateLimit(Function<T, R> function, long cooldown) {
        return new RateLimited<>(cooldown, function);
    }

    public static <T> RateLimited<T, Void> rateLimit(Consumer<T> consumer, long cooldown) {
        return new RateLimited<>(cooldown, consumer);
    }

    public static <R> RateLimited<Void, R> rateLimit(Supplier<R> supplier, long cooldown) {
        return new RateLimited<>(cooldown, supplier);
    }


}
