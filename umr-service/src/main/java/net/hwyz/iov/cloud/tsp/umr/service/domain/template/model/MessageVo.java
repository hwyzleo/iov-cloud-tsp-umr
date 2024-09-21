package net.hwyz.iov.cloud.tsp.umr.service.domain.template.model;

import cn.hutool.core.annotation.Alias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 自定义消息
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageVo {

    /**
     * 消息标题
     */
    private String title;
    /**
     * 消息内容
     */
    @Alias("msg_content")
    private String msgContent;
    /**
     * 消息附加信息
     */
    private Map<String, Object> extras;

}
