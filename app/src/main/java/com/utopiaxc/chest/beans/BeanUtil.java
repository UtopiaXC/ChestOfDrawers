package com.utopiaxc.chest.beans;

import com.utopiaxc.chest.utils.VARIABLES;

public class BeanUtil {
    VARIABLES.FUNCTIONS FUNCTION_ID;
    String title;
    String second_title;
    int icon_id;

    public BeanUtil(VARIABLES.FUNCTIONS FUNCTION_ID, String title, String second_title, int icon_id) {
        this.FUNCTION_ID = FUNCTION_ID;
        this.title = title;
        this.second_title = second_title;
        this.icon_id = icon_id;
    }

    public VARIABLES.FUNCTIONS getFUNCTION_ID() {
        return FUNCTION_ID;
    }

    public void setFUNCTION_ID(VARIABLES.FUNCTIONS FUNCTION_ID) {
        this.FUNCTION_ID = FUNCTION_ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSecond_title() {
        return second_title;
    }

    public void setSecond_title(String second_title) {
        this.second_title = second_title;
    }

    public int getIcon_id() {
        return icon_id;
    }

    public void setIcon_id(int icon_id) {
        this.icon_id = icon_id;
    }
}
