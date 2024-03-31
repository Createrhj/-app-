package entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@TableName("goods")
@Accessors(chain = true)
public class Goods implements Serializable {
    @TableId("goods_id")
    private int goodsID;
    @TableField("goods_category")
    private String goodsCategory;
    @TableField("goods_store")
    private String goodsStore;
    @TableField("goods_name")
    private String goodsName;
    @TableField("goods_img")
    private String goodsImg;
    @TableField("goods_detail")
    private String goodsDetail;
    @TableField("goods_price")
    private float goodsPrice;
    @TableField("goods_location")
    private String goodsLocation;
}
