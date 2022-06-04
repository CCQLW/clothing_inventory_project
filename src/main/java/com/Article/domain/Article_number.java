package com.Article.domain;

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
@EqualsAndHashCode(callSuper = false)
public class Article_number implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 货号
     */
    private String article_number;

    /**
     * 商品名
     */
    private String trade_name;

    /**
     * 色号
     */
    private String color_no;

    /**
     * 尺码
     */
    private Integer size;

    /**
     * 是否删除
     */
    @TableLogic(value = "0", delval = "1")
    private Integer put_delete;

}
