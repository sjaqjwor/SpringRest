package me.seungki.demoinflearnrestapi.events;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

@Component
public class EventValidator {

    public void validate(EventDto eventDto, Errors errors){
        if(eventDto.getBasePrice() > eventDto.getMaxPrice() && eventDto.getMaxPrice() !=0){
            //filed 에러
            //  errors.rejectValue("basePrice","wrongValue","BasePrice is wrong");
            //errors.rejectValue("MaxPrice","wrongValue","MaxPrice is wrong");
            // 글로벌 에러
            errors.reject("wrong Price","values to prices are wrong");
        }

        LocalDateTime endEventDateTime=eventDto.getEndEventDateTime();
        if(endEventDateTime.isBefore(eventDto.getBeginEventDateTime()) || endEventDateTime.isBefore(eventDto.getCloseEnrollmentDateTime())
        || endEventDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())){
            errors.rejectValue("endEventDateTime","wrong value","endEventDateTime is wrong");
        }


    }
}
