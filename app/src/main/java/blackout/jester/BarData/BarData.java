package blackout.jester.BarData;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.ArrayList;

import blackout.jester.DealsTab.DealListItem;

/**
 * Contains all data for a given bar.
 */

public class BarData implements Parcelable {

    private String barName;
    private String barListImage;
    private String barProfileImage;
    private ArrayList<BarDealData> deals;
    private ArrayList<BarEventData> events;

    public BarData(String barName, String barListImage, String barProfileImage,
                   ArrayList<BarDealData> deals, ArrayList<BarEventData> events){
        this.barName = barName;
        this.barListImage = barListImage;
        this.barProfileImage = barProfileImage;
        this.deals = deals;
        this.events = events;
    }

    public BarData(String barName, String barListImage, String barProfileImage){
        this.barName = barName;
        this.barListImage = barListImage;
        this.barProfileImage = barProfileImage;
        this.deals = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    public BarData(String barName){
        this.barName = barName;
        this.barListImage = "no_bar_list_image";
        this.barProfileImage = "no_bar_list_image";
        this.deals = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    //Getters

    public String getBarName() {
        return barName;
    }

    public String getBarListImage() {
        return barListImage;
    }

    public String getBarProfileImage() {
        return barProfileImage;
    }

    public ArrayList<BarDealData> getDeals() {
        return deals;
    }

    public BarDealData getDeal (int position){
        return deals.get(position);
    }

    public ArrayList<BarEventData> getEvents() {
        return events;
    }

    public BarEventData getEvent(int position){
        return events.get(position);
    }

    //Setters

    public void setBarName(String barName){this.barName = barName;}

    public void addDeal(String description, BigDecimal price, DealType dealType, String date){
        deals.add(new BarDealData(description, price, dealType, date));
    }

    public void addDeal(BarDealData dealData){
        deals.add(dealData);
    }

    public void addDeals (ArrayList<BarDealData> dealDatas){
        for (BarDealData dealData: dealDatas){
            deals.add(dealData);
        }
    }

    public void addEvent (String description){
        events.add(new BarEventData(description));
    }

    public void addEvent (BarEventData eventData){events.add(eventData);}

    public void addEvents (ArrayList<BarEventData> eventDatas){
        for (BarEventData eventData: eventDatas){
            events.add(eventData);
        }
    }

    /**
     * Generates an ArrayList of DealListItems from this bars deals for display in the Deals tab.
     *
     * @return ArrayList of DealListItems generated from this bars deals.
     */
    public ArrayList<DealListItem> generateDealList(){
        ArrayList<DealListItem> dealList = new ArrayList<>();

        for (int i = 0; i < deals.size(); i++){
            dealList.add(new DealListItem(this, i));
        }

        return dealList;
    }

    //Parceling data
    private BarData(Parcel in) {
        this.barName = in.readString();
        this.barListImage = in.readString();
        this.barProfileImage = in .readString();
        this.deals = in.readArrayList(BarDealData.class.getClassLoader()); //in.readParcelable(BarDealData.class.getClassLoader());
        this.events = in.readArrayList(BarEventData.class.getClassLoader());
    }

    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.barName);
        out.writeString(this.barListImage);
        out.writeString(this.barProfileImage);
        out.writeList(this.deals);
        out.writeList(this.events);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public BarData createFromParcel(Parcel in) {
            return new BarData(in);
        }

        public BarData[] newArray(int size) {
            return new BarData[size];
        }
    };

}
