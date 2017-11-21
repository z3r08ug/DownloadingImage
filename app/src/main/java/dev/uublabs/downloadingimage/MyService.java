package dev.uublabs.downloadingimage;

import android.app.IntentService;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Admin on 11/21/2017.
 */

public class MyService extends IntentService
{

    private String filepath;
    public static final String TAG = "SERVICE";

    public MyService()
    {
        super("MyService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent)
    {
        String img_url = intent.getStringExtra(MainActivity.KEY_URL);

        try
        {
            URL url = new URL(img_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);

            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            File mypath=new File(directory,"image.jpg");

            FileOutputStream fos = null;
            fos = new FileOutputStream(mypath);

            myBitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
            filepath = mypath.getAbsolutePath();
        }
        catch (IOException e)
        {
            filepath=null;
            e.printStackTrace();
        }

        Intent intent1 = new Intent();
        intent1.setAction(Constants.ACTION.DOWNLOAD);
        intent1.putExtra(Constants.KEYS.KEY_DOWNLOAD, filepath);
        sendBroadcast(intent1);
    }
}
