package blackout.jester.DealsTab;

import android.os.Parcel;
import android.os.Parcelable;

import blackout.jester.BarData.BarData;

/**
 * Encapsulates all data needed to display a Deal in any of the available lists.
 *
 * This class is meant to be used with an ArrayAdapter.
 */

public class DealListItem implements Parcelable {

    private BarData barData;
    private String barImage;
    private String barName;
    private String dealTabText;
    private String profileDealText;

    public DealListItem(BarData barData, int dealPosition){
        this.barData = barData;
        this.barImage = barData.getBarListImage();
        this.barName = barData.getBarName();

        // Constructing the deal text that will appear in the main Deal Tab List
        this.dealTabText = barData.getDeal(dealPosition)
                .getDescription() +
                "\nPrice: $" + barData.getDeal(dealPosition).getPrice().toString();

        // Constructing the deal text that will appear in the Deal Tab list in the
        // Bar Profile page.
        this.profileDealText = barData.getDeal(dealPosition)
                .getDescription() + " - $" + barData.getDeal(dealPosition).getPrice().toString();
    }

    // Getters
    public BarData getBarData(){return barData;};
    public String getBarImage(){return barImage;}
    public String getBarName(){return barName;}
    public String getDealTabText(){return dealTabText;}
    public String getProfileDealText(){return profileDealText;}

    // Parceling data
    private DealListItem(Parcel in) {
        String[] data = new String[3];

        in.readParcelable(this.barData.getClass().getClassLoader());
        in.readStringArray(data);
        this.barImage = data[0];
        this.barName = data[1];
        this.dealTabText = data[2];
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
