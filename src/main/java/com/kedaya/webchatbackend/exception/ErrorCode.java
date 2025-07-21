package com.kedaya.webchatbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码枚举
 * 错误码格式：模块代码(2位) + 具体错误(3位)
 * 
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.exception
 * @Project：webchat-backend
 * @name：ErrorCode
 * @Date：2025-07-17
 * @Filename：ErrorCode
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

    // ==================== 通用错误码 00xxx ====================
    SUCCESS("00000", "操作成功"),
    SYSTEM_ERROR("00001", "系统异常，请稍后重试"),
    PARAM_ERROR("00002", "参数错误"),
    PARAM_MISSING("00003", "参数缺失"),
    PARAM_INVALID("00004", "参数格式不正确"),
    REQUEST_METHOD_NOT_SUPPORTED("00005", "请求方法不支持"),
    REQUEST_TIMEOUT("00006", "请求超时"),
    NETWORK_ERROR("00007", "网络异常"),
    DATABASE_ERROR("00008", "数据库异常"),
    REDIS_ERROR("00009", "Redis异常"),

    // ==================== 用户模块错误码 10xxx ====================
    USER_NOT_FOUND("10001", "用户不存在"),
    USER_ALREADY_EXISTS("10002", "用户已存在"),
    USER_PASSWORD_ERROR("10003", "密码错误"),
    USER_ACCOUNT_LOCKED("10004", "账户已被锁定"),
    USER_ACCOUNT_DISABLED("10005", "账户已被禁用"),
    USER_MOBILE_ALREADY_EXISTS("10006", "手机号已被注册"),
    USER_MOBILE_NOT_FOUND("10007", "手机号未注册"),
    USER_MOBILE_FORMAT_ERROR("10008", "手机号格式错误"),
    USER_PASSWORD_TOO_WEAK("10009", "密码强度不够"),
    USER_PASSWORD_CONFIRM_ERROR("10010", "两次密码输入不一致"),
    USER_NICKNAME_TOO_LONG("10011", "昵称长度超出限制"),
    USER_NICKNAME_INVALID("10012", "昵称包含非法字符"),
    USER_AVATAR_UPLOAD_FAILED("10013", "头像上传失败"),
    USER_PROFILE_UPDATE_FAILED("10014", "用户信息更新失败"),

    // ==================== 数据库错误码 20xxx ====================
    DATABASE_ERROR_0001("20001", "数据库操作失败"),
    DATABASE_CONSTRAINT_VIOLATION("20002", "数据库约束违反"),
    DATABASE_NOT_NULL_VIOLATION("20003", "必填字段不能为空"),
    DATABASE_UNIQUE_VIOLATION("20004", "数据已存在，不能重复"),
    DATABASE_FOREIGN_KEY_VIOLATION("20005", "关联数据不存在"),
    DATABASE_DATA_TOO_LONG("20006", "数据长度超出限制"),

    // ==================== 认证授权错误码 11xxx ====================
    AUTH_TOKEN_MISSING("11001", "认证令牌缺失"),
    AUTH_TOKEN_INVALID("11002", "认证令牌无效"),
    AUTH_TOKEN_EXPIRED("11003", "认证令牌已过期"),
    AUTH_LOGIN_REQUIRED("11004", "请先登录"),
    AUTH_PERMISSION_DENIED("11005", "权限不足"),
    AUTH_LOGIN_FAILED("11006", "登录失败"),
    AUTH_LOGOUT_FAILED("11007", "退出登录失败"),
    AUTH_REFRESH_TOKEN_INVALID("11008", "刷新令牌无效"),
    AUTH_ACCOUNT_NOT_ACTIVATED("11009", "账户未激活"),
    AUTH_LOGIN_ATTEMPTS_EXCEEDED("11010", "登录尝试次数过多，请稍后重试"),

    // ==================== 好友模块错误码 20xxx ====================
    FRIEND_NOT_FOUND("20001", "好友不存在"),
    FRIEND_ALREADY_EXISTS("20002", "已经是好友关系"),
    FRIEND_REQUEST_NOT_FOUND("20003", "好友请求不存在"),
    FRIEND_REQUEST_ALREADY_SENT("20004", "好友请求已发送"),
    FRIEND_REQUEST_ALREADY_PROCESSED("20005", "好友请求已处理"),
    FRIEND_CANNOT_ADD_SELF("20006", "不能添加自己为好友"),
    FRIEND_REQUEST_EXPIRED("20007", "好友请求已过期"),
    FRIEND_DELETE_FAILED("20008", "删除好友失败"),
    FRIEND_BLOCK_FAILED("20009", "拉黑好友失败"),
    FRIEND_UNBLOCK_FAILED("20010", "取消拉黑失败"),

    // ==================== 消息模块错误码 30xxx ====================
    MESSAGE_NOT_FOUND("30001", "消息不存在"),
    MESSAGE_SEND_FAILED("30002", "消息发送失败"),
    MESSAGE_DELETE_FAILED("30003", "消息删除失败"),
    MESSAGE_CONTENT_EMPTY("30004", "消息内容不能为空"),
    MESSAGE_CONTENT_TOO_LONG("30005", "消息内容超出长度限制"),
    MESSAGE_TYPE_NOT_SUPPORTED("30006", "不支持的消息类型"),
    MESSAGE_FILE_UPLOAD_FAILED("30007", "文件上传失败"),
    MESSAGE_FILE_TOO_LARGE("30008", "文件大小超出限制"),
    MESSAGE_FILE_TYPE_NOT_SUPPORTED("30009", "不支持的文件类型"),
    MESSAGE_RECALL_FAILED("30010", "消息撤回失败"),
    MESSAGE_RECALL_TIMEOUT("30011", "消息撤回超时"),

    // ==================== 聊天室模块错误码 31xxx ====================
    CHAT_ROOM_NOT_FOUND("31001", "聊天室不存在"),
    CHAT_ROOM_ALREADY_EXISTS("31002", "聊天室已存在"),
    CHAT_ROOM_PERMISSION_DENIED("31003", "聊天室权限不足"),
    CHAT_ROOM_MEMBER_NOT_FOUND("31004", "聊天室成员不存在"),
    CHAT_ROOM_MEMBER_ALREADY_EXISTS("31005", "用户已在聊天室中"),
    CHAT_ROOM_FULL("31006", "聊天室人数已满"),
    CHAT_ROOM_CREATE_FAILED("31007", "创建聊天室失败"),
    CHAT_ROOM_JOIN_FAILED("31008", "加入聊天室失败"),
    CHAT_ROOM_LEAVE_FAILED("31009", "退出聊天室失败"),

    // ==================== 文件模块错误码 40xxx ====================
    FILE_NOT_FOUND("40001", "文件不存在"),
    FILE_UPLOAD_FAILED("40002", "文件上传失败"),
    FILE_DELETE_FAILED("40003", "文件删除失败"),
    FILE_SIZE_EXCEEDED("40004", "文件大小超出限制"),
    FILE_TYPE_NOT_SUPPORTED("40005", "不支持的文件类型"),
    FILE_NAME_INVALID("40006", "文件名无效"),
    FILE_STORAGE_ERROR("40007", "文件存储异常"),

    // ==================== 系统模块错误码 90xxx ====================
    SYSTEM_MAINTENANCE("90001", "系统维护中"),
    SYSTEM_BUSY("90002", "系统繁忙，请稍后重试"),
    SYSTEM_CONFIG_ERROR("90003", "系统配置错误"),
    SYSTEM_RESOURCE_EXHAUSTED("90004", "系统资源不足"),
    SYSTEM_SERVICE_UNAVAILABLE("90005", "服务暂不可用"),
    SYSTEM_VERSION_NOT_SUPPORTED("90006", "版本不支持"),
    SYSTEM_FEATURE_NOT_ENABLED("90007", "功能未启用"),

    // ==================== 第三方服务错误码 91xxx ====================
    THIRD_PARTY_SERVICE_ERROR("91001", "第三方服务异常"),
    SMS_SEND_FAILED("91002", "短信发送失败"),
    EMAIL_SEND_FAILED("91003", "邮件发送失败"),
    PAYMENT_FAILED("91004", "支付失败"),
    PUSH_NOTIFICATION_FAILED("91005", "推送通知失败");

    /**
     * 错误码
     */
    private final String code;

    /**
     * 错误信息
     */
    private final String message;
}
