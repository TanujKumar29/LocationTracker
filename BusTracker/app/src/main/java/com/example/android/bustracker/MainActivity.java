package com.example.android.bustracker;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap m_map;
    boolean mapReady=false;




    public String executePost(String targetURL, String urlParameters)
    {
        URL url;
        HttpURLConnection connection = null;
        try {
            //Create connection
            url = new URL(targetURL);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length", "" +
                    Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream ());
            Log.i("responseee", urlParameters);
            wr.writeBytes (urlParameters);
            wr.flush ();
            wr.close ();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = rd.readLine()) != null) {

                response.append(line);
                response.append('\r');
            }
            rd.close();


            return response.toString();

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {

            if(connection != null) {
                connection.disconnect();
            }
        }
    }

   /* public String retrieve()
    {
        String valRetrieved;
        while(true) {

            valRetrieved= executePost("http://0032029f.ngrok.io/ServerMapApp/SentJSONObj.do","Connected with app");
            System.out.println("Request sent");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return valRetrieved;
        }

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
        setContentView(R.layout.activity_main);
        Button btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mapReady)
                    m_map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });

        Button btnSatellite = (Button) findViewById(R.id.btnSatellite);
        btnSatellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mapReady)
                    m_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });

        Button btnHybrid = (Button) findViewById(R.id.btnHybrid);
        btnHybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mapReady)
                    m_map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map){
        mapReady=true;
        m_map = map;
        LatLng newYork = new LatLng(40.7484,-73.9857);
        CameraPosition target = CameraPosition.builder().target(newYork).zoom(14).build();
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));

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

        return super.onOptionsItemSelected(item);
    }
    /*private void flyTo(CameraPosition target)
    {
        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target), 1000, null);



    }*/
public void callOnButtonClick(View view)
{
   // estConnection();
    callAsynchronousTask();
/*new Thread() {
    @Override
    public void run() {
        super.run();
        estConnection();
        try {
            sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}.start();*/
}
    /*public void drawMap(String lat,String lon) {
        // String date=new Date().toString();

        CameraPosition target = CameraPosition.builder()
                .target(new LatLng(Double.parseDouble(lat),Double.parseDouble(lon)))
                .zoom(15)
                .bearing(90)
                .tilt(30)
                .build();

        m_map.setMyLocationEnabled(true);
        m_map.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(lat),Double.parseDouble(lon))).title("Object Location"));
        //Log.i("Map ready", );
        if(mapReady)
            m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target), 10000, null);
            //m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
           // flyTo(SEATTLE);
    }*/
public void estConnection()
{
    String val;
    final String lat,lon;
   // Button btnSeattle = (Button) findViewById(R.id.btnTrack);
    String[] coordinateVal=new String[2];
    val= executePost("http://3bc9b09d.ngrok.io/ServerMapApp/SentJSONObj.do","Connected with app");
        System.out.println("Request sent");

        coordinateVal= val.toString().split(",");
        lat=coordinateVal[0];
        Log.i("valueeeeeeeeeeeeee",lat);
        lon=coordinateVal[1];
        Log.i("valueeeeeeeeeeeeee",lon);
        CameraPosition target = CameraPosition.builder()
            .target(new LatLng(Double.parseDouble(lat),Double.parseDouble(lon)))
            .zoom(21)
            .bearing(90)
            .tilt(30)
            .build();

    m_map.setMyLocationEnabled(true);
    m_map.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(lat),Double.parseDouble(lon))).title("Object Location"));
    //Log.i("Map ready", );
    if(mapReady)
        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target), 10000, null);
       /* drawMap(lat,lon);
        btnSeattle.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // String date=new Date().toString();

            CameraPosition SEATTLE = CameraPosition.builder()
                    .target(new LatLng(Double.parseDouble(lat),Double.parseDouble(lon)))
                    .zoom(15)
                    .bearing(90)
                    .tilt(30)
                    .build();

            m_map.setMyLocationEnabled(true);
            m_map.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(lat),Double.parseDouble(lon))).title("Object Location"));
            if(mapReady)
                m_map.animateCamera(CameraUpdateFactory.newCameraPosition(SEATTLE), 10000, null);
        }
    });*/
}
    public void callAsynchronousTask() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            estConnection();
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 50000); //execute in every 50000 ms
    }

}