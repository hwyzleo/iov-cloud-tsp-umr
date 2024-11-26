package net.hwyz.iov.cloud.tsp.umr.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import net.hwyz.iov.cloud.tsp.umr.service.infrastructure.repository.po.MsgTemplatePo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 消息模板表 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2024-09-19
 */
@Mapper
public interface MsgTemplateDao extends BaseDao<MsgTemplatePo, Long> {

    /**
     * 根据代码查询消息模板
     *
     * @param code 消息模板代码
     * @return 消息模板
     */
    MsgTemplatePo selectPoByCode(String code);

}
