package dev.uublabs.downloadingimage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Admin on 11/21/2017.
 */

public class MyBroadcastReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        String action = intent.getAction();
        String path = intent.getStringExtra(Constants.KEYS.KEY_DOWNLOAD);
        Log.d("RECEIVER", "onReceive: receiving broadcast");

        switch (action)
        {
            case Constants.ACTION.DOWNLOAD:
            {

                Toast.makeText(context, path, Toast.LENGTH_LONG).show();
                break;
            }
        }
    }
}
