package com.fromtofare_trimax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseTO databaseTO;
    Spinner spinnerfrom, spinnerto;
    Button button;
    SpinnerArrayList spinnerAdapter;
    Integer fromID;
    String FromStation,ToStation;
    Integer toID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);
        databaseTO = new DatabaseTO(MainActivity.this);
        spinnerfrom = findViewById(R.id.spinner_from);
        spinnerto = findViewById(R.id.spinner_to);
        button = findViewById(R.id.button);
        /*spinner binding*/
        List<StationFareModel> stationFareModelList = databaseTO.getStation();
        spinnerAdapter = new SpinnerArrayList(this, (ArrayList<StationFareModel>) stationFareModelList);
        spinnerfrom.setAdapter(spinnerAdapter);
        spinnerto.setAdapter(spinnerAdapter);

        spinnerto.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id) {

                        StationFareModel clickedItem = (StationFareModel)
                                parent.getItemAtPosition(position);
                         toID = clickedItem.getiDs();
                         ToStation = clickedItem.getStationName();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
/*station selection*/
        spinnerfrom.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id) {

                        StationFareModel clickedItem = (StationFareModel)
                                parent.getItemAtPosition(position);
                         fromID = clickedItem.getiDs();
                        FromStation = clickedItem.getStationName();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*Station Fare Calculation*/
                Long fromStation = databaseTO.numberofstation() - fromID;
                Long toStation =  databaseTO.numberofstation() - toID ;
                Long NumberofStation = (long) (fromStation - toStation);

                Long fareAmiount = NumberofStation * databaseTO.getbasefare();
                Intent i = new Intent(MainActivity.this,FinalFare.class);
                i.putExtra("Fare",String.valueOf(Math.abs(fareAmiount)));
                i.putExtra("FromStation",FromStation);
                i.putExtra("ToStation",ToStation);
                startActivity(i);

                Toast.makeText(MainActivity.this, String.valueOf(Math.abs(fareAmiount)) + " selected", Toast.LENGTH_SHORT).show();
            }
        });

    }


}