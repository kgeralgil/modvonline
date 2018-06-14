package pe.edu.upc.tottus.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import pe.edu.upc.tottus.R;
import pe.edu.upc.tottus.fragments.HomeFragment;
import pe.edu.upc.tottus.fragments.SearchVoiceFragment;
import pe.edu.upc.tottus.fragments.SearchQRFragment;

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
                toolbarTitle.setText("Inicio");
                return new HomeFragment();
            case R.id.navigation_search:
                toolbarTitle.setText("Búsqueda por QR");
                return new SearchQRFragment();
            case R.id.navigation_notifications:
                toolbarTitle.setText("Búsqueda por Voz");
                return new SearchVoiceFragment();
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
