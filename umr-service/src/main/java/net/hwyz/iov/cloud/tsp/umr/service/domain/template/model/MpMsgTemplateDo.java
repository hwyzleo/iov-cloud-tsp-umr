package net.hwyz.iov.cloud.tsp.umr.service.domain.template.model;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.framework.commons.domain.BaseDo;
import net.hwyz.iov.cloud.tsp.framework.commons.domain.DomainObj;
import net.hwyz.iov.cloud.tsp.framework.commons.enums.Os;

import java.util.Set;

/**
 * 手机消息模板领域对象
 *
 * @author hwyz_leo
 */
@Slf4j
@Getter
@SuperBuilder
public class MpMsgTemplateDo extends BaseDo<Long> implements DomainObj<MpMsgTemplateDo> {

    /**
     * 模板名称
     */
    private String name;
    /**
     * 模板代码
     */
    private String code;
    /**
     * 推送平台
     * 为空默认全平台
     */
    private Set<Os> platform;
    /**
     * 通知
     */
    private NotificationVo notification;
    /**
     * 自定义消息
     */
    private MessageVo message;

    public void init() {
        stateInit();
    }

    /**
     * 更新模板名称
     *
     * @param name 模板名称
     */
    public void updateName(String name) {
        if (StrUtil.isNotBlank(name) && !name.equals(this.name)) {
            this.name = name;
            stateChange();
        }
    }

}
