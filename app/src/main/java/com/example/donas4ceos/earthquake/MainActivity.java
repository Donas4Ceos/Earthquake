package com.example.donas4ceos.earthquake;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import Vos.USGSVo;
import WsUSGS.WsUSGS;


public class MainActivity extends ActionBarActivity implements
        WsUSGS.WsUSGSInterface {

    ListView LvTerremotos;
    adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LvTerremotos = (ListView) this.findViewById(R.id.LvTerremotos);
        Consultar();
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.reload) {
            Consultar();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void USGS(boolean result, List<USGSVo> lista) {

        if (result) {

            adapter = new adapter(this, lista);
            LvTerremotos.setAdapter(adapter);

        }

    }

    public void Consultar() {
        WsUSGS servicio = new WsUSGS(this, this);
        servicio.requestUSGS(getResources().getString(R.string.url_Service));
    }
}
