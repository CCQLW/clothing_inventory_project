package com.example.domain;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class Delivery_details implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    //    货号
    private String articleNumber;
    //    商品名
    private String tradeName;
    //    色号
    private String colorNo;
    //    尺码
    private Integer size;
    //    数量
    private Integer number;
    //    是否删除 0是 没删，1 删除
    @TableLogic(value = "0", delval = "1")
    private Integer isDelete;

}
