package pe.edu.upc.tottus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;

import pe.edu.upc.tottus.R;
import pe.edu.upc.tottus.fragments.HomeFragment;
import pe.edu.upc.tottus.fragments.NotificationFragment;
import pe.edu.upc.tottus.fragments.SearchFragment;

import static pe.edu.upc.tottus.fragments.SearchFragment.REQUEST_CODE;

public class MainActivity extends AppCompatActivity {

    public TextView toolbarTitle;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return navigateAccordingTo(item.getItemId());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigateAccordingTo(R.id.navigation_home);
    }

    private Fragment getFragmentFor(int id){
        switch (id) {
            case R.id.navigation_home:
                toolbarTitle.setText("Home");
                return new HomeFragment();
            case R.id.navigation_search:
                toolbarTitle.setText("Búsqueda");
                return new SearchFragment();
            case R.id.navigation_notifications:
                toolbarTitle.setText("Notificaciones");
                return new NotificationFragment();
        }
        return null;
    }

    private boolean navigateAccordingTo(int id){
        try {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, getFragmentFor(id))
                    .commit();
            return true;
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return false;
    }
}
