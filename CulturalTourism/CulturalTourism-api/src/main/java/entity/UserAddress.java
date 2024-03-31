package entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("user_address")
public class UserAddress implements Serializable {
    @TableId("address_id")
    private int addressID;
    @TableField("user_id")
    private int userID;
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
    @TableField("weight")
    private int weight;
}
