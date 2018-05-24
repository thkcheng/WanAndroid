package com.app.play.entity;

/**
 * Created by xiexucheng on 17/2/17.
 */

public class ResultEntity {

    String message;
    String timestamp;
    int codeValue;
    int bonus;
    /** -1不提示错误  1吐司. 2只确定按钮系统弹框. 3话筒提示框. 4机器人弹框 */
    int showType;
    /** 吐司提示位置 -1不展示  1页面顶部. 2页面中部. 3页面底部 */
    int showLocation;
    /** 吐司时长 默认3秒 */
    int showSecond;

    public ResultEntity(int codeValue, String message) {
        this.message = message;
        this.codeValue = codeValue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(int codeValue) {
        this.codeValue = codeValue;
    }

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public int getShowLocation() {
        return showLocation;
    }

    public void setShowLocation(int showLocation) {
        this.showLocation = showLocation;
    }

    public int getShowSecond() {
        return showSecond;
    }

    public void setShowSecond(int showSecond) {
        this.showSecond = showSecond;
    }
}
