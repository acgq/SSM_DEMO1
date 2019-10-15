package cn.acgq.model;

import java.util.HashMap;
import java.util.Map;

public class JsonMsg {
    private String msg;
    private int code;
    private Map<String,Object> extendInfo=new HashMap<>();

    public static JsonMsg success(){
        JsonMsg jsonMsg=new JsonMsg();
        jsonMsg.setCode(100);
        jsonMsg.setMsg("操作成功");
        return jsonMsg;
    }
    public static JsonMsg fail(){
        JsonMsg jsonMsg=new JsonMsg();
        jsonMsg.setCode(101);
        jsonMsg.setMsg("操作失败!");
        return jsonMsg;
    }
    public JsonMsg addInfo(String key,Object obj){
        extendInfo.put(key, obj);
        return this;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Map<String, Object> getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(Map<String, Object> extendInfo) {
        this.extendInfo = extendInfo;
    }
}
