package com.fumiao.pay.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.fumiao.core.ui.CoreDialog;
import com.fumiao.pay.R;

public class GuideDialog extends CoreDialog{

    private ImageView IvGuideBg;

    private Button btnComfirm;

    public GuideDialog(Context context) {
        super(context);
        setContentView(R.layout.dialog_guide);
        IvGuideBg = findViewById(R.id.iv_guide_bg);
        btnComfirm = findViewById(R.id.btn_comfirm);
    }

    public void show(String btnText, int guideBg, View.OnClickListener btnClick){
        IvGuideBg.setBackgroundResource(guideBg);
        btnComfirm.setText(btnText);
        btnComfirm.setOnClickListener(btnClick);
        super.show();
    }
}
