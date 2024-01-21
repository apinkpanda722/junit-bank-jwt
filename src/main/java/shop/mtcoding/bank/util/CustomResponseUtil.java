package shop.mtcoding.bank.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shop.mtcoding.bank.dto.ResponseDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomResponseUtil {
    private static final Logger log = LoggerFactory.getLogger(CustomResponseUtil.class);

    public static void unAuthentication(HttpServletResponse response, String msg) {
        try {
            ObjectMapper om = new ObjectMapper();
            ResponseDto<?> responseDto = new ResponseDto<>(-1, msg, null);
            String responseBody = om.writeValueAsString(responseDto);

            response.setContentType("application/json; charset=utf-8");
            response.setStatus(401);
            response.getWriter().println(responseBody); // 예쁘게 메시지를 포장하는 공통적인 응답 DTO를 만들것
        } catch (IOException e) {
            log.error("서버 파싱 에러");
        }
    }

//    public static void unAuthorization(HttpServletResponse response, String msg) {
//        try {
//            ObjectMapper om = new ObjectMapper();
//            ResponseDto<?> responseDto = new ResponseDto<>(-1, msg, null);
//            String responseBody = om.writeValueAsString(responseDto);
//
//            response.setContentType("application/json; charset=utf-8");
//            response.setStatus(403);
//            response.getWriter().println(responseBody); // 예쁘게 메시지를 포장하는 공통적인 응답 DTO를 만들것
//        } catch (IOException e) {
//            log.error("서버 파싱 에러");
//        }
//    }

}