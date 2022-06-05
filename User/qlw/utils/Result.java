package com.qlw.utils;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Result {
    private String result;
    private Object data;

    public Result(){

    }

    public Result(boolean flag){
        if(flag) this.result ="success";
        else this.result = "操作失败";
    }
    public Result(String result){
        this.result = result;
        this.data = null;
    }

    public Result(Object data){
        this.data = data;
        this.result = "success";
    }

    public Result(String result,Object data){
        this.result =result;
        this.data = data;
    }
}
