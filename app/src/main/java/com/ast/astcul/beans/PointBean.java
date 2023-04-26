package com.ast.astcul.beans;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class PointBean implements Parcelable {

    private String pointName;
    //奖励节点的进度位置
    private int pointProgress;
    //奖励的活动币
    private int rewardCoin;


    protected PointBean(Parcel in) {
        pointName = in.readString();
        pointProgress = in.readInt();
        rewardCoin = in.readInt();
    }
    public PointBean() {
    }

    public static final Creator<PointBean> CREATOR = new Creator<PointBean>() {
        @Override
        public PointBean createFromParcel(Parcel in) {
            return new PointBean(in);
        }

        @Override
        public PointBean[] newArray(int size) {
            return new PointBean[size];
        }
    };

    public int getPointProgress() {
        return pointProgress;
    }

    public void setPointProgress(int pointProgress) {
        this.pointProgress = pointProgress;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }


    public int getRewardCoin() {
        return rewardCoin;
    }

    public void setRewardCoin(int rewardCoin) {
        this.rewardCoin = rewardCoin;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(pointName);
        dest.writeInt(pointProgress);
        dest.writeInt(rewardCoin);
    }
}
