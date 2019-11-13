package com.javapai.framework.enums;

/**
 * 用户行为类型.
 * <p/>
 */
public enum Action {

    CLICK("click", "点击事件"), INPUT("input", "输入事件"), GOTO("goto", "跳转事件"),GOIN("goin", "进入"),
    SLIDE("slide", "滑动"), SIGNUP("signup", "注册事件"),SignIn("signin", "登录"),
    OPEN("open", "打开APP"), CLOSE("close","关闭APP");
    /**
     * @param code
     * @param desc
     */
    private Action(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;

    private String desc;

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String getExtNameByCode(String code) {
        for (Action e : Action.values()) {
            if (e.getCode().equals(code)) {
                return e.desc;
            }
        }
        return null;
    }

}
