package net.wattpadpremium.jsonpojo.api;


import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.lang.reflect.Field;

public class PojoGlobalApi {

    public static String convertToJsonString(Class<?> clazz, Object object) {
        // Use Gson library to convert object to JSON string
        Gson gson = new Gson();

        // Use reflection to iterate through all fields of the object and add them to a JSON object
        JsonObject jsonObject = new JsonObject();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                if (value != null) {
                    jsonObject.add(field.getName(), gson.toJsonTree(value));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error accessing field " + field.getName() + " of " + clazz.getName(), e);
            }
        }
        return gson.toJson(jsonObject);
    }

    public static <T> T convertToPojo(String jsonString, Class<T> clazz) {
        // Use Gson library to convert JSON string to a JSON object
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

        // Create a new instance of the class to set the field values
        T newInstance;
        try {
            newInstance = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Error creating new instance of " + clazz.getName(), e);
        }

        // Use reflection to iterate through all fields of the class and set the values from the JSON object
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (jsonObject.has(field.getName())) {
                Object value = gson.fromJson(jsonObject.get(field.getName()), field.getType());
                try {
                    field.set(newInstance, value);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error setting field " + field.getName() + " of " + clazz.getName(), e);
                }
            }
        }

        return newInstance;
    }
}
