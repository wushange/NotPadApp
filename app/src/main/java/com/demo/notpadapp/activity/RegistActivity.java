package com.demo.notpadapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.demo.notpadapp.R;
import com.demo.notpadapp.base.BaseActivity;
import com.demo.notpadapp.bean.User;
import com.demo.notpadapp.util.UserUtil;

import org.litepal.LitePal;

import java.util.UUID;

/**
 * 注册页面
 */
public class RegistActivity extends BaseActivity {
    public static void callMe(Context context) {
        Intent intent = new Intent(context, RegistActivity.class);
        intent.putExtra("isUpdate", true);
        context.startActivity(intent);
    }

    private EditText mEtRegistPwd;
    private EditText mEtRegistRepwd;
    private EditText mEtRegistName;
    private Button mBtnRegist;


    private boolean isUpdate;

    @Override
    public int bindLayout() {
        return R.layout.activity_regist;
    }

    @Override
    public void initParms(Bundle parms) {
        isUpdate = parms.getBoolean("isUpdate");
    }

    @Override
    public void initView(View view) {
        mEtRegistPwd = (EditText) findViewById(R.id.et_regist_pwd);
        mEtRegistRepwd = (EditText) findViewById(R.id.et_regist_repwd);
        mEtRegistName = (EditText) findViewById(R.id.et_regist_name);
        mBtnRegist = (Button) findViewById(R.id.btn_regist);

        if(isUpdate){
            mEtRegistName.setText(UserUtil.getCurrentUser().getName());
            mEtRegistName.setEnabled(false);
            mEtRegistPwd.setText("");
            mEtRegistRepwd.setText("");
            mBtnRegist.setText("修改密码");
        }
        mBtnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mEtRegistName.getText().toString();
                String password = mEtRegistPwd.getText().toString();
                String repassword = mEtRegistRepwd.getText().toString();

                if (name.isEmpty() || password.isEmpty() || repassword.isEmpty()) {
                    ToastUtils.showShort("用户名密码不允许为空");
                    return;
                }
                if (password.length() < 6 || repassword.length() < 6) {
                    ToastUtils.showShort("密码长度不能小于6位");
                    return;
                }
                if (!password.equals(repassword)) {
                    ToastUtils.showShort("两次输入密码不一致");
                    return;
                }
                if(isUpdate){
                    User u = UserUtil.getCurrentUser();
                    u.setPassword(EncryptUtils.encryptMD5ToString(password));
                    u.saveOrUpdate("uid = ?",u.getUid());
                    ToastUtils.showShort("修改密码成功!请重新登陆");
                    ActivityUtils.finishAllActivities();
                    ActivityUtils.startActivity(LoginActivity.class);
                    return;
                }
                try {
                    if (LitePal.where("name = " + name).find(User.class) == null) {
                        ToastUtils.showShort("用户以注册！");
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                User user = new User();
                user.setUid(UUID.randomUUID().toString());
                user.setName(name);
                user.setPassword(EncryptUtils.encryptMD5ToString(password));
                user.save();
                ToastUtils.showShort("注册成功");
                finish();
            }
        });

    }

    @Override
    public void doBusiness(Context mContext) {

    }
}