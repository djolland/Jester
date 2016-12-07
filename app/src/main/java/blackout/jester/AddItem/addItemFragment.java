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

    public View onCreateView(Bundle savedInstanceState){
        View rootView = R.layout.owner_add_item;
        final int inactiveColor = android.R.color.darker_gray;
        final int activeColor = android.R.color.black;

        Calendar localCal = Calendar.getInstance();

        final int year = localCal.get(Calendar.YEAR);
        final int day = localCal.get(Calendar.DAY_OF_MONTH);
        final int month = localCal.get(Calendar.MONTH);

        final RelativeLayout reoccurBox = (RelativeLayout) findViewById(R.id.reoccurBox);
        final CheckBox reoccuringCB = (CheckBox) findViewById(R.id.reoccurCheckBox);
        final CheckBox allDayCB = (CheckBox) findViewById(R.id.allDayCheckBox);
        final CheckBox weeklyCB = (CheckBox) findViewById(R.id.weeklyCheckBox);
        final CheckBox monthlyCB = (CheckBox) findViewById(R.id.monthlyCheckBox);
        final CheckBox mondayCB = (CheckBox) findViewById(R.id.mondayCheckBox);
        final CheckBox tuesdayCB = (CheckBox) findViewById(R.id.tuesdayCheckBox);
        final CheckBox wednesdayCB = (CheckBox) findViewById(R.id.wednesdayCheckBox);
        final CheckBox thursdayCB = (CheckBox) findViewById(R.id.thursdayCheckBox);
        final CheckBox fridayCB = (CheckBox) findViewById(R.id.fridayCheckBox);
        final CheckBox saturdayCB = (CheckBox) findViewById(R.id.saturdayCheckBox);
        final CheckBox sundayCB = (CheckBox) findViewById(R.id.sundayCheckBox);
        final TextView selectDate = (TextView) findViewById(R.id.eventDateSelect);
        final TextView startDate = (TextView) findViewById(R.id.startDateSelect);
        final TextView endDate = (TextView) findViewById(R.id.endDateSelect);
        final TextView startTime = (TextView) findViewById(R.id.startTimeSelect);
        final TextView endTime = (TextView) findViewByeId(R.id.endTimeSelect);

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
