package newStringMethods;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        String string = "the red brown fox jumps over the lazy dog";
        String[] parts = string.splitWithDelimiters(" ", 5);
        Arrays.stream(parts).forEach(System.out::println);
    }
}
