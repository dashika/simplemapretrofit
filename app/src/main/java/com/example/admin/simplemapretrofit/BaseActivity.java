package com.example.admin.simplemapretrofit;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public abstract class BaseActivity extends FragmentActivity implements NetworkStateReceiver.NetworkStateReceiverListener {

    private NetworkStateReceiver networkStateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        networkStateReceiver = new NetworkStateReceiver();
    }

    protected abstract int getLayout();

    protected abstract View setViewForSnackBar();

    protected abstract void reloadData();

    @Override
    public void onResume() {
        super.onResume();
        networkStateReceiver.addListener(this);
        this.registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void onPause() {
        super.onPause();
        if (networkStateReceiver != null) {
            networkStateReceiver.removeListener(this);
            this.unregisterReceiver(networkStateReceiver);
        }
    }

    @Override
    public void networkAvailable() {
        reloadData();
    }

    @Override
    public void networkUnavailable() {
        Snackbar.make(setViewForSnackBar(), R.string.offline_mode, Snackbar.LENGTH_LONG).show();
    }

}
