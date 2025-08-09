package com.kedaya.webchatbackend.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @Author：CHENWEI
 * @Package：com.kedaya.webchatbackend.utils
 * @Project：webchat-backend
 * @name：JsonUtils
 * @Date：2025-07-27 23:26
 * @Filename：JsonUtils
 */
public class JsonUtils {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();

        // 日期格式统一（可改为你自己的格式）
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        // 忽略 null 字段序列化
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // 忽略未知字段（反序列化时）
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 忽略序列化失败的字段（避免序列化复杂对象时出错）
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // 支持 Java 8 时间类型
        mapper.registerModule(new JavaTimeModule());

        // 美化输出配置（可选）
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    // 对象转 JSON 字符串
    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("序列化失败：" + e.getMessage(), e);
        }
    }

    // JSON 字符串转对象
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("反序列化失败：" + e.getMessage(), e);
        }
    }

    // JSON 转泛型对象（如 List<T>, Map<String, T>）
    public static <T> T fromJson(String json, TypeReference<T> typeRef) {
        try {
            return mapper.readValue(json, typeRef);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("泛型反序列化失败：" + e.getMessage(), e);
        }
    }

    // JSON 转 Map
    public static Map<String, Object> toMap(String json) {
        return fromJson(json, new TypeReference<Map<String, Object>>() {
        });
    }

    // JSON 转 List<T>
    public static <T> List<T> toList(String json, Class<T> elementType) {
        try {
            JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, elementType);
            return mapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("List 反序列化失败：" + e.getMessage(), e);
        }
    }

    // Object 转 Map（通常用于调试）
    public static Map<String, Object> objToMap(Object obj) {
        return mapper.convertValue(obj, new TypeReference<Map<String, Object>>() {
        });
    }

    // Map 转对象
    public static <T> T mapToObj(Map<String, Object> map, Class<T> clazz) {
        return mapper.convertValue(map, clazz);
    }
}
