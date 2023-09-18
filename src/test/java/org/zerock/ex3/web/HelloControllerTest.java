package org.zerock.ex3.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.is;
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @DisplayName("hello가 리턴된다")
    @Test
    public void hello() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                    get("/hello/dto")  //get 방식의 요청
                        .param("name",name)  // 파라미터 String 만 허용
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())  // Http header의 status 200 OK
                .andExpect(jsonPath("$.name", is(name)))  //$. 다음에 필드명
                .andExpect(jsonPath("$.amount", is(amount)));
                          //jsonPath : JSON 응답 값을 필드별로 검증
    }
}
