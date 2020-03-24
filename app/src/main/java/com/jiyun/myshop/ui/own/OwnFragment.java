package com.jiyun.myshop.ui.own;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.myshop.R;
import com.jiyun.myshop.ui.login.LoginActivity;
import com.jiyun.myshop.ui.notifications.NotificationsViewModel;
import com.jiyun.myshop.utils.SpUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class OwnFragment extends Fragment {

    private RelativeLayout rl;
    private ImageView iv_tx;
    private TextView tv_name;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_own, container, false);
        rl = root.findViewById(R.id.rl);
        tv_name = root.findViewById(R.id.tv_name);
        iv_tx = root.findViewById(R.id.iv_tx);
        rl = root.findViewById(R.id.rl);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
        initData();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        String  avatar = SpUtils.getInstance().getString("avatar");
        String  nickname = SpUtils.getInstance().getString("nickname");
        if(!"".equals(avatar)){
            Glide.with(getActivity()).load(avatar).into(iv_tx);
        }
        if(!"".equals(nickname)){
            tv_name.setText(nickname);
        }
    }


}