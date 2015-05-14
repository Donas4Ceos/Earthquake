package WsUSGS;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.donas4ceos.earthquake.R;

import org.json.JSONObject;


/**
 * Created by Donas4Ceos on 13/05/15.
 */
public class WsGetJsonUSGS extends AsyncTask<String, String, JSONObject> {
    private ProgressDialog pDialog;

    Context LoContext;

    public WsGetJsonUSGS(Context context) {
        this.LoContext = context;
    }
    protected void response(JSONObject json) {}

    protected void sendRequest(String url) {

        this.execute(url);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pDialog = new ProgressDialog(LoContext);
        pDialog.setMessage(LoContext.getString(R.string.dialog_mess));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();

    }

    @Override
    protected JSONObject doInBackground(String... args) {
        WsUSGSParcer jParser = new WsUSGSParcer();
        JSONObject json = jParser.getJSONFromUrl(args[0]);
        return json;
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        pDialog.dismiss();
        response(json);

    }



}

