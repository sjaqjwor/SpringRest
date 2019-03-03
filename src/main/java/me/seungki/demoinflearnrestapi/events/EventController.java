package me.seungki.demoinflearnrestapi.events;


import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Controller
@RequestMapping(value = "/api/events",produces = MediaTypes.HAL_JSON_UTF8_VALUE )
//procedure은 해당 컨트롤러에 있는 응답은 hal로 보내겠다는 의미
public class EventController {


    @PostMapping
    public ResponseEntity createEvent(@RequestBody Event event){
        URI createUrl = linkTo(EventController.class).slash("{id}").toUri();
        event.setId(10);
        return ResponseEntity.created(createUrl).body(event);
    }
}
