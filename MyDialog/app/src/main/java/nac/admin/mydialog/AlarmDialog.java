package nac.admin.mydialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TimePicker;

/**
 * Created by Admin on 11/03/2017.
 */
public class AlarmDialog extends Dialog implements View.OnClickListener {
    private int mHour, mMinute;
    private TimePicker mPicker;
    private Button btOK, btCancel;
    private boolean is24Hour;

    public AlarmDialog(Context context) {
        super(context, R.style.dialog_theme);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.time_alarm);
        initViews();

        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initViews() {
        mPicker = (TimePicker)findViewById(R.id.tp_clock);
        mPicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mHour = hourOfDay;
                mMinute = minute;
                is24Hour = view.is24HourView();
            }
        });
        btOK = (Button)findViewById(R.id.bt_ok);
        btCancel = (Button)findViewById(R.id.bt_cancel);

        btOK.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_ok:
                setAlarm();
                dismiss();
                break;

            case R.id.bt_cancel:
                hide();
                break;
        }
    }

    private void setAlarm() {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_HOUR, mHour);
        intent.putExtra(AlarmClock.EXTRA_IS_PM, is24Hour);
        intent.putExtra(AlarmClock.EXTRA_MINUTES, mMinute);
        getContext().startActivity(intent);
    }
}
