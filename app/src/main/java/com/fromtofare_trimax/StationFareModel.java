package com.fromtofare_trimax;

public class StationFareModel {
    String StationName;
    Integer iDs;

   /* public StationFareModel(String StaionName, Integer Fare){
        this.StationName = StaionName;
        this.StationFare = Fare;
    }*/

    public String getStationName() {
        return StationName;
    }

    public void setStationName(String stationName) {
        StationName = stationName;
    }


    public Integer getiDs() {
        return iDs;
    }

    public void setiDs(Integer iDs) {
        this.iDs = iDs;
    }
}
