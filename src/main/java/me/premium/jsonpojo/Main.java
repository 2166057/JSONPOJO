package me.premium.jsonpojo;

public class Main {

    public static void main(String args[]){
        Foo efoo = new Foo(true);
        String estringfoo = efoo.toJsonString();

        Foo foo = new Foo(false);
        String stringfoo = foo.toJsonString();
        System.out.println("(JSONPOJO DEMO)");

        System.out.println("Here is an example of a simple Implementation of JSONPOJO");

        System.out.println("JSONPOJO - with exposed enable\n\n"+ estringfoo + "\n\n");

        System.out.println("JSONPOJO - with exposed disable\n\n" + stringfoo);
    }
}
