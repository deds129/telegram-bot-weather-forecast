import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {
//1.18
//067ffb5974f1131caeb53c5112b7fe1e
    //3feae23be73d960a58019e9b3035d403
    public static String getWeather(String message, Model model) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" +message+"&units=metcric&appid=3feae23be73d960a58019e9b3035d403");
Scanner in = new Scanner((InputStream)url.getContent());
String result ="";
while (in.hasNext()){
    result +=in.nextLine();
}
        JSONObject object = new JSONObject(result);
model.setName(object.getString("name"));
JSONObject main = object.getJSONObject("main");
model.setTemp(main.getDouble("temp"));
model.setHumidity(main.getDouble("humidity"));

        JSONArray getArray = object.getJSONArray("weather");
        for( int i=0;i<getArray.length();i++){
            JSONObject obj = getArray.getJSONObject(i);
            model.setIcon((String)obj.get("icon"));
            model.setMain((String)obj.get("main"));
        }
        return "City: " + model.getName()+ '\n'+ "Main: "+ model.getMain()+ '\n'+ "Temperature :" + model.getTemp() + " C" +'\n' +
                "Humadity: " +model.getHumidity() +" %"+'\n' + "http://openweathermap.org/img/wn/" +
                model.getIcon() + ".png";

    }
}

