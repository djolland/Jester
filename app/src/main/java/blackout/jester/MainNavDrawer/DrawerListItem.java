package blackout.jester.MainNavDrawer;

/**
 * Created by djolland on 12/8/2016.
 */

public class DrawerListItem {

    private String icon;
    private String text;

    public DrawerListItem(String icon, String text){
        this.icon = icon;
        this.text = text;
    }

    // Getters
    public String getIcon(){
        return icon;
    }

    public String getText(){
        return text;
    }
}
