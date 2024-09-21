package net.hwyz.iov.cloud.tsp.umr.service.infrastructure.repository;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.framework.commons.domain.AbstractRepository;
import net.hwyz.iov.cloud.tsp.framework.commons.enums.ClientType;
import net.hwyz.iov.cloud.tsp.umr.service.domain.template.model.MessageVo;
import net.hwyz.iov.cloud.tsp.umr.service.domain.template.model.MpMsgTemplateDo;
import net.hwyz.iov.cloud.tsp.umr.service.domain.template.model.NotificationVo;
import net.hwyz.iov.cloud.tsp.umr.service.domain.template.repository.MpMsgTemplateRepository;
import net.hwyz.iov.cloud.tsp.umr.service.infrastructure.repository.dao.MsgTemplateDao;
import net.hwyz.iov.cloud.tsp.umr.service.infrastructure.repository.po.MsgTemplatePo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 手机消息模板领域仓库接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class MpMsgTemplateRepositoryImpl extends AbstractRepository<Long, MpMsgTemplateDo> implements MpMsgTemplateRepository {

    private final MsgTemplateDao msgTemplateDao;

    @Override
    public Optional<MpMsgTemplateDo> getById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean save(MpMsgTemplateDo mpMsgTemplateDo) {
        switch (mpMsgTemplateDo.getState()) {
            case NEW -> {
                Map<String, Object> config = new HashMap<>();
                String msgTitle = null;
                if (mpMsgTemplateDo.getNotification() != null) {
                    config.put("isNotification", true);
                    msgTitle = mpMsgTemplateDo.getNotification().getAlert();
                } else if (mpMsgTemplateDo.getMessage() != null) {
                    config.put("isMessage", true);
                    msgTitle = mpMsgTemplateDo.getMessage().getTitle();
                }
                String msgContent = null;
                if (mpMsgTemplateDo.getMessage() != null) {
                    config.put("isMessage", true);
                    msgContent = mpMsgTemplateDo.getMessage().getMsgContent();
                }
                MsgTemplatePo msgTemplatePo = MsgTemplatePo.builder()
                        .name(mpMsgTemplateDo.getName())
                        .code(mpMsgTemplateDo.getCode())
                        .type(ClientType.MP.name())
                        .msgTitle(msgTitle)
                        .msgContent(msgContent)
                        .msgConfig(JSONUtil.toJsonStr(config))
                        .build();
                msgTemplateDao.insertPo(msgTemplatePo);
            }
            case CHANGED -> {
                Map<String, Object> config = new HashMap<>();
                String msgTitle = null;
                if (mpMsgTemplateDo.getNotification() != null) {
                    config.put("isNotification", true);
                    msgTitle = mpMsgTemplateDo.getNotification().getAlert();
                } else if (mpMsgTemplateDo.getMessage() != null) {
                    config.put("isMessage", true);
                    msgTitle = mpMsgTemplateDo.getMessage().getTitle();
                }
                String msgContent = null;
                if (mpMsgTemplateDo.getMessage() != null) {
                    config.put("isMessage", true);
                    msgContent = mpMsgTemplateDo.getMessage().getMsgContent();
                    if (mpMsgTemplateDo.getMessage().getExtras() != null) {
                        config.put("extras", mpMsgTemplateDo.getMessage().getExtras());
                    }
                }
                MsgTemplatePo msgTemplatePo = MsgTemplatePo.builder()
                        .id(mpMsgTemplateDo.getId())
                        .name(mpMsgTemplateDo.getName())
                        .code(mpMsgTemplateDo.getCode())
                        .type(ClientType.MP.name())
                        .msgTitle(msgTitle)
                        .msgContent(msgContent)
                        .msgConfig(JSONUtil.toJsonStr(config))
                        .build();
                msgTemplateDao.updatePo(msgTemplatePo);
            }
            default -> {
                return false;
            }
        }
        return true;
    }

    @Override
    public Optional<MpMsgTemplateDo> getByCode(String code) {
        MsgTemplatePo msgTemplatePo = msgTemplateDao.selectPoByCode(code);
        if (msgTemplatePo == null) {
            return Optional.empty();
        }
        if (!ClientType.MP.name().equals(msgTemplatePo.getType())) {
            return Optional.empty();
        }
        if (StrUtil.isBlank(msgTemplatePo.getMsgConfig())) {
            return Optional.empty();
        }
        JSONObject jsonObject = JSONUtil.parseObj(msgTemplatePo.getMsgConfig());
        NotificationVo notification = null;
        if (jsonObject.containsKey("isNotification") && jsonObject.getBool("isNotification")) {
            notification = NotificationVo.builder()
                    .alert(msgTemplatePo.getMsgTitle())
                    .build();
        }
        MessageVo message = null;
        if (jsonObject.containsKey("isMessage") && jsonObject.getBool("isMessage")) {
            Map<String, Object> extras = null;
            if (jsonObject.containsKey("extras")) {
                extras = jsonObject.getJSONObject("extras").toBean(new TypeReference<>() {
                });
            }
            message = MessageVo.builder()
                    .title(msgTemplatePo.getMsgTitle())
                    .msgContent(msgTemplatePo.getMsgContent())
                    .extras(extras)
                    .build();
        }
        MpMsgTemplateDo mpMsgTemplateDo = MpMsgTemplateDo.builder()
                .name(msgTemplatePo.getName())
                .code(msgTemplatePo.getCode())
                .notification(notification)
                .message(message)
                .build();
        return Optional.of(mpMsgTemplateDo);
    }
}
