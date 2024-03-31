package entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class OrdersDTO implements Serializable {
    @TableField("orders.orders_id")
    private String ordersID;
    @TableField("orders_money")
    private float ordersMoney;
    @TableField("orders_date")
    private String ordersDate;
    @TableField("orders_statue")
    private String ordersStatue;

    @TableField("goods_category")
    private String goodsCategory;
    @TableField("goods_store")
    public String goodsStore;
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
