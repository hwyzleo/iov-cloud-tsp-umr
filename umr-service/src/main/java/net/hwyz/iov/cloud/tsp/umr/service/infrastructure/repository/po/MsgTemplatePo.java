package net.hwyz.iov.cloud.tsp.umr.service.infrastructure.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.experimental.SuperBuilder;
import lombok.*;
import net.hwyz.iov.cloud.framework.mysql.po.BasePo;

/**
 * <p>
 * 消息模板表 数据对象
 * </p>
 *
 * @author hwyz_leo
 * @since 2024-09-19
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_msg_template")
public class MsgTemplatePo extends BasePo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 模板名称
     */
    @TableField("name")
    private String name;

    /**
     * 模板代码
     */
    @TableField("code")
    private String code;

    /**
     * 模板类型
     */
    @TableField("type")
    private String type;

    /**
     * 消息标题
     */
    @TableField("msg_title")
    private String msgTitle;

    /**
     * 消息内容
     */
    @TableField("msg_content")
    private String msgContent;

    /**
     * 消息设置
     */
    @TableField("msg_config")
    private String msgConfig;
}
