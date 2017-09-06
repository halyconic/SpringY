package com.example;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

/*
 * TODO: Use idiomatic method of testing for controllers
 */
public class YControllerTest {

    @Test
    public void test() {
        assertEquals(BigInteger.valueOf(5), YController.Y(YController.fib).apply(5));
        assertEquals(BigInteger.valueOf(55), YController.Y(YController.fib).apply(10));
        assertEquals(BigInteger.valueOf(610), YController.Y(YController.fib).apply(15));
        assertEquals(BigInteger.valueOf(6765), YController.Y(YController.fib).apply(20));
        assertEquals(BigInteger.valueOf(75025), YController.Y(YController.fib).apply(25));
        
        assertEquals(BigInteger.valueOf(5), YController.YMem(YController.fib).apply(5));
        assertEquals(BigInteger.valueOf(55), YController.YMem(YController.fib).apply(10));
        assertEquals(BigInteger.valueOf(610), YController.YMem(YController.fib).apply(15));
        assertEquals(BigInteger.valueOf(6765), YController.Y(YController.fib).apply(20));
        assertEquals(BigInteger.valueOf(75025), YController.Y(YController.fib).apply(25));
    }
}
