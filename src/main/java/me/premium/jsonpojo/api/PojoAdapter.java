package me.premium.jsonpojo.api;

import com.google.gson.*;

public abstract class JsonPojoImplementer<K>{

    //Don't Touch
    private Gson gson;


    //Only use this when overriding toString()
    protected Gson getGson(){
        return this.gson;
    }

    final Class<K> typeParameterClass;

    //Constructor where you specify the class your working and allow use annotations only.
    public JsonPojoImplementer(Class<K> typeParameterClass) {
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        this.typeParameterClass = typeParameterClass;
    }


    public K fromJSON(String json){
        return gson.fromJson(json, typeParameterClass);
    }

    public String toJsonString(){
        return gson.toJson(this, typeParameterClass);
    }

}
