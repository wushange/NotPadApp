package com.demo.notpadapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.demo.notpadapp.R;
import com.demo.notpadapp.base.BaseActivity;
import com.demo.notpadapp.bean.Income;
import com.demo.notpadapp.bean.Mark;
import com.demo.notpadapp.bean.Pay;
import com.demo.notpadapp.util.BasisTimesUtils;
import com.demo.notpadapp.util.UserUtil;

import org.litepal.LitePal;

import java.sql.Time;
import java.util.Calendar;
import java.util.UUID;

/**
 * 新增/修改支出
 */
public class PayActivity extends BaseActivity {
    public static void callMe(Context context, Pay pay) {
        Intent intent = new Intent(context, PayActivity.class);
        intent.putExtra("pay", pay);
        context.startActivity(intent);
    }

    private EditText mEtMoney;
    private TextView mEtTime;
    private Spinner mPaySpinner;
    private EditText mEtLocation;
    private EditText mEtMark;
    private Button mBtnSave;
    private Button mBtnCancel;

    int mYear = 0;
    int mMonth = 0;
    int mDay = 0;

    private Pay newPay;

    @Override
    public int bindLayout() {
        return R.layout.activity_addpay;
    }

    @Override
    public void initParms(Bundle parms) {
        newPay = (Pay) parms.getSerializable("pay");
        if (newPay != null) {
            newPay = LitePal.where("pid = ?", newPay.getPid()).findFirst(Pay.class);
        }
    }

    @Override
    public void initView(View view) {
        mEtMoney = (EditText) findViewById(R.id.et_money);
        mEtTime = (TextView) findViewById(R.id.et_time);
        mPaySpinner = (Spinner) findViewById(R.id.pay_spinner);
        mEtLocation = (EditText) findViewById(R.id.et_location);
        mEtMark = (EditText) findViewById(R.id.et_mark);
        mBtnSave = (Button) findViewById(R.id.btn_save);
        mBtnCancel = (Button) findViewById(R.id.btn_cancel);
        if (newPay != null) {
            mBtnCancel.setText("删除");
            mEtMoney.setText("" + newPay.getMoney());
            mEtTime.setText(TimeUtils.millis2String(newPay.getTime(), "yyyy-MM-dd"));
            mEtLocation.setText("" + newPay.getLocation());
            mEtMark.setText("" + newPay.getMark());
            setSpinnerDefaultValue(mPaySpinner, newPay.getType());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(TimeUtils.millis2Date(newPay.getTime()));
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH) + 1;
            mDay = calendar.get(Calendar.DAY_OF_MONTH);
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(TimeUtils.getNowDate());
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH) + 1;
            mDay = calendar.get(Calendar.DAY_OF_MONTH);
            mEtTime.setText(getData(mYear, mMonth, mDay));
        }
        mEtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BasisTimesUtils.showDatePickerDialog(getContext(), "选择日期"
                        , mYear, mMonth, mDay, new BasisTimesUtils.OnDatePickerListener() {
                            @Override
                            public void onConfirm(int year, int month, int dayOfMonth) {
                                mYear = year;
                                mMonth = month;
                                mDay = dayOfMonth;
                                mEtTime.setText(getData(mYear, mMonth, mDay));
                            }

                            @Override
                            public void onCancel() {

                            }
                        });
            }
        });

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (newPay != null) {
                    newPay.delete();
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
                if (newPay != null) {
                    newPay.setMark(mEtMark.getText().toString());
                    newPay.setMoney(Integer.parseInt(mEtMoney.getText().toString()));
                    newPay.setTimeStr(mEtTime.getText().toString());
                    newPay.setLocation(mEtLocation.getText().toString());
                    newPay.setType(mPaySpinner.getSelectedItem().toString());
                    newPay.saveOrUpdate("pid = ?", newPay.getPid());
                    ToastUtils.showShort("修改成功");
                    finish();
                } else {
                    Pay pay = new Pay();
                    pay.setPid(UUID.randomUUID().toString());
                    pay.setMark(mEtMark.getText().toString());
                    pay.setMoney(Integer.parseInt(mEtMoney.getText().toString()));
                    pay.setTimeStr(mEtTime.getText().toString());
                    pay.setUid(UserUtil.getCurrentUser().getUid());
                    pay.setLocation(mEtLocation.getText().toString());
                    pay.setType(mPaySpinner.getSelectedItem().toString());
                    pay.save();
                    ToastUtils.showShort("添加成功");
                    finish();
                }
            }
        });

    }

    @Override
    public void doBusiness(Context mContext) {

    }


}
