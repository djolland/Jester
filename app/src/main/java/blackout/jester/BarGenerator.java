package blackout.jester;

import java.math.BigDecimal;
import java.util.ArrayList;

import blackout.jester.BarData.BarData;
import blackout.jester.BarData.DealType;
import blackout.jester.BarData.EventType;

/**
 * Created by djolland on 12/8/2016.
 */

public class BarGenerator {

    public static ArrayList<BarData> generate(){
        ArrayList<BarData> barList = new ArrayList<>();

        /** Add New Bars in this section.
         *      - Be sure to add new bars to the barList at the end of this section.
         *      - To add an image asset copy it in windows file explorer, then right click on
         *        the res/drawable directory in the project view and paste. (yes it's that easy).
         *          * Make sure the image is in the drawable directory!
         *          * When instantiating a new bar you will reference the image by its file name
         *            WITHOUT its file extension.
         *          * Keep in mind that each bar will have 2 images, one that will appear on the
         *            deal and events lists (this one is small and square) and another that will
         *            appear in the bar profile view (this one can be larger and more detailed).
         **/

        // Social House //
        BarData barSocialHouse =
                new BarData("Social House",         // Bar Name
                        "social_house_list",    // Bar Image to appear in lists (small)
                        "social_house_profile"  // Bar Image to appear on profile (big)
                );
        // * Adding Info
        barSocialHouse.setDisatnceMiles(5);
        barSocialHouse.setAddress("2208 College St., Cedar Falls, IA");
        barSocialHouse.setHours("Monday - Saturday: 4PM - 2AM, Sunday: Closed");
        barSocialHouse.setContactInfo("(319) 266-3662");
        // * Adding Deals
        barSocialHouse.addDeal("2 for 1 Mixed Drinks", new BigDecimal(4.00), DealType.MIXEDDRINK, "Today");
        barSocialHouse.addDeal("Domestic Beers", new BigDecimal(3.00), DealType.BEER, "Today");
        // * Adding Events
        barSocialHouse.addEvent("DJ Sumptin", "8:00PM", "Today", EventType.LIVEMUSIC, new BigDecimal(0.00));
        barSocialHouse.addEvent("Lady Googa", "7:00PM", "Tomorrow", EventType.LIVEMUSIC, new BigDecimal(10.00));

        barSocialHouse.setAsFavorite(); // Testing favorites.


        // Blank Bar //
        BarData barBlankBar = new BarData("Blank Bar");
        // * Adding Deals
        barBlankBar.addDeal("Free Beer!", new BigDecimal(0.00), DealType.BEER, "Today");

        barBlankBar.addEvent("Karaoke", "12:00am-1:00pm", "25th Dec.", EventType.KARAOKE, new BigDecimal(10.00));

        // Pump House //
        BarData barPumpHaus = new BarData("Pump Haus", "pump_haus_logo", "pump_haus_profile");
        barPumpHaus.setDisatnceMiles(2);
        barPumpHaus.setAddress("311 Main St, Cedar Falls, IA");
        barPumpHaus.setHours("Sunday - Saturday: 11AM - 2AM");
        barPumpHaus.setContactInfo("(319) 277 -8111");

        barPumpHaus.addDeal("Peppermint Patty Shots", new BigDecimal(2.00), DealType.SHOTS, "Today");
        barPumpHaus.addDeal("Domestic Drafts", new BigDecimal(1.50), DealType.BEER, "Today");

        barPumpHaus.addEvent("Daniel Tosh Stand up", "8:00pm-8:30pm", "19th Dec.", EventType.COMEDY, new BigDecimal(15.00));


        // Little Bigs //
        BarData barLittleBigs = new BarData("Little Bigs", "little_bigs_logo", "paddys_pub_door");
        barLittleBigs.setDisatnceMiles(1);
        barLittleBigs.setAddress("2210 College St, Cedar Falls, IA");
        barLittleBigs.setHours("Sunday - Saturday: 11AM - 2AM");
        barLittleBigs.setContactInfo("(319) 277 -8111");

        barLittleBigs.addDeal("The Little Big", new BigDecimal(3.00), DealType.MIXEDDRINK, "Today");
        barLittleBigs.addDeal("Import Bottles", new BigDecimal(1.50), DealType.BEER, "Today");

        barLittleBigs.addEvent("Trivia Night", "8:00pm-10:00pm", "21th Dec.", EventType.OTHER, new BigDecimal(0.00));


        // Octopus //
        BarData barOctopus = new BarData("Octopus", "octopus_logo", "octopus_profile");
        barOctopus.setDisatnceMiles(1);
        barOctopus.setAddress("2304 College St, Cedar Falls, IA");
        barOctopus.setHours("Sunday - Saturday: 11AM - 2AM");
        barOctopus.setContactInfo("(319) 273 -6228");

        barOctopus.addDeal("Free Popcorn", new BigDecimal(0.00), DealType.OTHER, "Today");
        barOctopus.addDeal("PBR Drafts", new BigDecimal(1.00), DealType.BEER, "Today");

        barOctopus.addEvent("Stand Up Comedy", "8:00pm-11:00pm", "29th Dec.", EventType.COMEDY, new BigDecimal(0.00));


        // Single Speed //
        BarData barSingleSpeed =
                new BarData("Single Speed",
                            "singlespeed_logo",
                            "singlespeed_profile"
                );
        // * Adding Deals
        barSingleSpeed.addDeal(
                "Craft Beer Flights", new BigDecimal(5.00), DealType.BEER, "Saturday");
        barSingleSpeed.addDeal(
                "Seasonal Craft Beer", new BigDecimal(6.50), DealType.BEER, "Friday");


        // Toads Bar and Grill //
        BarData barToads =
                new BarData("Toads",
                            "toads_logo",
                            "toads_profile"
                );
        // * Adding Deals
        barToads.addDeal(
                "Whiskey Shots", new BigDecimal(3.00), DealType.MIXEDDRINK, "Saturday");

        // Screaming Eagle //
        BarData barScreamingEagle =
                new BarData("Sscreaming Eagle",
                            "screaming_eagle_logo",
                            "screaming_eagle_profile"
                );
        // * Adding Deals
        barScreamingEagle.addDeal(
                "Whiskey Shots", new BigDecimal(3.00), DealType.MIXEDDRINK, "Saturday");


        // !!! Add your bars to the barList !!! //
        barList.add(barSocialHouse);
        barList.add(barBlankBar);
        barList.add(barPumpHaus);
        barList.add(barLittleBigs);
        barList.add(barOctopus);
        barList.add(barSingleSpeed);
        barList.add(barToads);
        barList.add(barScreamingEagle);

        return barList;
    }

}
