package Vos;

import java.io.Serializable;

/**
 * Created by Donas4Ceos on 13/05/15.
 */
public class USGSVo {

    String magnitude = "";
    String place = "";
    String geometria = "";
    String time = "";

    public String getPlace() {

        return place;
    }

    public void setPlace(String place) {

        this.place = place;
    }

    public String getMagnitude() {

        return magnitude;
    }

    public void setMagnitude(String magnitude) {

        this.magnitude = magnitude;
    }

    public String getGeometria() {
        return geometria;
    }

    public void setGeometria(String geometria) {
        this.geometria = geometria;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
