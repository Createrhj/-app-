package entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class OrdersDetail implements Serializable {
    @TableField("user_name")
    private String userName;
    @TableField("goods_category")
    private String goodsCategory;
    @TableField("goods_store")
    private String goodsStore;
    @TableField("goods_name")
    private String goodsName;
    @TableField("goods_img")
    private String goodsImg;
    @TableField("goods_location")
    private String goodsLocation;
    @TableField("goods_number")
    private int goodsNumber;
    @TableField("goods_price")
    private float goodsPrice;
    @TableField("goods_total")
    private float goodsTotal;
    @TableField("name")
    private String name;
    @TableField("phone")
    private String phone;
    @TableField("province")
    private String province;
    @TableField("city")
    private String city;
    @TableField("detail_address")
    private String detailAddress;

}
