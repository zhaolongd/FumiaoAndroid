package com.fumiao.pay.app;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.fumiao.core.app.CoreFragment;
import com.fumiao.pay.config.HttpConfig;
import com.fumiao.pay.config.KeyConfig;

/**
 * Created by chee on 2018/8/24.
 */
public class BaseFragment extends CoreFragment implements KeyConfig,HttpConfig {

   public View root;

   public void jumpActivity(String activityUrl) {
      if (activityUrl == null || activityUrl.equals("")) {
         Toast.makeText(getActivity(),"功能暂未开放",Toast.LENGTH_SHORT).show();
         return;
      }
      try {
         Class clz = Class.forName(activityUrl);
         startActivity(new Intent(getContext(), clz));
      } catch (ClassNotFoundException e) {
         Toast.makeText(getActivity(),"功能暂未开放",Toast.LENGTH_SHORT).show();
      }
   }

}
