package pe.edu.upc.tottus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pe.edu.upc.tottus.R;
import pe.edu.upc.tottus.utils.PersistentUtil;

/**
 * Created by Fernando on 23/06/2018.
 */

public class AddressActivity extends AppCompatActivity {

    Button btnEnter;
    EditText edtAddress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        btnEnter = (Button) findViewById(R.id.btn_enter);
        edtAddress = (EditText) findViewById(R.id.edt_address);


        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersistentUtil.saveAddress(getApplicationContext(), edtAddress.getText().toString());

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}
