package entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@Accessors(chain = true)
public class CartDTO implements Serializable {

    @TableField("goods_id")
    public int goodsID;
    @TableField("goods_category")
    public String goodsCategory;
    @TableField("goods_store")
    public String goodsStore;
    @TableField("goods_name")
    public String goodsName;
    @TableField("goods_img")
    public String goodsImg;
    @TableField("goods_location")
    public String goodsLocation;
    @TableField("goods_price")
    public float goodsPrice;
    @TableField("goodsNumber")
    public int goodsNumber;
    @TableField("is_check")
    public int isCheck;
    @TableField("goods_total")
    public float goodsTotal;
}
