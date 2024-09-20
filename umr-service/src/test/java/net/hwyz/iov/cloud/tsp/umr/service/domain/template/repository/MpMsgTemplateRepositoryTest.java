package net.hwyz.iov.cloud.tsp.umr.service.domain.template.repository;

import net.hwyz.iov.cloud.tsp.umr.service.BaseTest;
import net.hwyz.iov.cloud.tsp.umr.service.domain.template.model.MpMsgTemplateDo;
import net.hwyz.iov.cloud.tsp.umr.service.domain.template.model.NotificationVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 手机消息模板领域仓库接口测试类
 *
 * @author hwyz_leo
 */
public class MpMsgTemplateRepositoryTest extends BaseTest {

    @Autowired
    private MpMsgTemplateRepository mpMsgTemplateRepository;

    @Test
    @Order(1)
    @DisplayName("保存")
    public void testSave() throws Exception {
        MpMsgTemplateDo mpMsgTemplateDo = MpMsgTemplateDo.builder()
                .name("车控通知")
                .code("MP_000001")
                .notification(NotificationVo.builder().alert("车控操作成功").build())
                .build();
        mpMsgTemplateDo.init();
        mpMsgTemplateRepository.save(mpMsgTemplateDo);
    }

}
