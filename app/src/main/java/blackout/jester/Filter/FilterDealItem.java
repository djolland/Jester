package blackout.jester.Filter;

import android.os.Parcel;
import android.os.Parcelable;

import blackout.jester.BarData.DealType;

/**
 * Created by djolland on 12/6/2016.
 */

public class FilterDealItem implements Parcelable{

    private DealType dealType;
    private Boolean isChecked;

    public FilterDealItem(DealType dealType, Boolean isChecked){
        this.dealType = dealType;
        this.isChecked = isChecked;
     }

    // Getters
    public DealType getDealType(){return dealType;};
    public Boolean getIsChecked(){return isChecked;}

    // Setters
    public void setIsChecked(Boolean bool){
        isChecked = bool;
    }

    // Parceling data
    private FilterDealItem(Parcel in) {
        this.dealType = DealType.valueOf(in.readString());
        boolean[] boolIn = new boolean[1];
        in.readBooleanArray(boolIn);
        this.isChecked = boolIn[0];
    }

    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.dealType.name());
        boolean[] boolOut = new boolean[1];
        boolOut[0] = this.isChecked;
        out.writeBooleanArray(boolOut);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public FilterDealItem createFromParcel(Parcel in) {
            return new FilterDealItem(in);
        }

        public FilterDealItem[] newArray(int size) {
            return new FilterDealItem[size];
        }
    };
}
