package blackout.jester.Filter;

import java.util.ArrayList;

import blackout.jester.BarData.EventType;
import blackout.jester.DealsTab.DealListItem;
import blackout.jester.EventsTab.EventListItem;

/**
 * Created by Russell on 12/5/2016.
 */

public class FilterClass {

    public static ArrayList<DealListItem> filterByDeal(ArrayList<DealListItem> currentDealList,
                                                       ArrayList<FilterListItem> dealFilterList){
        ArrayList<DealListItem> filteredDealList = new ArrayList<>();
        for (DealListItem dealItem: currentDealList){
            for (FilterListItem filterItem: dealFilterList){
                if (filterItem.getFilterType().equals(dealItem.getDealType()) && filterItem.getIsChecked()){
                    filteredDealList.add(dealItem);
                }
            }
        }
        return filteredDealList;
    }

    public static ArrayList<EventListItem> filterByEvent(ArrayList<EventListItem> currentEventList,
                                                         ArrayList<FilterListItem> eventFilterList){
        ArrayList<EventListItem> filteredEventList = new ArrayList<>();
        for (EventListItem eventItem: currentEventList){
            for (FilterListItem filterItem: eventFilterList){
                if (filterItem.getFilterType().equals(eventItem.getEventType()) && filterItem.getIsChecked()){
                    filteredEventList.add(eventItem);
                }
            }
        }
        return filteredEventList;
    }
}
