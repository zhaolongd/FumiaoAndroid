package com.fumiao.pay.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.fumiao.pay.R;

public class ClearEditText extends LinearLayout {

    private ImageView leftImage;
    private EditText textEdit;
    private ImageView clear;
    private ViewGroup root;

    public ClearEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ClearEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClearEditText);
        String hint = typedArray.getString(R.styleable.ClearEditText_hint);
        String text = typedArray.getString(R.styleable.ClearEditText_text);
        boolean is_password = typedArray.getBoolean(R.styleable.ClearEditText_is_password, false);//是否為密碼
        boolean ed_focusable = typedArray.getBoolean(R.styleable.ClearEditText_ed_focusable, true);
        boolean is_number = typedArray.getBoolean(R.styleable.ClearEditText_is_number, false);
        final boolean is_clear = typedArray.getBoolean(R.styleable.ClearEditText_is_clear, false); //是否显示清除按钮
        int leftImageSrc = typedArray.getResourceId(R.styleable.ClearEditText_image_src, 0);
        root = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.widget_clear_edit, null);

        textEdit = root.findViewById(com.fumiao.pay.R.id.edit_text);
        leftImage = root.findViewById(com.fumiao.pay.R.id.left_image);
        clear = root.findViewById(com.fumiao.pay.R.id.clear);
        clear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                clear.setVisibility(View.GONE);
                textEdit.setText("");
            }
        });

        //给左边图标设置背景
        if(leftImageSrc != 0){
            leftImage.setBackgroundResource(leftImageSrc);
        }
        textEdit.setFocusable(ed_focusable);

        if (is_password) {
            textEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        if (is_number) {
            textEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
        textEdit.setHint(hint);
        textEdit.setText(text);

        textEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(is_clear){
                    if(isEmpty()){
                        clear.setVisibility(View.GONE);
                    }else {
                        clear.setVisibility(View.VISIBLE);
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        final ViewGroup.LayoutParams VG_LP_MW
                = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        addView(root, VG_LP_MW);
    }



    public void setText(String text) {
        textEdit.setText(text);
    }

    public String getText() {
        return textEdit.getText().toString().trim();
    }

    public EditText getEditText() {
        return textEdit;
    }

    public void setHint(String hint) {
        textEdit.setHint(hint);
    }

    public String getHint() {
        return textEdit.getHint().toString();
    }

    public void addTextChangedListener(ClearEditText.EditTextWatcher watcher) {
        textEdit.addTextChangedListener(watcher);
    }

    public static class EditTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    public boolean isEmpty() {
        return textEdit.getText().toString().trim().equals("");
    }

}
