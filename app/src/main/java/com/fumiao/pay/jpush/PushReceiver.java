package com.fumiao.pay.jpush;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.fumiao.core.app.CoreApp;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.pay.ui.activity.MainActivity;
import com.fumiao.pay.ui.activity.home.ReconciliationsActivity;
import com.yzy.voice.VoicePlay;
import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

import static com.fumiao.pay.config.KeyConfig.VOICE_SWITCH;

public class PushReceiver extends JPushMessageReceiver {

    public static final String PUSH_TAG = "PUSH_TAG";

    @Override
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onTagOperatorResult(context, jPushMessage);
    }
    @Override
    public void onCheckTagOperatorResult(Context context,JPushMessage jPushMessage){
        super.onCheckTagOperatorResult(context, jPushMessage);
    }
    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onAliasOperatorResult(context, jPushMessage);
    }

    @Override
    public void onMobileNumberOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onMobileNumberOperatorResult(context, jPushMessage);
    }


    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        super.onMessage(context, customMessage);
        Log.e(PUSH_TAG, "onMessage: " + customMessage.toString());
        if (SPUtil.getInstance().getBoolean(CoreApp.getSingle(), VOICE_SWITCH, true)) {
            Log.e(PUSH_TAG, "语音开启");
            try {
                JSONObject extraJson = new JSONObject(customMessage.extra);
                String params = extraJson.getString("params");
                JSONObject paramsJson = new JSONObject(params);
                int pay_type = paramsJson.getInt("pay_type"); //1支付成功 2顾客取消支付
                if(pay_type == 2){
                    VoicePlay.with(context).playCancel();
                }else {
                    String amount = paramsJson.getString("money");
                    VoicePlay.with(context).play(amount);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(PUSH_TAG, "语音关闭");
        }
    }

    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageOpened(context, notificationMessage);
        Log.e(PUSH_TAG, "onNotifyMessageOpened: " + notificationMessage.toString());
        Intent[] intents = new Intent[2];
        Intent intent_main = new Intent(context, MainActivity.class);
        intent_main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Intent intent_target = new Intent(context, ReconciliationsActivity.class);
        intent_target.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intents[0] = intent_main;
        intents[1] = intent_target;
        context.startActivities(intents);
    }

    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageArrived(context, notificationMessage);
        Log.e(PUSH_TAG, "onNotifyMessageArrived: " + notificationMessage.toString());
    }
}
