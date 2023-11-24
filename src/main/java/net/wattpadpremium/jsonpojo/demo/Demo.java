package net.wattpadpremium.jsonpojo.demo;

import com.google.gson.annotations.Expose;
import net.wattpadpremium.jsonpojo.api.PojoAdapter;


public class Main {

    public static void main(String args[]){
        Foo demoGlobal = new Foo();

        System.out.println("Foo has 2 field one of them is private and the other one is public and exposed");

        System.out.println("Global Demo\n");

        System.out.println("PojoToJson Global :"+ PojoGlobalApi.convertToJsonString(demoGlobal.getClass(),demoGlobal));

        Foo demo = PojoGlobalApi.convertToPojo(PojoGlobalApi.convertToJsonString(demoGlobal.getClass(),demoGlobal),Foo.class);

        System.out.println("PojoToJson implementation with class extend "+demo.toJsonString());

    }


    public static class Foo extends PojoAdapter<Foo> {
        public Foo() {
            super(Foo.class);
        }

        @Expose
        public String exposedString = "exposed String is visible";
        private String regularString = "regular String is visible";
    }
}
