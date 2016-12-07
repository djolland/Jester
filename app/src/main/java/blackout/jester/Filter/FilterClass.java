package blackout.jester.Filter;

import java.util.ArrayList;

import blackout.jester.BarData.BarData;
import blackout.jester.BarData.DealType;
import blackout.jester.BarData.EventType;
import blackout.jester.DealsTab.DealListItem;
import blackout.jester.EventsTab.EventListItem;

/**
 * Created by Russell on 12/5/2016.
 */

public class FilterClass {

    public static ArrayList<DealListItem> filterByDeal(ArrayList<DealListItem> currentDealList, ArrayList<DealType> filterByEnum){
        ArrayList<DealListItem> filteredDealList = new ArrayList<>();
        for(DealListItem item: currentDealList){
            if (filterByEnum.contains(item.getDealType())){
                filteredDealList.add(item);
            }
        }
        return filteredDealList;
    }

    public static ArrayList<EventListItem> filterByEvent(ArrayList<EventListItem> currentEventList, ArrayList<EventType> filterByEnum){
        ArrayList<EventListItem> filteredEventList = new ArrayList<>();
        for(EventListItem item: currentEventList){
            if (filterByEnum.contains(item.getEventType())){
                filteredEventList.add(item);
            }
        }
        return filteredEventList;
    }
}
