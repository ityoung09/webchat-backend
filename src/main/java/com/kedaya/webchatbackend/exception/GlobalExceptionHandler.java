package com.kedaya.webchatbackend.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.kedaya.webchatbackend.common.Res;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 全局异常处理器
 * 统一处理系统中的各种异常，返回标准化的错误响应
 *
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.exception
 * @Project：webchat-backend
 * @name：GlobalExceptionHandler
 * @Date：2025-07-16 23:45
 * @Filename：GlobalExceptionHandler
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理 @Valid 验证失败异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Res<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        Res<Object> response = Res.fail("400", "参数验证失败", errors.toString());
        return ResponseEntity.ok(response);
    }

    /**
     * 处理 @Validated 验证失败异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Res<Object>> handleConstraintViolationException(ConstraintViolationException ex) {
        String errors = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        Res<Object> response = Res.fail("400", "参数验证失败", errors);
        return ResponseEntity.ok(response);
    }

    /**
     * 处理绑定异常
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Res<Object>> handleBindException(BindException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        Res<Object> response = Res.fail("400", "参数绑定失败", errors.toString());
        return ResponseEntity.ok(response);
    }

    /**
     * 处理token失效问题
     */
    @ExceptionHandler(NotLoginException.class)
    public ResponseEntity<Res<Object>> handleNotLoginException(NotLoginException ex) {
        Res<Object> response = Res.fail("401", ex.getMessage());
        return ResponseEntity.ok(response);
    }

    // ==================== 自定义异常处理 ====================

    /**
     * 处理基础异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Res<Object>> handleException(Exception ex) {
        log.error("业务异常: message={}",
                ex.getMessage(), ex);
        Res<Object> response = Res.fail("500", "系统内部异常", ex.getMessage());
        return ResponseEntity.ok(response);
    }

    /**
     * 处理基础异常
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Res<Object>> handleBaseException(BaseException ex) {
        log.error("业务异常: code={}, message={}, detailMessage={}",
                ex.getCode(), ex.getMessage(), ex.getDetailMessage(), ex);

        Res<Object> response = Res.fail(ex.getCode(), ex.getMessage(), ex.getDetailMessage());
        return ResponseEntity.ok(response);
    }

    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Res<Object>> handleBusinessException(BusinessException ex) {
        log.warn("业务异常: code={}, message={}, detailMessage={}",
                ex.getCode(), ex.getMessage(), ex.getDetailMessage(), ex);

        Res<Object> response = Res.fail(ex.getCode(), ex.getMessage(), ex.getDetailMessage());
        return ResponseEntity.ok(response);
    }
}
