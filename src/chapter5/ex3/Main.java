package chapter5.ex3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Main {
    static Map<Integer, Long> values = new ConcurrentHashMap<>() {{
        put(0, 0L);
        put(1, 1L);
    }};

    public static void main(String[] args) {

        System.out.println(fibonacci(10));
        System.out.println(values);
    }

    public static Long fibonacci(int x) {

        Long computeIfAbsent = values.computeIfAbsent(x, n -> fibonacci(n - 2) + fibonacci(n - 1));
        return computeIfAbsent;
    }

}
