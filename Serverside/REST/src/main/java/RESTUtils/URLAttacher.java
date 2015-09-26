package RESTUtils;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Created by Remco on 26-9-2015.
 */
public class URLAttacher {

    public static JsonElement attachURLS(JsonElement jElement, String url, Identifiers identifier){
        JsonElement finalElement = null;

        if (jElement.isJsonArray()){
            JsonArray jArray = jElement.getAsJsonArray();
            for (int i = 0; i < jArray.size(); i++){
                JsonObject jObj = jArray.get(i).getAsJsonObject();
                jObj.addProperty("self", url + "/" + jObj.get(identifier.identifier));
            }
            finalElement = jArray;
        }

        return finalElement;
    }
}
