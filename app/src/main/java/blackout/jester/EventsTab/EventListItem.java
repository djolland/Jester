package blackout.jester.EventsTab;

import android.os.Parcel;
import android.os.Parcelable;

import blackout.jester.BarData.BarData;
import blackout.jester.BarData.BarEventData;

/**
 * Encapsulates all data needed to display an Event in any of the available lists.
 *
 * This class is meant to be used with an ArrayAdapter.
 */

public class EventListItem implements Parcelable {

    private BarData barData;
    private String barImage;
    private String barName;
    private String eventTabText;
    private String profileEventText;

    public EventListItem(BarData barData, int eventPosition){
        this.barData = barData;
        this.barImage = barData.getBarListImage();
        this.barName = barData.getBarName();

        BarEventData event = this.barData.getEvent(eventPosition);
        // Constructing the deal text that will appear in the main Deal Tab List
        this.eventTabText = event.getDescription() + " - " + event.getTime() +
                "\nCover: $" + event.getCoverCharge().toString();
        // Constructing the deal text that will appear in the Deal Tab list in the
        // Bar Profile page.
        this.profileEventText = event.getDescription() + " - $" + event.getCoverCharge().toString();
    }

    // Getters
    public BarData getBarData(){return barData;};
    public String getBarImage(){return barImage;}
    public String getBarName(){return barName;}
    public String getEventTabText(){return eventTabText;}
    public String getProfileEventText(){return profileEventText;}

    // Parceling data
    private EventListItem(Parcel in) {
        String[] data = new String[4];

        in.readParcelable(this.barData.getClass().getClassLoader());
        in.readStringArray(data);
        this.barImage = data[0];
        this.barName = data[1];
        this.eventTabText = data[2];
        this.profileEventText = data[3];
    }

    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(this.barData, flags);
        out.writeStringArray(new String[] {
                this.barImage,
                this.barName,
                this.eventTabText,
                this.profileEventText});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public EventListItem createFromParcel(Parcel in) {
            return new EventListItem(in);
        }

        public EventListItem[] newArray(int size) {
            return new EventListItem[size];
        }
    };


}
