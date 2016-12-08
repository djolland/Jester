package blackout.jester.DealsTab;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

import blackout.jester.BarData.BarData;
import blackout.jester.BarData.BarDealData;
import blackout.jester.BarData.DealType;

/**
 * Encapsulates all data needed to display a Deal in any of the available lists.
 *
 * This class is meant to be used with an ArrayAdapter.
 */

public class DealListItem implements Parcelable, Comparable<DealListItem> {

    private BarData barData;
    private String barImage;
    private String barName;
    private String dealTabText;
    private String profileDealText;
    private DealType dealType;
    private BarDealData dealData;

    public DealListItem(BarData barData, int dealPosition){
        this.barData = barData;
        this.barImage = barData.getBarListImage();
        this.barName = barData.getBarName();
        this.dealData = barData.getDeal(dealPosition);
        this.dealType = this.dealData.getDealType();

        // Constructing the deal text that will appear in the main Deal Tab List
        this.dealTabText = barData.getDeal(dealPosition)
                .getDescription() +
                "\nPrice: $" + this.dealData.getPrice();

        // Constructing the deal text that will appear in the Deal Tab list in the
        // Bar Profile page.
        this.profileDealText = barData.getDeal(dealPosition)
                .getDescription() + " - $" + this.dealData.getPrice();
    }

    // Getters
    public BarData getBarData(){return barData;};
    public String getBarImage(){return barImage;}
    public String getBarName(){return barName;}
    public String getDealTabText(){return dealTabText;}
    public String getProfileDealText(){return profileDealText;}
    public DealType getDealType(){return dealType;}
    public BarDealData getDealData(){return dealData;}

    // Parceling data
    private DealListItem(Parcel in) {
        String[] data = new String[3];

        in.readParcelable(this.barData.getClass().getClassLoader());
        in.readStringArray(data);
        this.barImage = data[0];
        this.barName = data[1];
        this.dealTabText = data[2];
        this.dealType = DealType.valueOf(in.readString());
    }

    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(this.barData, flags);
        out.writeStringArray(new String[] {
                this.barImage,
                this.barName,
                this.dealTabText});
        out.writeString(dealType.name());
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public DealListItem createFromParcel(Parcel in) {
            return new DealListItem(in);
        }

        public DealListItem[] newArray(int size) {
            return new DealListItem[size];
        }
    };

    @Override
    public int compareTo(DealListItem o) {
        return this.dealData.compareTo(o.dealData);
    }
}
