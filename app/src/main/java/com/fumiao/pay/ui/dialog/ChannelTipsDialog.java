package com.fumiao.pay.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.fumiao.core.ui.CoreDialog;
import com.fumiao.pay.R;

public class ChannelTipsDialog extends CoreDialog {

    ImageView close;

    public ChannelTipsDialog(Context context) {
        super(context);
        setContentView(R.layout.dialog_channel_tips);
        close = findViewById(R.id.btn_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
