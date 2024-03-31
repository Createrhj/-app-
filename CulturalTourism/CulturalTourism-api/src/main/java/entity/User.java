package entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@TableName("user")
@Accessors(chain = true)
public class User implements Serializable {
    @TableId("user_id")
    private int userID;
    @TableField("user_account")
    private String userAccount;
    @TableField("user_password")
    private String userPassword;
    @TableField("user_phone")
    private String userPhone;
    @TableField("user_avatar")
    private String userAvatar;
    @TableField("user_name")
    private String userName;
    @TableField("user_sex")
    private String userSex;
    @TableField("user_age")
    private int userAge;
    @TableField("user_detail")
    private String userDetail;
    @TableField("user_role")
    private String userRole;
}
