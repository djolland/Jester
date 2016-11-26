package blackout.jester.DealsTab;

import android.os.Parcel;
import android.os.Parcelable;

import blackout.jester.BarData.BarData;

/**
 * Created by djoll on 11/16/2016.
 */

public class DealListItem implements Parcelable {

    private BarData barData;
    private String barImage;
    private String barName;
    private String dealText;

    public DealListItem(BarData barData, int dealPosition){
        this.barData = barData;
        this.barImage = barData.getBarListImage();
        this.barName = barData.getBarName();
        this.dealText = barData.getDeal(dealPosition)
                .getDescription() +
                "\nPrice: $" + barData.getDeal(dealPosition).getPrice().toString();
    }

    // Getters
    public BarData getBarData(){return barData;};
    public String getBarImage(){return barImage;}
    public String getBarName(){return barName;}
    public String getDealText(){return dealText;}

    //Parceling data
    private DealListItem(Parcel in) {
        String[] data = new String[3];

        in.readStringArray(data);
        this.barImage = data[0];
        this.barName = data[1];
        this.dealText = data[2];
    }

    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeStringArray(new String[] {
                this.barImage,
                this.barName,
                this.dealText});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public DealListItem createFromParcel(Parcel in) {
            return new DealListItem(in);
        }

        public DealListItem[] newArray(int size) {
            return new DealListItem[size];
        }
    };

}
