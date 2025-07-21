# 自定义异常体系设计文档

## 概述

本项目采用分层的自定义异常体系，提供统一的错误处理机制，确保系统错误信息的一致性和可维护性。

## 异常体系架构

### 1. 异常类层次结构

```
RuntimeException
    └── BaseException (基础异常类)
        ├── BusinessException (业务异常类)
        │   ├── UserNotFoundException
        │   ├── UserAlreadyExistsException
        │   ├── InvalidPasswordException
        │   ├── AccountLockedException
        │   ├── FriendNotFoundException
        │   └── MessageNotFoundException
        ├── SystemException (系统异常类)
        └── AuthException (认证异常类)
            └── InvalidTokenException
```

### 2. 错误码设计

错误码采用5位数字格式：`模块代码(2位) + 具体错误(3位)`

| 模块 | 错误码范围 | 说明 |
|------|-----------|------|
| 通用 | 00xxx | 系统通用错误 |
| 用户 | 10xxx | 用户相关错误 |
| 认证 | 11xxx | 认证授权错误 |
| 好友 | 20xxx | 好友相关错误 |
| 消息 | 30xxx | 消息相关错误 |
| 聊天室 | 31xxx | 聊天室相关错误 |
| 文件 | 40xxx | 文件相关错误 |
| 系统 | 90xxx | 系统级错误 |
| 第三方 | 91xxx | 第三方服务错误 |

## 核心组件

### 1. ErrorCode 枚举

定义所有错误码和错误信息，提供统一的错误码管理。

```java
public enum ErrorCode {
    USER_NOT_FOUND("10001", "用户不存在"),
    USER_ALREADY_EXISTS("10002", "用户已存在"),
    // ...
}
```

### 2. BaseException 基础异常类

所有自定义异常的父类，提供统一的异常结构。

```java
public class BaseException extends RuntimeException {
    private final String code;           // 错误码
    private final String message;        // 错误信息
    private final String detailMessage;  // 详细错误信息
}
```

### 3. 具体异常类

- **BusinessException**: 业务逻辑异常
- **SystemException**: 系统级异常
- **AuthException**: 认证授权异常

### 4. GlobalExceptionHandler 全局异常处理器

统一处理所有异常，返回标准化的错误响应。

## 使用指南

### 1. 抛出异常

#### 使用预定义异常

```java
// 用户不存在
throw new UserNotFoundException();
throw UserNotFoundException.byUserId(123L);
throw UserNotFoundException.byMobile("13800138000");

// 用户已存在
throw UserAlreadyExistsException.byMobile("13800138000");

// 密码错误
throw new InvalidPasswordException();
throw InvalidPasswordException.weakPassword();

// 账户锁定
throw AccountLockedException.dueToFailedAttempts(30);
```

#### 使用通用异常

```java
// 业务异常
throw new BusinessException(ErrorCode.FRIEND_ALREADY_EXISTS);
throw new BusinessException(ErrorCode.MESSAGE_CONTENT_EMPTY, "消息内容为空");

// 系统异常
throw new SystemException(ErrorCode.DATABASE_ERROR, "数据库连接失败", cause);

// 认证异常
throw new AuthException(ErrorCode.AUTH_PERMISSION_DENIED, "权限不足");
```

#### 自定义错误码

```java
throw new BusinessException("99001", "自定义业务错误");
throw new SystemException("99002", "自定义系统错误", "详细信息");
```

### 2. 异常处理

全局异常处理器会自动处理所有异常，返回统一格式的响应：

```json
{
    "code": "10001",
    "message": "用户不存在",
    "errors": "用户ID: 123 不存在",
    "data": null
}
```

### 3. HTTP状态码映射

| 异常类型 | HTTP状态码 | 说明 |
|----------|-----------|------|
| BusinessException | 400 | 业务逻辑错误 |
| AuthException | 401 | 认证失败 |
| 权限异常 | 403 | 权限不足 |
| SystemException | 500 | 系统内部错误 |

## 最佳实践

### 1. 异常选择原则

- **业务逻辑错误**: 使用 `BusinessException` 或具体的业务异常类
- **系统级错误**: 使用 `SystemException`
- **认证授权错误**: 使用 `AuthException` 或 `InvalidTokenException`

### 2. 错误信息原则

- **用户友好**: 错误信息应该对用户友好，避免技术术语
- **信息充分**: 提供足够的信息帮助用户理解错误原因
- **安全考虑**: 不要暴露敏感的系统内部信息

### 3. 日志记录原则

- **业务异常**: 使用 WARN 级别记录
- **系统异常**: 使用 ERROR 级别记录
- **详细信息**: 在日志中记录详细的错误信息和堆栈跟踪

### 4. 异常链原则

- 保留原始异常信息，使用异常链传递根本原因
- 在捕获异常后重新抛出时，保留原始异常作为 cause

```java
try {
    // 数据库操作
    userRepository.save(user);
} catch (DataAccessException e) {
    throw new SystemException(ErrorCode.DATABASE_ERROR, "保存用户失败", e);
}
```

## 扩展指南

### 1. 添加新的错误码

在 `ErrorCode` 枚举中添加新的错误码：

```java
// 新增模块错误码
NEW_MODULE_ERROR("50001", "新模块错误"),
```

### 2. 添加新的异常类

继承相应的基础异常类：

```java
public class NewModuleException extends BusinessException {
    public NewModuleException() {
        super(ErrorCode.NEW_MODULE_ERROR);
    }
    
    // 其他构造函数...
}
```

### 3. 更新全局异常处理器

如果需要特殊处理某种异常，在 `GlobalExceptionHandler` 中添加对应的处理方法：

```java
@ExceptionHandler(NewModuleException.class)
public ResponseEntity<Res<Object>> handleNewModuleException(NewModuleException ex) {
    // 特殊处理逻辑
    return ResponseEntity.badRequest().body(Res.fail(ex.getCode(), ex.getMessage(), null));
}
```

## 测试

运行异常体系测试：

```bash
mvn test -Dtest=ExceptionTest
```

测试覆盖了：
- 错误码枚举功能
- 异常类构造和继承关系
- 静态工厂方法
- 异常链处理

## 注意事项

1. **性能考虑**: 异常处理有性能开销，不要将异常用于正常的业务流程控制
2. **国际化**: 当前错误信息为中文，如需国际化支持，可扩展 ErrorCode 枚举
3. **监控告警**: 建议对系统异常进行监控和告警
4. **文档维护**: 新增异常类型时，及时更新相关文档

## 相关文件

- `ErrorCode.java` - 错误码枚举
- `BaseException.java` - 基础异常类
- `BusinessException.java` - 业务异常类
- `SystemException.java` - 系统异常类
- `AuthException.java` - 认证异常类
- `GlobalExceptionHandler.java` - 全局异常处理器
- `ExceptionUsageExample.java` - 使用示例
- `ExceptionTest.java` - 测试类
