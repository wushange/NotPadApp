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
import com.demo.notpadapp.bean.Pay;
import com.demo.notpadapp.util.BasisTimesUtils;
import com.demo.notpadapp.util.UserUtil;

import org.litepal.LitePal;

import java.util.Calendar;
import java.util.UUID;

/**
 * 添加/修改收入
 */
public class IncomeActivity extends BaseActivity {
    public static void callMe(Context context, Income pay) {
        Intent intent = new Intent(context, IncomeActivity.class);
        intent.putExtra("income", pay);
        context.startActivity(intent);
    }

    private EditText mEtMoney;
    private TextView mEtTime;
    private Spinner mIncomeSpinner;
    private EditText mEtUser;
    private EditText mEtMark;
    private Button mBtnSave;
    private Button mBtnCancel;


    int mYear = 0;
    int mMonth = 0;
    int mDay = 0;

    private Income newIncome;

    @Override
    public int bindLayout() {
        return R.layout.activity_addincome;
    }

    @Override
    public void initParms(Bundle parms) {
        newIncome = (Income) parms.getSerializable("income");
        if (newIncome != null) {
            newIncome = LitePal.where("iid = ?", newIncome.getIid()).findFirst(Income.class);
        }

    }

    @Override
    public void initView(View view) {

        mEtMoney = (EditText) findViewById(R.id.et_money);
        mEtTime = (TextView) findViewById(R.id.et_time);
        mIncomeSpinner = (Spinner) findViewById(R.id.income_spinner);
        mEtUser = (EditText) findViewById(R.id.et_user);
        mEtMark = (EditText) findViewById(R.id.et_mark);
        mBtnSave = (Button) findViewById(R.id.btn_save);
        mBtnCancel = (Button) findViewById(R.id.btn_cancel);


        if (newIncome != null) {
            mBtnCancel.setText("删除");
            mEtMoney.setText(""+newIncome.getMoney());
            mEtMark.setText(""+newIncome.getMark());
            mEtUser.setText(""+newIncome.getPayer());
            mEtTime.setText(TimeUtils.millis2String(newIncome.getTime(), "yyyy-MM-dd"));
            setSpinnerDefaultValue(mIncomeSpinner, newIncome.getType());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(TimeUtils.millis2Date(newIncome.getTime()));
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
                if (newIncome != null) {
                    newIncome.delete();
                    finish();
                } else {
                    finish();
                }
            }
        });
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (newIncome != null) {
                    newIncome.setMoney(Integer.parseInt(mEtMoney.getText().toString()));
                    newIncome.setPayer(mEtUser.getText().toString());
                    newIncome.setMark(mEtMark.getText().toString());
                    newIncome.setType(mIncomeSpinner.getSelectedItem().toString());
                    newIncome.setTimeStr(mEtTime.getText().toString());
                    newIncome.saveOrUpdate("iid = ?", newIncome.getIid());
                    ToastUtils.showShort("修改成功！");
                    finish();
                } else {
                    Income income = new Income();
                    income.setIid(UUID.randomUUID().toString());
                    income.setMark(mEtMark.getText().toString());
                    income.setMoney(Integer.parseInt(mEtMoney.getText().toString()));
                    income.setTimeStr(mEtTime.getText().toString());
                    income.setUid(UserUtil.getCurrentUser().getUid());
                    income.setPayer(mEtUser.getText().toString());
                    income.setType(mIncomeSpinner.getSelectedItem().toString());
                    income.save();
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
