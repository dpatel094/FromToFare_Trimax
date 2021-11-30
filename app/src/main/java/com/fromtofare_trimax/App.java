package com.fromtofare_trimax;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    DatabaseTO databaseTO;
    private static Context context;
    private final String StaionName[] = {
            "Versova",
            "DN Nagar",
            "Azad Nagar",
            "Andheri",
            "Western Express Highway",
            "Chakala (JB Nagar)",
            "Airport Road",
            "Marol Naka",
            "Saki Naka",
            "Asalpha",
            "Jagruti Nagar",
            "Ghatkopar"
    };


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        databaseTO = new DatabaseTO(context);

        saveFare();
    }

    private void saveFare() {
        Long stationFareModels = databaseTO.numberofstation();
        if (stationFareModels < 12) {
            creatarraylist();
            databaseTO.AddBaseFare(5);
        }
    }
    public void creatarraylist(){
        for(int i=0;i<StaionName.length;i++){
            databaseTO.AddStation(StaionName[i]);
        }

    }
}
