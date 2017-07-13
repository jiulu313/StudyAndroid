package com.zhj.sa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhj.sa.listener.OnMultiTouchListener;
import com.zhj.sa.utils.ToastUtil;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    public String TAG = getClass().getSimpleName();

    ImageView tvImage;
    Button btnDouble;
    Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvImage = (ImageView) findViewById(R.id.tv_image);
        btnDouble = (Button) findViewById(R.id.btn_double);
        btnTest = (Button) findViewById(R.id.btn_test);

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTest();
            }
        });

        btnDouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDouble();
            }
        });
    }

    void showShortMsg(String ...args){
        ToastUtil.showShortMsg(this,args);
    }

    private void onDouble() {
        showShortMsg("double",",good",",click");
    }

    private void onTest() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("hello");
                emitter.onNext("world");
                emitter.onComplete();
                emitter.onNext("THIS IS ");
            }
        });

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG,"onSubscribe ...");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d(TAG,"s="+s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"e="+e.toString());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };

        observable.subscribe(observer);


//        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                // 发送一个 Hello World 事件
//                subscriber.onNext("Hello World!");
//
//                // 事件发送完成
//                subscriber.onCompleted();
//            }
//        });
    }
}
