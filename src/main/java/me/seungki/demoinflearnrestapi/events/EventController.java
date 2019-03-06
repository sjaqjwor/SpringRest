package me.seungki.demoinflearnrestapi.events;


import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.net.URI;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Controller
@RequestMapping(value = "/api/events",produces = MediaTypes.HAL_JSON_UTF8_VALUE )
//procedure은 해당 컨트롤러에 있는 응답은 hal로 보내겠다는 의미
public class EventController {

    private final EventRepository eventRepository;

    private final ModelMapper modelMapper;

    private final EventValidator eventValidator;

    public EventController(EventRepository eventRepository,ModelMapper modelMapper,EventValidator eventValidator){
        this.eventRepository=eventRepository;
        this.modelMapper=modelMapper;
        this.eventValidator = eventValidator;
    }


    //@valid
    // binding 될떄 검증 수행 할 수 있다.
    @PostMapping
    public ResponseEntity createEvent(@RequestBody @Valid EventDto eventDto, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        eventValidator.validate(eventDto,errors);

        if(errors.hasErrors()){
            //errors는 시리얼라이즈가 안된다.
            //errors는 자바 빈 스팩을 고려하지 않은 클래스다
            //그렇기에 바디에 넣어서 errors을 전달하면 안된다.
            return ResponseEntity.badRequest().body(errors);
        }

        Event event = modelMapper.map(eventDto,Event.class);
        Event newEvent =  this.eventRepository.save(event);
        URI createUrl = linkTo(EventController.class).slash(newEvent.getId()).toUri();
        return ResponseEntity.created(createUrl).body(event);
    }
}

