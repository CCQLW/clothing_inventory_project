package com.example.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 蒋鹏
 * @since 2022-06-04
 */
@Data
//@EqualsAndHashCode(callSuper = false)
public class Article_number {

//    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;// 序号

    private String articleNumber;//货号

    private String tradeName;    //商品名

    private String colorNo;    //色号

    private Integer size;    //尺码

    @TableLogic(value = "0", delval = "1")
    private byte putDelete;   //是否删除

}
