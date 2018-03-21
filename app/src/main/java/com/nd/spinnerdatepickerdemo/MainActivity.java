package com.nd.spinnerdatepickerdemo;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TextView dateTextView;
    Button dateButton;

    SimpleDateFormat simpleDateFormat;
    int mSelectedDay, mSelectedMonth, mSelectedYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateButton = (Button) findViewById(R.id.set_date_button);
        dateTextView = (TextView) findViewById(R.id.date_textview);
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        Calendar mCalendar = Calendar.getInstance();
        String strDate = simpleDateFormat.format(mCalendar.getTime());
        Log.e("strDate", strDate);

        String mStrDateArray[] = strDate.split("-");
        mSelectedDay = Integer.parseInt(mStrDateArray[0]);
        mSelectedMonth = Integer.parseInt(mStrDateArray[1]) - 1;
        mSelectedYear = Integer.parseInt(mStrDateArray[2]);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // showDate(1980, 0, 1, R.style.DatePickerSpinner);

                showDate(mSelectedYear, mSelectedMonth, mSelectedDay, R.style.DatePickerSpinner);
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        Log.e("calendar", String.valueOf(calendar));

        dateTextView.setText(simpleDateFormat.format(calendar.getTime()));

        mSelectedDay = dayOfMonth;
        mSelectedMonth = monthOfYear;
        mSelectedYear = year;

        Log.e("mSelectedMonth", String.valueOf(mSelectedMonth));
    }


    @VisibleForTesting
    void showDate(int year, int monthOfYear, int dayOfMonth, int spinnerTheme) {
        new SpinnerDatePickerDialogBuilder()
                .context(MainActivity.this)
                .callback(MainActivity.this)
                .spinnerTheme(spinnerTheme)
                .defaultDate(year, monthOfYear, dayOfMonth)
                .maxDate(year, monthOfYear, dayOfMonth)
                .build()
                .show();
    }
}
