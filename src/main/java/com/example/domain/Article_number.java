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

    private Integer number = 0;     //数量

    @TableLogic(value = "0", delval = "1")
    private byte putDelete;   //是否删除

    public void setArticleNumber(){
        String strTradeName;
        switch (tradeName){
            case "T恤" : {
                strTradeName = "T";
                break;
            }
            case "长T" : {
                strTradeName = "CT";
                break;
            }
            case "POLO衫" : {
                strTradeName = "PL";
                break;
            }
            case "棒球衫" : {
                strTradeName = "BQS";
                break;
            }
            case "背心" : {
                strTradeName = "BX";
                break;
            }
            case "衬衫" : {
                strTradeName = "CS";
                break;
            }
            case "卫衣" : {
                strTradeName = "WY";
                break;
            }
            default:{
                strTradeName = "ATHER";
                break;
            }
        }
        articleNumber = strTradeName + "-" + colorNo + "-" + size;
    }
}
