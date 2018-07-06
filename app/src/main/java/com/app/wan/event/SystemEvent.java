package com.app.wan.event;

/**
 * Created by thkcheng on 2018/7/6.
 */

public class SystemEvent {

    int position;

    public SystemEvent(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
