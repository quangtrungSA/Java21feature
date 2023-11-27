package PatternMatching;

import javax.swing.text.Position;

public class Test {
    public static void main(String[] args) {
        Object obj = "123";
        //old in java 21
        if (obj instanceof String s && s.length() > 5)  System.out.println(s.toUpperCase());
        else if (obj instanceof String s)               System.out.println(s.toLowerCase());
        else if (obj instanceof Integer i)              System.out.println(i * i);

        //new in java 21
        switch (obj) {
            case String s when s.length() > 5 -> System.out.println(s.toUpperCase());
            case String s                     -> System.out.println(s.toLowerCase());
            case Integer i                    -> System.out.println(i * i);
            default                           -> {}
        }
    }
}
