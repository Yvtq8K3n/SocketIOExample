import io.socket.client.IO;
import io.socket.client.Socket;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.geom.Point2D;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] arg) {
        System.out.println("Hihi");

        try {
            Socket socket = IO.socket("http://localhost:8080");
            socket.connect();

            socket.on(Socket.EVENT_CONNECT, args -> {
                System.out.println("Connection successful");
            }).on("contents", args -> {
                JSONArray data = (JSONArray) args[0];

                for (int i = 0; i < data.length(); ++i) {
                    try {
                        JSONObject rec = data.getJSONObject(i);
                        System.out.println(rec);
                       // create(rec);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // ...
                }
                /*try {

                    name = data.getString("id");
                } catch (JSONException e) {

                }*/

            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static ContentBase create(JSONObject content) throws JSONException {
        String type = content.getString("type");

        switch (type){
            case "basic":
                String name = content.getString("name");
                Point2D[] points = Helper.convertJSONtoPoints(content.getJSONArray("points"));
                String creator = content.getString("creator");
                createContent(name, points, creator);
                break;

            case "composed":
                String nameComposed = content.getString("name");
                ContentBase parent = create(content.getJSONObject("parent"));
                String creatorComposed = content.getString("creator");

                createComposed(nameComposed, parent, creatorComposed);

                break;

            default: throw new JSONException("Invalid Content type");
        }
    }

    public static void createContent(String name, Point2D[] points, String creator){
        new Content(name, points, creator);
    }

    public static void createComposedContent(String name, ContentBase parent, String creator){
        new Content(name, points, creator);
    }


    public void createContentComposed(){

    }
}
