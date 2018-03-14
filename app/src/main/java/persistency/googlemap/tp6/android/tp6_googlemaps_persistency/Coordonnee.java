package persistency.googlemap.tp6.android.tp6_googlemaps_persistency;

/**
 * Created by Alex on 14/03/2018.
 */

public class Coordonnee {
    private long id;
    private int lat;
    private int lng;

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public int getLat(){
        return lat;
    }

    public void setLat(int lat){
        this.lat = lat;
    }

    public int getLng() {
        return lng;
    }

    public void setLng(int lng) {
        this.lng = lng;
    }
}
