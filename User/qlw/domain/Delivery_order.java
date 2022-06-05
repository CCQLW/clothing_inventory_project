package com.qlw.domain;

import com.baomidou.mybatisplus.annotation.IdType;

import java.sql.Date;
import java.text.SimpleDateFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author QLW
 * @since 2022-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Delivery_order implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 序号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 单据编号
     */
    private String receiptNumber;
    /**
     * 所出仓库
     */
    private String warehouse;
    /**
     * 出库时间
     */
    private Date storageTime;
    /**
     * 经办人
     */
    private String agent;

    /**
     * 去处
     */
    private String whereabouts;

    /**
     * 是否删除
     */
    @TableLogic(value = "0", delval = "1")
    private Integer isDelete;


}
