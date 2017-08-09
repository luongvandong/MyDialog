package nac.admin.mydialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Admin on 11/03/2017.
 */
public class MainAct extends Activity implements View.OnClickListener {
    private Button btShowToast;
    private MyToast myToast;
    private AlertDialog alertDialog;
    private Button btShowAlert;
    private AlarmDialog alarmDialog;
    private Button btShowMyDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
        initAlertDialog();
    }

    private void initAlertDialog() {
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alarm");
        alertDialog.setMessage("Do you want to get up at 4:00 pm?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Noi dung xu ly khi click vao button
                        Toast.makeText(MainAct.this, "You click YES", Toast.LENGTH_SHORT);

                        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                        intent.putExtra(AlarmClock.EXTRA_HOUR, 16);
                        intent.putExtra(AlarmClock.EXTRA_IS_PM, true);
                        intent.putExtra(AlarmClock.EXTRA_MINUTES, 55);
                        startActivity(intent);
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Noi dung xu ly khi click vao button
                        Toast.makeText(MainAct.this, "You click NO", Toast.LENGTH_SHORT).show();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CANCEL",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Noi dung xu ly khi click vao button
                        Toast.makeText(MainAct.this, "You click CANCEL", Toast.LENGTH_SHORT).show();
                    }
                });
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);

        alarmDialog = new AlarmDialog(this);
    }

    private void initView() {
        btShowToast = (Button) findViewById(R.id.bt_show_toast);
        btShowAlert = (Button) findViewById(R.id.bt_show_alert);
        btShowMyDialog = (Button) findViewById(R.id.bt_show_my_dialog);
        btShowToast.setOnClickListener(this);
        btShowAlert.setOnClickListener(this);
        btShowMyDialog.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_show_alert:
                if(alertDialog.isShowing())return;

                alertDialog.show();
                break;
            case R.id.bt_show_toast:
                myToast = new MyToast(this);
                myToast.show();
                break;
            case R.id.bt_show_my_dialog:
                alarmDialog.show();
                break;
            default:
                break;
        }
    }
}
