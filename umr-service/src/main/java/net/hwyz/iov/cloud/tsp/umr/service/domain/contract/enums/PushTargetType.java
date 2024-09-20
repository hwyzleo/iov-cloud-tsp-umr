package net.hwyz.iov.cloud.tsp.umr.service.domain.contract.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 推送对象类型枚举类
 *
 * @author hwyz_leo
 */
@Getter
@AllArgsConstructor
public enum PushTargetType {

    /** 广播 **/
    ALL,
    /** 标签 **/
    TAG,
    /** 别名 **/
    ALIAS,
    /** 注册ID **/
    REG_ID;

}
