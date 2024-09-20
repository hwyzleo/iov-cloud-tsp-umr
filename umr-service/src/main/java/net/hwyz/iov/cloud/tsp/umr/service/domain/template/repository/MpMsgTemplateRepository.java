package net.hwyz.iov.cloud.tsp.umr.service.domain.template.repository;

import net.hwyz.iov.cloud.tsp.framework.commons.domain.BaseRepository;
import net.hwyz.iov.cloud.tsp.umr.service.domain.template.model.MpMsgTemplateDo;

import java.util.Optional;

/**
 * 手机消息模板领域仓库接口
 *
 * @author hwyz_leo
 */
public interface MpMsgTemplateRepository extends BaseRepository<Long, MpMsgTemplateDo> {

    /**
     * 根据模板代码获取手机消息模板领域对象
     *
     * @param code 模板代码
     * @return 手机消息模板领域对象
     */
    Optional<MpMsgTemplateDo> getByCode(String code);

}
