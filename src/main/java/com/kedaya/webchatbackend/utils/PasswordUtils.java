package com.kedaya.webchatbackend.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.utils
 * @Project：webchat-backend
 * @name：PasswordUtils
 * @Date：2025-07-17 23:11
 * @Filename：PasswordUtils
 * 密码工具类：使用 BCrypt 加密和校验密码
 * 特点：
 *  - 不可逆加密（哈希）
 *  - 每次加密结果都不同（因为包含随机 salt）
 *  - 验证时会自动提取哈希中的 salt 和参数来完成校验
 */
public class PasswordUtils {

    /**
     * 使用 BCrypt 加密原始密码
     * @param password 原始明文密码
     * @return 加密后的密码字符串（包含 salt 和 cost 参数）
     */
    public static String hashPassword(String password) {
        // cost 参数为 12，计算复杂度为 2^12 = 4096 次迭代
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    /**
     * 校验原始密码是否与加密后的密码一致
     * @param password 原始明文密码（用户输入）
     * @param hashedPassword 数据库存储的加密密码（包含 salt）
     * @return 是否验证成功（true 表示密码正确）
     */
    public static boolean verifyPassword(String password, String hashedPassword) {
        // BCrypt 自动从 hashedPassword 中提取 salt 和 cost 参数，再对比
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);
        return result.verified;
    }

    /**
     * 示例代码：加密 + 验证
     */
    public static void main(String[] args) {
        String rawPassword = "123456";

        // 1. 加密原始密码（结果包含 salt）
        String hashed = hashPassword(rawPassword);
        System.out.println("加密后：" + hashed);

        // 2. 用已知的加密密码进行验证（来自数据库或模拟值）
        String storedHash = "$2a$12$9bShCWtCFtptCndJnmMCR.4/fGD9IHr2f785w7C.N6boBq5LXLEj2";

        // 验证正确的密码
        System.out.println("验证正确：" + verifyPassword("123456", storedHash));

        // 验证错误的密码
        System.out.println("验证错误：" + verifyPassword("wrongpass", hashed));
    }
}