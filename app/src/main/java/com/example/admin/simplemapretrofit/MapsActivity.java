package com.example.admin.simplemapretrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Point> pointList;
    private TextView twDescription;
    private SlidingUpPanelLayout slidingUpPanelLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        twDescription = findViewById(R.id.twDescription);
        slidingUpPanelLayout = findViewById(R.id.sliding_layout);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapFragment.setRetainInstance(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_maps;
    }

    @Override
    protected View setViewForSnackBar() {
        return twDescription;
    }

    @Override
    protected void reloadData() {
        short DEFAULT_PARAM = 2;
        AppSimpleMapRetrofit.get().createService(GetDataAPI.class).listData(DEFAULT_PARAM).enqueue(new Callback<List<Point>>() {
            @Override
            public void onResponse(Call<List<Point>> call, Response<List<Point>> response) {
                if (response.isSuccessful()) {
                    pointList = response.body();
                    if (mMap != null) {
                        mMap.clear();
                        for (Point p : pointList) {
                            LatLng latLng = new LatLng(p.getLat(), p.getLng());
                            mMap.addMarker(new MarkerOptions().position(latLng).title(p.getTitle()).snippet(p.getDescription()));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Point>> call, Throwable t) {
                Toast.makeText(MapsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                twDescription.setText(marker.getSnippet());
                slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                return false;
            }
        });
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
            }
        });
    }

}
