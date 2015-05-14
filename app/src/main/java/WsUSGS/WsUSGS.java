package WsUSGS;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Vos.USGSVo;

/**
 * Created by Donas4Ceos on 13/05/15.
 */
public class WsUSGS extends WsGetJsonUSGS {

    public interface WsUSGSInterface {
        void USGS(boolean result, List<USGSVo> lista);
    }

    WsUSGSInterface sender;
    Context context;

    public WsUSGS(Context context, WsUSGSInterface sender) {
        super(context);
        this.context = context;
        this.sender = sender;
    }

    public void requestUSGS(String URL) {
        this.sendRequest(URL);
    }

    @Override
    protected void response(JSONObject jsonob) {

        if (jsonob != null) {
            List<USGSVo> templist = new ArrayList<USGSVo>();
            try {

                JSONArray array = jsonob.getJSONArray("features");

                JSONObject object;

                for (int n = 0; n < array.length(); n++) {
                    USGSVo tempVO = new USGSVo();

                    object = array.getJSONObject(n);
                    tempVO.setPlace(object.getJSONObject("properties").getString("place"));
                    tempVO.setMagnitude(object.getJSONObject("properties").getString("mag"));
                    tempVO.setTime(object.getJSONObject("properties").getString("time"));
                    tempVO.setGeometria(object.getJSONObject("geometry").getString("coordinates"));
                    templist.add(tempVO);
                }


                sender.USGS(true, templist);
            } catch (Exception e) {
                e.printStackTrace();
                sender.USGS(false, null);
            }
        } else {
            sender.USGS(false, null);
        }
    }


}
