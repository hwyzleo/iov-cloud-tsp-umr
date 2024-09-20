package net.hwyz.iov.cloud.tsp.umr.service.domain.template.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通知内容值对象
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationVo {

    /**
     * 通知标题
     */
    private String alert;

}
