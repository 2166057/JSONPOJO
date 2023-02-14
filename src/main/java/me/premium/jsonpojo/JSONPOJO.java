package me.premium.jsonpojo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class JSONPOJO<K> {
    //Don't Touch
    private final Gson gson;


    //Only use this when overriding toString()
    protected Gson getGson(){
        return this.gson;
    }

    final Class<K> typeParameterClass;

    //Constructor where you specify the class your working and allow use annotations only.
    public JSONPOJO(Class<K> typeParameterClass,Boolean exposedOnly) {
        if (exposedOnly){
            gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        }else {
            gson = new GsonBuilder().create();
        }
        this.typeParameterClass = typeParameterClass;
    }

    
    protected K fromJSON(String json){
        return gson.fromJson(json, typeParameterClass);
    }

    public String toJsonString(){
        return gson.toJson(this, typeParameterClass);
    }

}
