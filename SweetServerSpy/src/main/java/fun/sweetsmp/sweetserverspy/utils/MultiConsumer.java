package fun.sweetsmp.sweetserverspy.utils;

@FunctionalInterface
public interface MultiConsumer<T, U> {
    public void accept(T t, U u);
}
