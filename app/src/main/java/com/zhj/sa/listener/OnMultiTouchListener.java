package com.zhj.sa.listener;

import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhanghongjun on 2017/3/15.
 */

public abstract class OnMultiTouchListener implements View.OnTouchListener {
    //上次 onTouch 发生的时间
    private long lastTouchTime = 0;
    //已经连续 touch 的次数
    private AtomicInteger touchCount = new AtomicInteger(0);
    private Runnable mRun = null;

    private Context context;
    private Handler uiHandler;

    public OnMultiTouchListener(Context context) {
        this.context = context;
        uiHandler = new Handler(context.getMainLooper());
    }

    public void removeCallback() {
        if (mRun != null) {
            uiHandler.removeCallbacks(mRun);
            mRun = null;
        }
    }

    @Override
    public boolean onTouch(final View v, final MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            final long now = System.currentTimeMillis();
            lastTouchTime = now;
            touchCount.incrementAndGet();
            removeCallback();

            mRun = new Runnable() {
                @Override
                public void run() {
                    if (now == lastTouchTime) {
                        onMultiTouch(v, event, touchCount.get());
                        touchCount.set(0);
                    }
                }
            };

            uiHandler.postDelayed(mRun,getMultiTouchInterval());
        }
        return true;
    }
    protected int getMultiTouchInterval() {
        return 400;
    }
    public abstract void onMultiTouch(View v, MotionEvent event, int touchCount);


}
