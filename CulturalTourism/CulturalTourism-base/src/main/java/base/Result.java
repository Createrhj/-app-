package base;

import lombok.Data;

@Data
public class Result {

    private int code;
    private int total;
    private String msg;
    private Object data;

    public static Result fail(){
        return result(400,"失败",0,null);
    }

    public static Result fail(String msg){
        return result(400,msg,0,null);
    }

    public static Result success(){
        return result(200,"成功",0,null);
    }

    public static Result success(Object data){
        return result(200,"成功",0,data);
    }


    public static Result success(int total,Object data){
        return result(200,"成功",total,data);
    }

    public static Result result(int code,String msg,int total,Object data){
        Result result=new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setTotal(total);
        result.setData(data);
        return result;
    }

}
