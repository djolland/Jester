package blackout.jester.EventsTab;

import android.os.Parcel;
import android.os.Parcelable;

import blackout.jester.BarData.BarData;
import blackout.jester.BarData.BarEventData;
import blackout.jester.BarData.DealType;
import blackout.jester.BarData.EventType;

/**
 * Encapsulates all data needed to display an Event in any of the available lists.
 *
 * This class is meant to be used with an ArrayAdapter.
 */

public class EventListItem implements Parcelable, Comparable<EventListItem> {

    private BarData barData;
    private String barImage;
    private String barName;
    private String eventTabText;
    private String profileEventText;
    private EventType eventType;
    private BarEventData eventData;

    public EventListItem(BarData barData, int eventPosition){
        this.barData = barData;
        this.barImage = barData.getBarListImage();
        this.barName = barData.getBarName();
        this.eventData = barData.getEvent(eventPosition);
        this.eventType = this.eventData.getEventType();

        // Constructing the deal text that will appear in the main Deal Tab List
        this.eventTabText = this.eventData.getDescription() + " - " + this.eventData.getTime() +
                "\nCover: $" + this.eventData.getCoverCharge();
        // Constructing the deal text that will appear in the Deal Tab list in the
        // Bar Profile page.
        this.profileEventText = this.eventData.getDescription() + " - $" + this.eventData.getCoverCharge().toString();
    }

    // Getters
    public BarData getBarData(){return barData;};
    public String getBarImage(){return barImage;}
    public String getBarName(){return barName;}
    public String getEventTabText(){return eventTabText;}
    public String getProfileEventText(){return profileEventText;}
    public EventType getEventType() {return eventType;}
    public BarEventData getEventData(){return eventData;}
    // Parceling data
    private EventListItem(Parcel in) {
        String[] data = new String[4];

        in.readParcelable(this.barData.getClass().getClassLoader());
        in.readStringArray(data);
        this.barImage = data[0];
        this.barName = data[1];
        this.eventTabText = data[2];
        this.profileEventText = data[3];
        this.eventType = EventType.valueOf(in.readString());
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
        out.writeString(eventType.name());
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public EventListItem createFromParcel(Parcel in) {
            return new EventListItem(in);
        }

        public EventListItem[] newArray(int size) {
            return new EventListItem[size];
        }
    };


    @Override
    public int compareTo(EventListItem o) {
        return this.eventData.compareTo(o.getEventData());
    }
}
