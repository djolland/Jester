package blackout.jester.Filter;

import android.os.Parcel;
import android.os.Parcelable;

import blackout.jester.BarData.DealType;

/**
 * Created by djolland on 12/6/2016.
 */

public class FilterListItem implements Parcelable{

    private Enum filterType;
    private Boolean isChecked;

    public FilterListItem(Enum filterType, Boolean isChecked){
        this.filterType = filterType;
        this.isChecked = isChecked;
     }

    // Getters
    public Enum getFilterType(){return filterType;};
    public Boolean getIsChecked(){return isChecked;}

    // Setters
    public void setIsChecked(Boolean bool){
        isChecked = bool;
    }

    // Parceling data
    private FilterListItem(Parcel in) {
        this.filterType = DealType.valueOf(in.readString());
        boolean[] boolIn = new boolean[1];
        in.readBooleanArray(boolIn);
        this.isChecked = boolIn[0];
    }

    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.filterType.name());
        boolean[] boolOut = new boolean[1];
        boolOut[0] = this.isChecked;
        out.writeBooleanArray(boolOut);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public FilterListItem createFromParcel(Parcel in) {
            return new FilterListItem(in);
        }

        public FilterListItem[] newArray(int size) {
            return new FilterListItem[size];
        }
    };
}
