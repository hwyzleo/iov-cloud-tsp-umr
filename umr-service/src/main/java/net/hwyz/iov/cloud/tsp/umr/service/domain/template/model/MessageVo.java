package net.hwyz.iov.cloud.tsp.umr.service.domain.template.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String msgContent;

}
