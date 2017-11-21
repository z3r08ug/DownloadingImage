package dev.uublabs.downloadingimage;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    public static final String IMAGE_URL = "http://images5.fanpop.com/image/photos/31300000/beautiful-heart-pic-beautiful-pictures-31395948-333-500.jpg";
    public static final String KEY_URL = "image_url";
    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private MyBroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBroadcastReceiver = new MyBroadcastReceiver();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACTION.DOWNLOAD);
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(myBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        unregisterReceiver(myBroadcastReceiver);
    }

    public void downloadImage(View view)
    {
        Log.d(TAG, "downloadImage: start the service");
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra(KEY_URL, IMAGE_URL);
        startService(intent);
    }

}
