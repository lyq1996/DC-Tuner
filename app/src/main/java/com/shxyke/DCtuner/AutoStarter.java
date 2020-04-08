package com.shxyke.DCtuner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class AutoStarter extends BroadcastReceiver {

    private SharedPreferences sharedPreferences;
    public AutoStarter()
    {
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
        {
            if(!Utilties.haveRoot()){
                return;
            }
            if(!Utilties.isDCKernel()){
                return;
            }
            sharedPreferences = context.getSharedPreferences("share", context.MODE_PRIVATE);
            if(sharedPreferences.getBoolean(context.getString(R.string.auto_run_status),false)){
                Utilties.set_elvss_num(sharedPreferences.getInt(context.getString(R.string.elvss_min),66));
                Utilties.set_dc_status(true);
                if(Utilties.isColorModKernel()){
                    if(sharedPreferences.getBoolean(context.getString(R.string.srgb_status), false)){
                        Utilties.set_srgb_status(true);
                    }
                    if(sharedPreferences.getBoolean(context.getString(R.string.dci_p3_status), false)){
                        Utilties.set_dci_p3_status(true);
                    }
                }
                Utilties.update_status(context);
            }
        }
    }
}
