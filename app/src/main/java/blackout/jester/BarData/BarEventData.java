package blackout.jester.BarData;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

/**
 * Contains all data concerning a single event
 */

public class BarEventData implements Parcelable{

    private String description;
    private String time;
    private String date;
    private BigDecimal coverCharge;

    public BarEventData(String description, String time, String date, BigDecimal coverCharge){
        this.description = description;
        this.time = time;
        this.date = date;
        this.coverCharge = coverCharge;
    }

    // Getters

    public String getDescription(){
        return description;
    }

    public String getTime(){
        return time;
    }

    public String getDate(){
        return date;
    }

    public BigDecimal getCoverCharge(){
        return coverCharge;
    }

    // Setters

    //Parceling data
    private BarEventData(Parcel in) {
        String[] data = new String[1];

        in.readStringArray(data);
        this.description = data[0];
    }

    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeStringArray(new String[] {
                this.description});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public BarEventData createFromParcel(Parcel in) {
            return new BarEventData(in);
        }

        public BarEventData[] newArray(int size) {
            return new BarEventData[size];
        }
    };

}
