package com.example.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;

import com.example.dao.Article_numberDao;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 
 * </p>
 *
 * @author 蒋鹏
 * @since 2022-06-05
 */
@Data
public class Warehousing_detail{

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;    //序号

    private Integer grnId;      //入库单序号

    private Integer articleId;      //货号序号

    private String articleNumber;    //货号

    private String tradeName;    //商品名

    private String colorNo;    //色号

    private Integer size;    //尺码

    private Integer number;    //数量

    @TableLogic(value = "0", delval = "1")
    private byte putDelete;    //是否删除

    void setArticleNumber(Article_number cArticleNumber){
        articleNumber = cArticleNumber.getArticleNumber();
    }

    void setTradeName(Article_number cArticleNumber){
        tradeName = cArticleNumber.getTradeName();
    }

    void setColorNo(Article_number cArticleNumber){
        colorNo = cArticleNumber.getColorNo();
    }

    void setSize(Article_number cArticleNumber){
        size = cArticleNumber.getSize();
    }

    public void setArticle(Article_number cArticleNumber){
        setArticleNumber(cArticleNumber);
        setTradeName(cArticleNumber);
        setColorNo(cArticleNumber);
        setSize(cArticleNumber);
    }

}
