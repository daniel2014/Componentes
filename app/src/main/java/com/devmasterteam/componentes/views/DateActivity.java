package com.devmasterteam.componentes.views;

import android.app.DatePickerDialog;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.devmasterteam.componentes.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        this.mViewHolder.mButtonDate = (Button) this.findViewById(R.id.button_date);
        this.mViewHolder.mButtonGetTime = (Button) this.findViewById(R.id.button_get_time);
        this.mViewHolder.mButtonSetTime = (Button) this.findViewById(R.id.button_set_time);
        this.mViewHolder.mTimePicker = (TimePicker) this.findViewById(R.id.time_picker);

        // Eventos
        this.setListeners();

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.button_date) {
            this.showTimePickerDialog();
        } else if (id == R.id.button_get_time) {

            if (Build.VERSION.SDK_INT >= 23) {

                String hour = String.valueOf(this.mViewHolder.mTimePicker.getHour());
                String minute = String.valueOf(this.mViewHolder.mTimePicker.getMinute());

                String value = hour + ":" + minute;
                Toast.makeText(this, value, Toast.LENGTH_LONG).show();

            } else {

                String hour = String.valueOf(this.mViewHolder.mTimePicker.getCurrentHour());
                String minute = String.valueOf(this.mViewHolder.mTimePicker.getCurrentMinute());

                String value = hour + ":" + minute;
                Toast.makeText(this, value, Toast.LENGTH_LONG).show();

            }

        } else if (id == R.id.button_set_time) {

            if (Build.VERSION.SDK_INT >= 23) {

                this.mViewHolder.mTimePicker.setHour(20);
                this.mViewHolder.mTimePicker.setMinute(15);

            } else {

                this.mViewHolder.mTimePicker.setCurrentHour(20);
                this.mViewHolder.mTimePicker.setCurrentMinute(15);

            }

        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);

        String date = SIMPLE_DATE_FORMAT.format(calendar.getTime());
        this.mViewHolder.mButtonDate.setText(date);

    }

    /**
     * Mostra seleção de data
     */
    public void showTimePickerDialog() {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(this, this, year, month, day).show();

    }

    /**
     * Atribui eventos aos elementos
     */
    private void setListeners() {
        this.mViewHolder.mButtonDate.setOnClickListener(this);
        this.mViewHolder.mButtonGetTime.setOnClickListener(this);
        this.mViewHolder.mButtonSetTime.setOnClickListener(this);
    }

    /**
     * ViewHolder
     */
    private static class ViewHolder {
        private Button mButtonDate;
        private Button mButtonGetTime;
        private Button mButtonSetTime;
        private TimePicker mTimePicker;
    }
}
