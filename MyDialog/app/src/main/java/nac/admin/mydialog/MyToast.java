package nac.admin.mydialog;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Admin on 11/03/2017.
 */
public class MyToast extends Toast {
    private Context mContext;
    private int wScreen;
    private int hScreen;
    private ImageView ivFace;
    private TextView tvFace;
    private final Random mRandom = new Random();
    private int imgArr[] = new int[]{
            R.drawable.beauty,
            R.drawable.cry,
            R.drawable.lovely,R.drawable.oh,
            R.drawable.too_sad
    };
    private String textArr[] = new String[]{"Beauty", "Cry", "Lovely", "Oh", "Too sad"};

    public MyToast(Context context) {
        super(context);
        mContext = context;
        initToast();
    }

    private void initToast() {
        setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
        setDuration(LENGTH_SHORT);
        wScreen = mContext.getResources().getDisplayMetrics().widthPixels;
        hScreen = mContext.getResources().getDisplayMetrics().heightPixels;

        View rootView = View.inflate(mContext, R.layout.item_view, null);
        ivFace = (ImageView)rootView.findViewById(R.id.iv_face);
        tvFace = (TextView)rootView.findViewById(R.id.tv_face);
        setView(rootView);
    }

    @Override
    public void show() {
        setGravity(Gravity.LEFT|Gravity.TOP,
                mRandom.nextInt(wScreen), mRandom.nextInt(hScreen));

        int index = mRandom.nextInt(imgArr.length);
        ivFace.setImageResource(imgArr[index]);
        tvFace.setText(textArr[index]);

        super.show();
    }
}
