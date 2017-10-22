package falih.myapplication;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.zip.CheckedOutputStream;

/**
 * Created by Falih on 10/22/2017.
 */

public class GetWebSource  extends AsyncTaskLoader<String> {

    private String url_link;

    public GetWebSource(Context context, String url_link) {
        super(context);
        this.url_link = url_link;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public String loadInBackground(){
        InputStream in;

        try{
            URL url = new URL(url_link);
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(20000);
            connection.setRequestMethod("GET");
            connection.connect();

            in = connection.getInputStream();

            BufferedReader buff = new BufferedReader (new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line = "";

            while ((line = buff.readLine()) !=null) {
                sb.append(line + "\n");
            }

            buff.close();
            in.close();

            return sb.toString();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

        }
    }

