package blackout.jester.BarData;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Contains all data concerning a single event
 */

public class BarEventData implements Parcelable{

    private String description;

    public BarEventData(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

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
