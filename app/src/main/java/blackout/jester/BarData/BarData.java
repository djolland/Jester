package blackout.jester.BarData;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.ArrayList;

import blackout.jester.DealsTab.DealListItem;
import blackout.jester.EventsTab.EventListItem;

/**
 * Contains all data for a given bar.
 */

public class BarData implements Parcelable {

    private String barName;
    private String barListImage;
    private String barProfileImage;
    private Boolean favorite;
    private BarInfoData info;
    private ArrayList<BarDealData> deals;
    private ArrayList<BarEventData> events;

    public BarData(String barName, String barListImage, String barProfileImage,
                   ArrayList<BarDealData> deals, ArrayList<BarEventData> events){
        this.barName = barName;
        this.barListImage = barListImage;
        this.barProfileImage = barProfileImage;
        this.info = new BarInfoData();
        this.deals = deals;
        this.events = events;
        this.favorite = false;
    }

    public BarData(String barName, String barListImage, String barProfileImage){
        this.barName = barName;
        this.barListImage = barListImage;
        this.barProfileImage = barProfileImage;
        this.info = new BarInfoData();
        this.deals = new ArrayList<>();
        this.events = new ArrayList<>();
        this.favorite = false;
    }

    public BarData(String barName){
        this.barName = barName;
        this.barListImage = "no_bar_list_image";
        this.barProfileImage = "no_bar_list_image";
        this.info = new BarInfoData();
        this.deals = new ArrayList<>();
        this.events = new ArrayList<>();
        this.favorite = false;
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

    public BarInfoData getInfo(){return info;}

    public String getAddress(){return info.getAddress();}

    public String getHours(){return info.getHours();}

    public String getContactInfo(){return info.getContact();}

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

    public Boolean isFavorite(){
        return favorite;
    }

    //Setters

    public void setBarListImage(String barListImage){
        this.barListImage = barListImage;
    }

    public void setBarProfileImage(String barProfileImage){
        this.barProfileImage = barProfileImage;
    }

    public void setBarName(String barName){this.barName = barName;}

    public void setAddress(String address){
        this.info.setAddress(address);
    }

    public void setHours(String hours){
        this.info.setHours(hours);
    }

    public void setContactInfo(String contactInfo){
        this.info.setContact(contactInfo);
    }

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

    public void addEvent (String description, String time, String date, BigDecimal coverCharge){
        events.add(new BarEventData(description, time, date, coverCharge));
    }

    public void addEvent (BarEventData eventData){events.add(eventData);}

    public void addEvents (ArrayList<BarEventData> eventDatas){
        for (BarEventData eventData: eventDatas){
            events.add(eventData);
        }
    }

    public void addToFavorites(){
        favorite = true;
    }

    public void removeFromFavorites(){
        favorite = false;
    }

    /**
     * Generates an ArrayList of DealListItems from this bars deals.
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

    /**
     * Generates an ArrayList of EventListItems from this bars events.
     *
     * @return ArrayList of DealListItems generated from this bars events.
     */
    public ArrayList<EventListItem> generateEventList(){
        ArrayList<EventListItem> eventList = new ArrayList<>();

        for (int i = 0; i < events.size(); i++){
            eventList.add(new EventListItem(this, i));
        }

        return eventList;
    }

    //Parceling data
    private BarData(Parcel in) {
        boolean[] fav = new boolean[1];
        this.barName = in.readString();
        this.barListImage = in.readString();
        this.barProfileImage = in .readString();
        this.info = in.readParcelable(BarInfoData.class.getClassLoader());
        this.deals = in.readArrayList(BarDealData.class.getClassLoader());
        this.events = in.readArrayList(BarEventData.class.getClassLoader());
        in.readBooleanArray(fav);
        this.favorite = fav[0];
    }

    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        boolean[] fav = new boolean[1];
        fav[0] = this.favorite;
        out.writeString(this.barName);
        out.writeString(this.barListImage);
        out.writeString(this.barProfileImage);
        out.writeParcelable(this.info, flags);
        out.writeList(this.deals);
        out.writeList(this.events);
        out.writeBooleanArray(fav);
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
