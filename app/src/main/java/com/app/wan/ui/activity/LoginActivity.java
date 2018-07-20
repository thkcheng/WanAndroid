package com.app.wan.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.wan.R;
import com.app.wan.api.Apis;
import com.app.wan.base.BaseActivity;
import com.app.wan.http.HttpManager;
import com.app.wan.http.callback.StringCallback;
import com.app.wan.http.error.ErrorModel;
import com.app.wan.manager.ImageManager;
import com.app.wan.model.UserBean;
import com.app.wan.util.CommonAnimator;
import com.app.wan.util.ToastUtil;

import java.util.LinkedHashMap;

import butterknife.BindView;

/**
 * Created by thkcheng on 2018/7/20.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.imgHead)
    ImageView imgHead;

    @BindView(R.id.loginLayout)
    LinearLayout mLoginLayout;

    @BindView(R.id.etUserName)
    EditText etUserName;

    @BindView(R.id.etUserPwd)
    EditText etUserPwd;

    @BindView(R.id.btnLogin)
    Button btnLogin;

    @Override
    public int getLayoutResID() {
        return R.layout.activity_login_layout;
    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUserName.getText().toString().trim().equals("")) {
                    ToastUtil.showToast("请输入用户名");
                    return;
                }
                if (etUserPwd.getText().toString().trim().equals("")) {
                    ToastUtil.showToast("请输入密码");
                    return;
                }
                login();
            }
        });
    }

    private void login() {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("username", etUserName.getText().toString().trim());
        params.put("password", etUserPwd.getText().toString().trim());
        HttpManager.post()
                .url(Apis.WAN_LOGIN)
                .params(params)
                .build()
                .enqueue(new StringCallback<UserBean>() {
                    @Override
                    public void onSuccess(UserBean response, Object... obj) {
                        if (response.getData().getId() > 0) {
                            ToastUtil.showToast("登录成功");
                            changeUI();
                        }
                    }

                    @Override
                    public void onFailure(ErrorModel errorModel) {

                    }
                });
    }

    private void changeUI() {
        //1、更换头像
        ImageManager.loadImage(imgHead, R.mipmap.icon_head);
        //2、隐藏登录框
        new CommonAnimator.Builder(mLoginLayout)
                .alphaValues(1, 0)
                .duration(1000)
                .listener(new CommonAnimator.Listener() {
                    @Override
                    public void onAnimationEnd() {
                        mLoginLayout.setVisibility(View.GONE);
                    }
                })
                .build()
                .foldWithAnimatorSet();
        //3、显示退出登录

        //4、保存登录数据
    }
}
