package blackout.jester.BarData;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Contains all the data concerning a single deal.
 */

public class BarDealData implements Comparable<BarDealData>, Parcelable{

    private String description;
    private BigDecimal price;
    private DealType dealType;
    private String date;

    public BarDealData(String description, BigDecimal price, DealType dealType, String date){
        this.description = description;
        this.price = price;
        this.dealType = dealType;
        this.date = date;
    }

    public String getDescription(){
        return description;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public DealType getDealType(){
        return dealType;
    }

    public String getDate(){return date;}

    /**
     * Currently only compares the price of two Bar Deals.
     *
     * @param o
     * @return  a negative integer, zero, or a positive integer as this object
     *          is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(BarDealData o) {
        return this.getPrice().compareTo(o.getPrice());
    }

    //Parceling data
    private BarDealData(Parcel in) {
        this.description = in.readString();
        this.price = new BigDecimal(in.readString());
        this.dealType = DealType.valueOf(in.readString());
        this.date = in.readString();
    }

    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.description);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);
        String priceString = df.format(this.price);
        out.writeString(priceString);
        out.writeString(this.dealType.name());
        out.writeString(this.date);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public BarDealData createFromParcel(Parcel in) {
            return new BarDealData(in);
        }

        public BarDealData[] newArray(int size) {
            return new BarDealData[size];
        }
    };

}