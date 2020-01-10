package com.king.king.util;


import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/**shihao.ma 2019/12/31 猫哆哩迁移**/
public abstract class EdpWebUtil {
    public static final String APP_API = "/yst";
    public static final String SEC_API = "/sec";
    public static final String EDS_API = "/eds";
    public static final String DEV_API = "/dev";
    public static final long JS_MAX_SAFE_NUMBER = (long)Math.pow(2.0D, 53.0D) - 1L;
    public static final long JS_MIN_SAFE_NUMBER;
    public static final String MEDIA_JSON = "application/json; charset=UTF-8";
    public static final String MEDIA_XML = "application/xml; charset=UTF-8";

    public EdpWebUtil() {
    }

    public static long generateSafeRandomNumber() {
        return ThreadLocalRandom.current().nextLong(0L, JS_MAX_SAFE_NUMBER);
    }

    public static Optional<HttpServletRequest> getRequest() {
        return getRequestContext().map(ServletRequestAttributes::getRequest);
    }

    public static Optional<ServletRequestAttributes> getRequestContext() {
        return Optional.ofNullable((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
    }

    public static Map<String, String> getQueryParams(String url) {
        return UriComponentsBuilder.fromHttpUrl(url).build().getQueryParams().toSingleValueMap();
    }

    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    public static String toTraceInfo(HttpServletRequest req) {
        return req.getRequestURI() + "?" + req.getQueryString() + " by u-" + req.getRemoteUser() + "@" + getClientIp(req);
    }

    public static Optional<Cookie> getCookie(HttpServletRequest request, String cookieName) {
        return Optional.ofNullable(request.getCookies()).flatMap((cookies) -> {
            return Arrays.stream(cookies).filter((cookie) -> {
                return cookie.getName().equals(cookieName);
            }).findAny();
        });
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

    public static boolean isReadRequest(HttpServletRequest request) {
        HttpMethod method = HttpMethod.resolve(request.getMethod());
        return method == HttpMethod.GET;
    }

    public static boolean isCommandRequest(HttpServletRequest request) {
        HttpMethod method = HttpMethod.resolve(request.getMethod());
        return method == HttpMethod.POST || method == HttpMethod.PATCH || method == HttpMethod.PUT || method == HttpMethod.DELETE;
    }

    public static boolean isFileRequest(HttpServletRequest request) {
        String contentType = request.getContentType();
        return contentType != null && MediaType.valueOf(contentType).isCompatibleWith(MediaType.MULTIPART_FORM_DATA);
    }

    public static BodyBuilder opBuilder(OpResult result) {
        BodyBuilder builder = ResponseEntity.ok();
        Optional.ofNullable(result).ifPresent((r) -> {
            BodyBuilder var10000 = (BodyBuilder)builder.header("el-result-code", new String[]{r.getCode()});
        });
        return builder;
    }

    static {
        JS_MIN_SAFE_NUMBER = -JS_MAX_SAFE_NUMBER;
    }

    public static enum ApiTypes {
        sec("/sec/", "安全接口"),
        ops("/ops/", "管理接口"),
        api("/api/", "服务接口");

        private String path;
        private String name;

        private ApiTypes(String path, String name) {
            this.path = path;
            this.name = name;
        }

        public String getPath() {
            return this.path;
        }

        public String getName() {
            return this.name;
        }
    }
}
