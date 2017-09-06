package com.example;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Y = \f.(\x.f(x x))(\x.f(x x))
 * 
 * In this code we reimplement recursion using the Y combinator.
 * This is less efficient than Java's built in recursion!
 */
@Controller
public class YController {
    interface RecursiveFunction<F> extends Function<RecursiveFunction<F>, F> { }

    public static <A,B> Function<A,B> Y(Function<Function<A,B>, Function<A,B>> f) {
        RecursiveFunction<Function<A,B>> r = w -> f.apply(x -> w.apply(w).apply(x));
        return r.apply(r);
    }
    
    public static <A,B> Function<A,B> YMem(Function<Function<A,B>, Function<A,B>> f) {
        Map<A, B> map = new HashMap<>();
        RecursiveFunction<Function<A,B>> r = w -> f.apply(x -> map.computeIfAbsent(x, w.apply(w)));
        return r.apply(r);
    }
    
    // Function defining how to get the next number in the fibonacci sequence
    public static Function<Function<Integer, BigInteger>, Function<Integer, BigInteger>> fib = f -> n ->
        (n <= 2)
            ? BigInteger.ONE
            : f.apply(n - 1).add(f.apply(n - 2));
        
    private static String getFib(int i) {
        long a = System.nanoTime();
        BigInteger v = Y(fib).apply(i);
        long b = System.nanoTime();
        return "fib(" + i  + ") = " + v + " in " + (b - a) + "ns";
    }
    
    private static String getMemoizedFib(int i) {
        long a = System.nanoTime();
        BigInteger v = YMem(fib).apply(i);
        long b = System.nanoTime();
        return "fib(" + i  + ") = " + v + " in " + (b - a) + "ns";
    }
    
    @RequestMapping("/")
    public String fibonacci(Map<String, Object> model) {
        for (int i = 5; i <= 25; i += 5) {
            model.put("y_" + i, getFib(i));
        }
        
        for (int i = 5; i <= 25; i += 5) {
            model.put("m_" + i, getMemoizedFib(i));
        }    

        return "index";
    }
}
