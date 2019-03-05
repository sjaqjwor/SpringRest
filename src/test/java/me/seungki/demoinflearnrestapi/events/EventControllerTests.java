package me.seungki.demoinflearnrestapi.events;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Vector;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



//web과 관련된 빈들이 등록이 된다.
//단위 테스트라고 보기에는 많은것들이 포함되어 있다.
//repository는 빈으로 등록x
@RunWith(SpringRunner.class)
//springapplication 찾아서 모든 빈을 등록해준다.
@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTests {

    //가짜 요청을 만들어서 처리할 수 있다.
    // web server을 띄우지는 앟지만 디스패처 서블릿을 사용한다. 그렇기에 단위테스트 보다 느리다.
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    //mock bean이기에 데이터베이스에 값을 저장하더라도 리턴되는 값은 null이다.
//    @MockBean
//    EventRepository eventRepository;

    @Test
    public void createEvent() throws Exception{
        Event event = Event.builder()
                .id(100)
                .name("Spring")
                .description("Rest Api Developement with Spring")
                .beginEnrollmentDateTime(LocalDateTime.of(2018,11,12,13,22))
                .closeEnrollmentDateTime(LocalDateTime.of(2018,11,13,13,22))
                .beginEventDateTime(LocalDateTime.of(2018,11,25,14,22))
                .endEventDateTime(LocalDateTime.of(2018,11,26,14,21))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("d2")
                .free(true)
                .offline(true)
                .eventStatus(EventStatus.PUBLISHED)
                .build();


        mockMvc.perform(post("/api/events/")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaTypes.HAL_JSON)
                    .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("id").value(Matchers.not(100)))
                .andExpect(jsonPath("free").value(Matchers.not(true)))
                .andExpect(jsonPath("eventStatus").value(EventStatus.DRAFE.name()))
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE,MediaTypes.HAL_JSON_UTF8_VALUE));


    }


}
