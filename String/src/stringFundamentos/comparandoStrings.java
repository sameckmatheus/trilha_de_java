package stringFundamentos;

public class comparandoStrings {
    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = "HELLO";

        System.out.println(s1.equals("Hello"));
        System.out.println(s1.equals(s2));
        System.out.println(s1.equalsIgnoreCase(s2));
        System.out.println(s1.equalsIgnoreCase("azul"));
    }
}
