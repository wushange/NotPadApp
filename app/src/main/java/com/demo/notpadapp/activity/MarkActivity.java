package com.demo.notpadapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.demo.notpadapp.R;
import com.demo.notpadapp.base.BaseActivity;
import com.demo.notpadapp.bean.Income;
import com.demo.notpadapp.bean.Mark;
import com.demo.notpadapp.bean.User;
import com.demo.notpadapp.util.UserUtil;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.UUID;

/**
 * 新增/修改便签
 */
public class MarkActivity extends BaseActivity {
    public static void callMe(Context context, Mark mark) {
        Intent intent = new Intent(context, MarkActivity.class);
        intent.putExtra("mark", mark);
        context.startActivity(intent);
    }

    private EditText mEtMark;
    private Button mBtnSave;
    private Button mBtnCancel;
    private Mark updateMark;


    @Override
    public int bindLayout() {
        return R.layout.activity_add_mark;
    }

    @Override
    public void initParms(Bundle parms) {
        updateMark = (Mark) parms.getSerializable("mark");
        if (updateMark != null) {
            updateMark = LitePal.where("mid = ?", updateMark.getMid()).findFirst(Mark.class);
        }
    }

    @Override
    public void initView(View view) {
        mEtMark = (EditText) findViewById(R.id.et_mark);
        mBtnSave = (Button) findViewById(R.id.btn_save);
        mBtnCancel = (Button) findViewById(R.id.btn_cancel);

        if (updateMark != null) {
            mBtnCancel.setText("删除");
            mEtMark.setText("" + updateMark.getMark());
        }
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (updateMark != null) {
                    updateMark.delete();
                    ToastUtils.showShort("删除成功！");
                    finish();
                } else {
                    finish();
                }
            }
        });

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEtMark.getText().toString().isEmpty()) {
                    ToastUtils.showShort("不允许为空！");
                    return;
                }
                if (mEtMark.getText().toString().length() > 200) {
                    ToastUtils.showShort("字数超过限制");
                    return;
                }
                if (updateMark != null) {
                    updateMark.setTime(TimeUtils.getNowMills());
                    updateMark.setMark(mEtMark.getText().toString());
                    updateMark.saveOrUpdate("mid = ? ", updateMark.getMid());
                    ToastUtils.showShort("修改成功！");
                    finish();
                } else {
                    Mark mark = new Mark();
                    mark.setMid(UUID.randomUUID().toString());
                    mark.setUid(UserUtil.getCurrentUser().getUid());
                    mark.setMark(mEtMark.getText().toString());
                    mark.setTime(TimeUtils.getNowMills());
                    mark.save();
                    ToastUtils.showShort("保存成功！");
                    finish();
                }
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }
}
