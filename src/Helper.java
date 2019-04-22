import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.geom.Point2D;

public class Helper {

    public static Point2D[] convertJSONtoPoints(JSONArray jsonPoints) throws JSONException {
        Point2D[] points = new Point2D[jsonPoints.length()];
        for (int i = 0; i < jsonPoints.length(); ++i) {
            JSONObject point = jsonPoints.getJSONObject(i);

            float x = (float) point.getDouble("x"); float y = (float) point.getDouble("y");
            points[i] = new Point2D.Float(x, y);
        }
        return points;
    }
}
