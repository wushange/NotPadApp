package com.demo.notpadapp.util;

import com.blankj.utilcode.util.CacheDoubleUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.demo.notpadapp.bean.Income;
import com.demo.notpadapp.bean.Mark;
import com.demo.notpadapp.bean.Pay;
import com.demo.notpadapp.bean.User;

import org.litepal.LitePal;

import java.util.List;

public class UserUtil {
    public static void login(User user) {
        CacheDoubleUtils.getInstance().put("currentUser", user);
    }

    public static void logout() {
        CacheDoubleUtils.getInstance().clear();
    }

    public static User getCurrentUser() {
        return (User) CacheDoubleUtils.getInstance().getSerializable("currentUser");
    }

    public static List<Pay> getPayList() {
        return LitePal.where("uid = ?", UserUtil.getCurrentUser().getUid()).find(Pay.class);
    }

    public static List<Income> getIncomeList() {
        return LitePal.where("uid = ?", UserUtil.getCurrentUser().getUid()).find(Income.class);
    }

    public static List<Mark> getMarkList() {
        return LitePal.where("uid = ?", UserUtil.getCurrentUser().getUid()).find(Mark.class);
    }

    public static int getPaySumInYear() {
        int sumMoney = 0;
        List<Pay> pays = LitePal.where("uid = ? and timeStr like ?", getCurrentUser().getUid(),
                "%" + TimeUtils.millis2String(TimeUtils.getNowMills(), "yyyy") + "%").find(Pay.class);
        for (Pay p : pays) {
            sumMoney += p.getMoney();
        }
        return sumMoney;
    }


    public static int getPaySumInMonth() {
        int sumMoney = 0;
        List<Pay> pays = LitePal.where("uid = ? and timeStr like ?", getCurrentUser().getUid(),
                "%" + TimeUtils.millis2String(TimeUtils.getNowMills(), "yyyy-MM") + "%").find(Pay.class);
        for (Pay p : pays) {
            sumMoney += p.getMoney();
        }
        return sumMoney;
    }

    public static int getPaySumInDay() {
        int sumMoney = 0;
        List<Pay> pays = LitePal.where("uid = ? and timeStr = ?", getCurrentUser().getUid(),
                TimeUtils.millis2String(TimeUtils.getNowMills(), "yyyy-MM-dd")).find(Pay.class);
        for (Pay p : pays) {
            sumMoney += p.getMoney();
        }
        return sumMoney;
    }

    public static int getIncomeSumInYear() {
        int sumMoney = 0;
        List<Income> pays = LitePal.where("uid = ? and timeStr like ?", getCurrentUser().getUid(),
                "%" + TimeUtils.millis2String(TimeUtils.getNowMills(), "yyyy") + "%").find(Income.class);
        for (Income p : pays) {
            sumMoney += p.getMoney();
        }
        return sumMoney;
    }


    public static int getIncomeSumInMonth() {
        int sumMoney = 0;
        List<Income> pays = LitePal.where("uid = ? and timeStr like ?", getCurrentUser().getUid(),
                "%" + TimeUtils.millis2String(TimeUtils.getNowMills(), "yyyy-MM") + "%").find(Income.class);
        for (Income p : pays) {
            sumMoney += p.getMoney();
        }
        return sumMoney;
    }

    public static int getIncomeSumInDay() {
        int sumMoney = 0;
        List<Income> pays = LitePal.where("uid = ? and timeStr = ?", getCurrentUser().getUid(),
                TimeUtils.millis2String(TimeUtils.getNowMills(), "yyyy-MM-dd")).find(Income.class);
        for (Income p : pays) {
            sumMoney += p.getMoney();
        }
        return sumMoney;
    }

}
