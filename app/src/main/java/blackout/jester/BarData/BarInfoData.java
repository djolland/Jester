package blackout.jester.BarData;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Encapsualtes all general bar info
 */

public class BarInfoData implements Parcelable {

    private String address;
    private String hours;
    private String contact;

    public BarInfoData(){
        address = "";
        hours = "";
        contact = "";
    }

    public BarInfoData(String address, String hours, String contact){
        this.address = address;
        this.hours = hours;
        this.contact = contact;
    }

    // Setters
    public void setAddress(String address){
        this.address = address;
    }

    public void setHours(String hours){
        this.hours = hours;
    }

    public void setContact(String contact){
        this.contact = contact;
    }

    //Getters
    public String getAddress(){
        return address;
    }

    public String getHours(){
        return hours;
    }

    public String getContact(){
        return contact;
    }

    //Parceling data
    private BarInfoData(Parcel in) {
        this.address = in.readString();
        this.hours = in.readString();
        this.contact = in.readString();
    }

    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.address);
        out.writeString(this.hours);
        out.writeString(this.contact);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public BarInfoData createFromParcel(Parcel in) {
            return new BarInfoData(in);
        }

        public BarInfoData[] newArray(int size) {
            return new BarInfoData[size];
        }
    };
}
