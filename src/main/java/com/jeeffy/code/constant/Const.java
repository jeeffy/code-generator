package com.jeeffy.code.constant;

/**
 * Created by Jeeffy on 2017/8/26.
 */
public class Const {

    public static final String DAO_JPA = "jpa";
    public static final String DAO_MYBATIS = "mybatis";

    public static final String TYPE_CONTROLLER = "controller";
    public static final String TYPE_CONTROLLER_TEST = "controllerTest";
    public static final String TYPE_SERVICE_TEST = "serviceTest";
    public static final String TYPE_SERVICE = "service";
    public static final String TYPE_DAO = "dao";
    public static final String TYPE_MAPPER = "mapper";
    public static final String TYPE_BEAN = "bean";

    public static final String[] CODE_JPA = {TYPE_CONTROLLER, TYPE_CONTROLLER_TEST, TYPE_SERVICE_TEST, TYPE_SERVICE, TYPE_DAO, TYPE_BEAN};
    public static final String[] CODE_MYBATIS = {TYPE_CONTROLLER, TYPE_CONTROLLER_TEST, TYPE_SERVICE, TYPE_DAO, TYPE_BEAN, TYPE_MAPPER};
}
