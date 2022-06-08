package com.example.domain;

import com.baomidou.mybatisplus.annotation.IdType;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author 蒋鹏
 * @since 2022-06-05
 */
@Data
public class Grn{

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;    //序号

    private String receiptNumber;    //单据编号

    private String warehouse;    //所入仓库

    private Date storageTime;    //入库时间

    private String agent;    //经办人

    private String source;    //来源

    @TableLogic(value = "0", delval = "1")
    private byte putDelete;    //是否删除

    @TableField(exist = false)
    private Date date = new Date();

    public void setReceiptNumber(){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        receiptNumber = "JP-QLW-" + df.format(date);
    }

    public void setStorageTime(){
        storageTime  = date;
    }
}
