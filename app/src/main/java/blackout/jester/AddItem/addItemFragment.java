package blackout.jester.AddItem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;


import java.util.Calendar;

import blackout.jester.R;



public class addItemFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.owner_add_item, container, false);
        final int inactiveColor = android.R.color.darker_gray;
        final int activeColor = android.R.color.black;

        Calendar localCalendar = Calendar.getInstance();

        final RelativeLayout reoccurBox = (RelativeLayout) rootView.findViewById(R.id.reoccurBox);
        final RelativeLayout datePickBox = (RelativeLayout) rootView.findViewById(R.id.datePickerBox);
        final RelativeLayout timePickBox = (RelativeLayout) rootView.findViewById(R.id.timePickerBox);
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
        final Button cancelBTN = (Button) rootView.findViewById(R.id.saveButton);
        final Button saveBTN = (Button) rootView.findViewById(R.id.cancelButton);
        final Button dateSaveBTN = (Button) rootView.findViewById(R.id.dateSaveButton);
        final Button dateCancelBTN = (Button) rootView.findViewById(R.id.dateCancelButton);
        final Button timeSaveBTN = (Button) rootView.findViewById(R.id.timeSaveButton);
        final Button timeCancelBTN = (Button) rootView.findViewById(R.id.timeCancelButton);
        final DatePicker startDatePicker = (DatePicker) rootView.findViewById(R.id.startDatePicker);
        final DatePicker endDatePicker = (DatePicker) rootView.findViewById(R.id.endDatePicker);
        final TimePicker startTimePicker = (TimePicker) rootView.findViewById(R.id.startTimePicker);
        final TimePicker endTimePicker = (TimePicker) rootView.findViewById(R.id.endTimePicker);

        final boolean[] reoccur = new boolean[1];//Android Studio forced this declaration type

        //Check box listeners
        reoccuringCB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                if (reoccuringCB.isChecked()) {
                    reoccurBox.setVisibility(View.VISIBLE);
                    selectDate.setClickable(false);
                    selectDate.setTextColor(getResources().getColor(inactiveColor));
                    reoccur[0] = true;
                }
                else{
                    reoccurBox.setVisibility(View.INVISIBLE);
                    selectDate.setClickable(true);
                    selectDate.setTextColor(getResources().getColor(activeColor));
                    reoccur[0] = false;
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
                //will be used for setting reoccurance within database
            }
        });
        monthlyCB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //will be use for setting reoccurance within database
            }
        });
        mondayCB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //will be use for setting reoccurance within database
            }
        });
        tuesdayCB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //will be use for setting reoccurance within database
            }
        });
        wednesdayCB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //will be use for setting reoccurance within database
            }
        });
        thursdayCB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //will be use for setting reoccurance within database
            }
        });
        fridayCB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //will be use for setting reoccurance within database
            }
        });
        saturdayCB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //will be use for setting reoccurance within database
            }
        });
        sundayCB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //will be use for setting reoccurance within database
            }
        });

        //Text input listeners
        selectDate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startDatePicker.setVisibility(View.VISIBLE);
                datePickBox.setVisibility(View.VISIBLE);
            }
        });
        startDate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startDatePicker.setVisibility(View.VISIBLE);
                endDatePicker.setVisibility(View.INVISIBLE);
                datePickBox.setVisibility(View.VISIBLE);
            }
        });
        endDate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startDatePicker.setVisibility(View.INVISIBLE);
                endDatePicker.setVisibility(View.VISIBLE);
                datePickBox.setVisibility(View.VISIBLE);
            }
        });
        startTime.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startTimePicker.setVisibility(View.VISIBLE);
                endTimePicker.setVisibility(View.INVISIBLE);
                timePickBox.setVisibility(View.VISIBLE);
            }
        });
        endTime.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startTimePicker.setVisibility(View.INVISIBLE);
                endTimePicker.setVisibility(View.VISIBLE);
                timePickBox.setVisibility(View.VISIBLE);
            }
        });

        //Button listeners
        cancelBTN.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //need to close screen eventually
            }
        });

        saveBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                //will save information to database, currently no
                //database to save to.
            }
        });

        dateCancelBTN.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                datePickBox.setVisibility(View.INVISIBLE);
            }
        });

        dateSaveBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                if (startDatePicker.getVisibility() == View.VISIBLE){
                    if (reoccur[0]){
                        startDate.setText(setDate(startDatePicker));
                    }
                    else{
                        selectDate.setText(setDate(startDatePicker));
                    }
                }
                else if (endDatePicker.getVisibility() == View.VISIBLE){
                    endDate.setText(setDate(endDatePicker));
                }
                else{
                    //shouldn't get here, need to add error handling
                }
                datePickBox.setVisibility(View.INVISIBLE);
            }
        });

        timeCancelBTN.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                timePickBox.setVisibility(View.INVISIBLE);
            }
        });

        timeSaveBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                if (startTimePicker.getVisibility() == View.VISIBLE) {
                    startTime.setText(setTime(startTimePicker));
                }
                else if (endTimePicker.getVisibility() == View.VISIBLE){
                    endTime.setText(setTime(endTimePicker));
                }
                else{
                    //shouldn't reach here, need to add error handling
                }
                timePickBox.setVisibility(View.INVISIBLE);
            }
        });

        return rootView;
    }

    private String setDate(DatePicker date){
        String localDate;
        int localYear = date.getYear();
        int localMonth = date.getMonth() + 1;//zero indexed
        int localDay = date.getDayOfMonth();

        localDate = localMonth + "/" + localDay + "/" + localYear;

        return localDate;
    }

    private String setTime(TimePicker time) {
        String localText;
        String am_pm = "am";
        int localHour = time.getCurrentHour();
        int localMin = time.getCurrentMinute();

        if (localHour > 12){
            am_pm = "pm";
            localHour = localHour - 12;
        }

        localText = localHour + ":" + localMin + " " + am_pm;

        return localText;
    }
}