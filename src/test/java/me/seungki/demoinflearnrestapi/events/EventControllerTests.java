package me.seungki.demoinflearnrestapi.events;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
//web과 관련된 빈들이 등록이 된다.
//단위 테스트라고 보기에는 많은것들이 포함되어 있다.
@WebMvcTest
public class EventControllerTests {

    //가짜 요청을 만들어서 처리할 수 있다.
    // web server을 띄우지는 앟지만 디스패처 서블릿을 사용한다. 그렇기에 단위테스트 보다 느리다.
    @Autowired
    MockMvc mockMvc;

    @Test
    public void createEvent() throws Exception{
        mockMvc.perform(post("/api/events/")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaTypes.HAL_JSON))
                .andExpect(status().isCreated());


    }


}
