package me.seungki.demoinflearnrestapi.events;


import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.runner.RunWith;

//junitPrams는 테스트에 매개변수를 줄수 있어 코드의 양을 줄일 수 있다.
@RunWith(JUnitParamsRunner.class)
public class JunitParamsTest {



    @Test
  //  @Parameters({"0,0,true","100,0,false","0,100,false"})
    @Parameters(method = "parametersForTestFree")
    public void test(int basePrice,int maxPrice,boolean isFree){
        Event event = Event.builder()
                .basePrice(basePrice)
                .maxPrice(maxPrice)
                .build();

        //when
        event.update();

        //then
        assertThat(event.isFree()).isEqualTo(isFree);
    }
    private Object[] parametersForTestFree(){
        return new Object[]{
                new Object[]{0,0,true},new Object[]{100,0,false},new Object[]{0,100,false},new Object[]{100,200,false}
        };
    }

    @Test
    @Parameters(method = "parametersTestOffline")
    public void testOffline(String location,boolean check){
        Event event = Event.builder()
                .location(location)
                .build();

        //when
        event.update();

        //given
        assertThat(event.isOffline()).isEqualTo(check);
    }

    private Object[] parametersTestOffline(){
      return   new Object[]{
                new Object[]{"강남",true},new Object[]{null,false},new Object[]{" ",false}

        };
    }
}
