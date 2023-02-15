package me.premium.jsonpojo.api;

import com.google.gson.*;
import me.premium.jsonpojo.Foo;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public abstract class JSONPOJO<K> implements JsonSerializer<K>, JsonDeserializer<K> {
    //Don't Touch
    private Gson gson;


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
            gson = new GsonBuilder().registerTypeAdapter(typeParameterClass,this).excludeFieldsWithModifiers(Modifier.PRIVATE).create();
        }
        this.typeParameterClass = typeParameterClass;
    }


    protected K fromJSON(String json){
        return gson.fromJson(json, typeParameterClass);
    }

    public String toJsonString(){
        return gson.toJson(this, typeParameterClass);
    }


    @Override
    public K deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        K object = null;
            try {
                object = (K) type.getClass().newInstance();
                Field[] fields = object.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (jsonObject.has(field.getName())) {
                        JsonElement jsonElement = jsonObject.get(field.getName());
                        Object value = jsonDeserializationContext.deserialize(jsonElement, field.getGenericType());
                        field.set(object, value);
                    }
                }
            } catch (InstantiationException | IllegalAccessException e) {

            }
            return object;
    }

    @Override
    public JsonElement serialize(K a, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        Field[] fields = a.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(a);
                JsonElement jsonElement = jsonSerializationContext.serialize(value);
                jsonObject.add(field.getName(), jsonElement);
            } catch (IllegalAccessException e) {
            }
        }
        return jsonObject;
    }
}
