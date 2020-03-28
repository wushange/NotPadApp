package com.demo.notpadapp.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.demo.notpadapp.MainActivity;
import com.demo.notpadapp.R;
import com.demo.notpadapp.base.BaseActivity;
import com.demo.notpadapp.bean.User;
import com.demo.notpadapp.util.UserUtil;

import org.litepal.LitePal;

/**
 * 登陆页面
 */
public class LoginActivity extends BaseActivity {
    private Button mBtnLogin;
    private EditText mEtLoginPwd;
    private EditText mEtLoginName;
    private TextView mBtnRegist;


    @Override
    public int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mEtLoginPwd = (EditText) findViewById(R.id.et_login_pwd);
        mEtLoginName = (EditText) findViewById(R.id.et_login_name);
        mBtnRegist = (TextView) findViewById(R.id.btn_regist);

        mBtnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtils.startActivity(RegistActivity.class);
            }
        });
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    User user = (User) LitePal.where("name = ?  and password =  ? ",
                            mEtLoginName.getText().toString(),
                            EncryptUtils.encryptMD5ToString(mEtLoginPwd.getText().toString()))
                            .findFirst(User.class);
                    if (user != null) {
                        ToastUtils.showShort("登陆成功！");
                        UserUtil.login(user);
                        ActivityUtils.startActivity(MainActivity.class);
                        finish();
                    } else {
                        ToastUtils.showShort("账号或者密码错误，请重试");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    public void doBusiness(Context mContext) {

    }
}
