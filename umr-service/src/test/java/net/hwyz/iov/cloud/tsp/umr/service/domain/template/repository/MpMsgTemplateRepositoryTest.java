package net.hwyz.iov.cloud.tsp.umr.service.domain.template.repository;

import net.hwyz.iov.cloud.tsp.umr.service.BaseTest;
import net.hwyz.iov.cloud.tsp.umr.service.domain.template.model.MessageVo;
import net.hwyz.iov.cloud.tsp.umr.service.domain.template.model.MpMsgTemplateDo;
import net.hwyz.iov.cloud.tsp.umr.service.domain.template.model.NotificationVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

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
    @DisplayName("保存-新增")
    public void testSave_create() throws Exception {
        MpMsgTemplateDo mpMsgTemplateDo = MpMsgTemplateDo.builder()
                .name("车控通知")
                .code("MP_000001")
                .notification(NotificationVo.builder().alert("车控操作成功").build())
                .build();
        mpMsgTemplateDo.init();
        mpMsgTemplateRepository.save(mpMsgTemplateDo);
    }

    @Test
    @Order(2)
    @DisplayName("保存-修改")
    public void testSave_modify() throws Exception {
        Map<String, Object> extras = new HashMap<>(3);
        extras.put("cmdId", "指令ID");
        extras.put("cmdState", "指令状态");
        extras.put("failureMsg", "失败原因");
        MpMsgTemplateDo mpMsgTemplateDo = MpMsgTemplateDo.builder()
                .id(1L)
                .name("车控通知")
                .code("MP_000001")
                .notification(NotificationVo.builder().alert("车控操作成功").build())
                .message(MessageVo.builder().msgContent("车控操作成功").extras(extras).build())
                .build();
        mpMsgTemplateDo.stateLoad();
        mpMsgTemplateDo.updateName("车控通知更新");
        mpMsgTemplateRepository.save(mpMsgTemplateDo);
    }

}
