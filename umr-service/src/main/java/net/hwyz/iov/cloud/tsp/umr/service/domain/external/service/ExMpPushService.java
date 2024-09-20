package net.hwyz.iov.cloud.tsp.umr.service.domain.external.service;

import net.hwyz.iov.cloud.tsp.umr.service.domain.contract.enums.PushTargetType;
import net.hwyz.iov.cloud.tsp.umr.service.domain.template.model.MessageVo;
import net.hwyz.iov.cloud.tsp.umr.service.domain.template.model.NotificationVo;

import java.util.Set;

/**
 * 外部手机推送相关服务
 *
 * @author hwyz_leo
 */
public interface ExMpPushService {

    /**
     * 推送手机
     *
     * @param pushTargetType 推送目标类型
     * @param pushTargetSet  推送目标集合
     * @param notification   通知
     * @param message        自定义消息
     */
    void push(PushTargetType pushTargetType, Set<String> pushTargetSet, NotificationVo notification, MessageVo message);

}
