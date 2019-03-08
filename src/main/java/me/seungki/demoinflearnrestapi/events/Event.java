package me.seungki.demoinflearnrestapi.events;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of="id")
@Entity
public class Event {

    @Id @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location;
    private int basePrice;
    private int maxPrice;
    private int limitOfEnrollment;
    private boolean free;
    private boolean offline;
    //ordinal 은 enum의 순서값을 저장한다.
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus=EventStatus.DRAFE;


    public void update(){
        if(this.basePrice==0 && this.maxPrice==0){
            this.free=true;
        }else{
            this.free=false;
        }
        //ofline
        if(this.location==null || this.location.isBlank()){
            this.offline =false;
        }else{
            this.offline=true;
        }
    }

}
