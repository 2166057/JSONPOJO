package me.premium.jsonpojo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.annotations.Expose;
import me.premium.jsonpojo.api.JSONPOJO;

import java.lang.reflect.Type;

public class Foo extends JSONPOJO<Foo> {
    public Foo(Boolean exposed) {
        super(Foo.class, exposed);
    }

    @Expose
    public String exposedString = "exposed String is visible";
    public String regularString = "regular String is visible";


//    public void exemple(){
//        //this is just a JSON String
//        String foojson = new Foo().toJsonString();
//
//        //now parsing the JSON String back into a new Object.
//        Foo foo = new Foo().fromJSON(foojson);
//    }
}
