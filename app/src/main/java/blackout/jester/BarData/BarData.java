package blackout.jester.BarData;

import java.util.ArrayList;

/**
 * Contains all data for a given bar.
 */

public class BarData {

    private String barName;
    private String barListImage;
    private String barProfileImage;
    private ArrayList<BarDealData> deals;
    private String[] events;

    public BarData(String barName, String barListImage, String barProfileImage,
                   ArrayList<BarDealData> deals, String[] events){
        this.barName = barName;
        this.barListImage = barListImage;
        this.barProfileImage = barProfileImage;
        this.deals = deals;
        this.events = events;
    }

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

    public String[] getEvents() {
        return events;
    }

    public void addDeal(BarDealData dealData){
        deals.add(dealData);
    }
}
