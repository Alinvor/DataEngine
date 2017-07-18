package com.dovsnier.controller;

/**
 * Created by lizw on 2017/7/18.
 */

public interface MonitorFeedback {

    void info(String message);

    void debug(String message);

    void warning(String message);

    void error(String message, Exception e);

}
