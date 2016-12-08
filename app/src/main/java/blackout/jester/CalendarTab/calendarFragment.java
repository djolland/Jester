package blackout.jester.CalendarTab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RelativeLayout;

import blackout.jester.R;

public class calendarFragment extends Fragment  {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.calendar_layout, container, false);

        final CalendarView appCalendar = (CalendarView) rootView.findViewById(R.id.calendarView);
        final Button specialBTN = (Button) rootView.findViewById(R.id.specialButton);
        final Button eventBTN = (Button) rootView.findViewById(R.id.eventButton);
        final Button cancelBTN = (Button) rootView.findViewById(R.id.cancelButton);
        final RelativeLayout popUp = (RelativeLayout) rootView.findViewById(R.id.popUp);

        appCalendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                popUp.setVisibility(View.VISIBLE);
            }
        });

        specialBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                //use to display new add calendar item view
            }
        });

        eventBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                //use to display new add calendar item view
            }
        });

        cancelBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                popUp.setVisibility(View.INVISIBLE);
            }
        });
        return rootView;
    }
}
