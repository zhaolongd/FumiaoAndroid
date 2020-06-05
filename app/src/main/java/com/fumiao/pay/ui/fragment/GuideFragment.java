package com.fumiao.pay.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.fumiao.pay.R;
import com.fumiao.pay.ui.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import static android.content.Context.MODE_PRIVATE;


/**
 * @author XieBoss
 * @version 1.0
 * @date 2019/8/18 16:13
 */
public class GuideFragment extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.btn_guide)
    Button btn_guide;
    @BindView(R.id.iv_splash)
    ImageView ivSplash;
    private int[] bgRes = {R.mipmap.ic_guide_one,R.mipmap.ic_guide_two,R.mipmap.ic_guide_three};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_content,null);
        unbinder = ButterKnife.bind(this, view);
        int index = getArguments().getInt("index");
        ivSplash.setImageResource(bgRes[index]);
        btn_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainActivity.class));
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("is_first_in_data", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isFirstIn", false);
                editor.commit();
                getActivity().finish();
            }
        });
        btn_guide.setVisibility(index == 2 ? View.VISIBLE : View.GONE);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
