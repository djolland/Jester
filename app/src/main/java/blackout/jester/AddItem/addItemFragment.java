package blackout.jester.AddItem;

import android.app.DatePickerDialog;
import android.graphics.Color;
import java.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import blackout.jester.R;

public class addItemFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.owner_add_item, container, false);
        final int inactiveColor = android.R.color.darker_gray;
        final int activeColor = android.R.color.black;

        Calendar localCal = Calendar.getInstance();

        final int year = localCal.get(Calendar.YEAR);
        final int day = localCal.get(Calendar.DAY_OF_MONTH);
        final int month = localCal.get(Calendar.MONTH);

        final RelativeLayout reoccurBox = (RelativeLayout) rootView.findViewById(R.id.reoccurBox);
        final CheckBox reoccuringCB = (CheckBox) rootView.findViewById(R.id.reoccurCheckBox);
        final CheckBox allDayCB = (CheckBox) rootView.findViewById(R.id.allDayCheckBox);
        final CheckBox weeklyCB = (CheckBox) rootView.findViewById(R.id.weeklyCheckBox);
        final CheckBox monthlyCB = (CheckBox) rootView.findViewById(R.id.monthlyCheckBox);
        final CheckBox mondayCB = (CheckBox) rootView.findViewById(R.id.mondayCheckBox);
        final CheckBox tuesdayCB = (CheckBox) rootView.findViewById(R.id.tuesdayCheckBox);
        final CheckBox wednesdayCB = (CheckBox) rootView.findViewById(R.id.wednesdayCheckBox);
        final CheckBox thursdayCB = (CheckBox) rootView.findViewById(R.id.thursdayCheckBox);
        final CheckBox fridayCB = (CheckBox) rootView.findViewById(R.id.fridayCheckBox);
        final CheckBox saturdayCB = (CheckBox) rootView.findViewById(R.id.saturdayCheckBox);
        final CheckBox sundayCB = (CheckBox) rootView.findViewById(R.id.sundayCheckBox);
        final TextView selectDate = (TextView) rootView.findViewById(R.id.eventDateSelect);
        final TextView startDate = (TextView) rootView.findViewById(R.id.startDateSelect);
        final TextView endDate = (TextView) rootView.findViewById(R.id.endDateSelect);
        final TextView startTime = (TextView) rootView.findViewById(R.id.startTimeSelect);
        final TextView endTime = (TextView) rootView.findViewById(R.id.endTimeSelect);

        //Check box listeners
        reoccuringCB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (reoccuringCB.isChecked()) {
                    reoccurBox.setVisibility(View.VISIBLE);
                    selectDate.setClickable(false);
                    selectDate.setTextColor(getResources().getColor(inactiveColor));
                }
                else{
                    reoccurBox.setVisibility(View.INVISIBLE);
                    selectDate.setClickable(true);
                    selectDate.setTextColor(getResources().getColor(activeColor));
                }
            }
        });
        allDayCB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (allDayCB.isChecked()){
                    startTime.setClickable(false);
                    startTime.setTextColor(getResources().getColor(inactiveColor));
                    endTime.setClickable(false);
                    endTime.setTextColor(getResources().getColor(inactiveColor));
                }
                else {
                    startTime.setClickable(true);
                    startTime.setTextColor(getResources().getColor(activeColor));
                    endTime.setClickable(true);
                    endTime.setTextColor(getResources().getColor(activeColor));
                }
            }
        });
        weeklyCB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

            }
        });
        monthlyCB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

            }
        });
        mondayCB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

            }
        });
        tuesdayCB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

            }
        });
        wednesdayCB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

            }
        });
        thursdayCB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

            }
        });
        fridayCB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

            }
        });
        saturdayCB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

            }
        });
        sundayCB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

            }
        });

        //Text input listeners
        selectDate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });

        startDate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });

        endDate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });

        startTime.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });

        endTime.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
        return rootView;
    }
}
