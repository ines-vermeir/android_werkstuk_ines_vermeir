package vermeir.ines.ehb.be.androidwerkstuk.Model;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ines on 7/12/2018.
 */

public class answerConverter {
    @TypeConverter
    public static HashMap<String, Boolean> fromString(String value) {
        Type mapType = new TypeToken<HashMap<String, Boolean>>() {
        }.getType();
        return new Gson().fromJson(value, mapType);
    }

    @TypeConverter
    public static String fromStringMap(Map<String, Boolean> map) {
        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
