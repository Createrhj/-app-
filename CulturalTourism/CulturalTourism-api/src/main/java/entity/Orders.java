package entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("orders")
public class Orders implements Serializable {

    @TableId("orders_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private String ordersID;
    @TableField("user_id")
    private int userID;
    @TableField("address_id")
    private int addressID;
    @TableField("orders_money")
    private float ordersMoney;
    @TableField("orders_date")
    private String ordersDate;
    @TableField("orders_statue")
    private String ordersStatue;

}
