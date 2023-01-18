package com.lxm.textconverter.config;

/**
 * Application constants
 */
public final class Constants {

    public static final String CONVERT_STATE_NEW = String.valueOf(CONVERT_STATE.NEW);
    public static final String CONVERT_STATE_RUNNING = String.valueOf(CONVERT_STATE.RUNNING);
    public static final String CONVERT_STATE_CANCELED = String.valueOf(CONVERT_STATE.CANCELED);
    public static final String CONVERT_STATE_SUCCESS = String.valueOf(CONVERT_STATE.SUCCESS);
    public static final String CONVERT_STATE_FAILED = String.valueOf(CONVERT_STATE.FAILED);
    public static final String PRODUCER_GROUP_TEXT_CONVERT = "PRODUCER-GROUP-TEXT-CONVERT";
    public static final String CONSUMER_GROUP_TEXT_CONVERT = "CONSUMER-GROUP-TEXT-CONVERT";
    public static final String NAMESERV_ADDR = "localhost:9876";
    public static final String TOPIC_TEXT_CONVERT = "TOPIC_CONVERT_TEXT";


    /**
     * state of text convert
     */
    public enum CONVERT_STATE {
        NEW, RUNNING, CANCELED, SUCCESS, FAILED
    }

    private Constants() {
    }
}
