package me.seungki.demoinflearnrestapi.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.validation.Errors;

import java.io.IOException;

//ovject mapper에 등록해야 해당 시리얼라이즈를 사용할수있다.
//jsonComponent사용하면 쉽게 등록이 가능하다.
@JsonComponent
public class ErrorsSerializer extends JsonSerializer<Errors> {
    @Override
    public void serialize(Errors errors, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        errors.getFieldErrors().stream().forEach(e ->{
            try {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("field",e.getField());
                jsonGenerator.writeStringField("objectName",e.getObjectName());
                jsonGenerator.writeStringField("code",e.getCode());
                jsonGenerator.writeStringField("defaultMessage",e.getDefaultMessage());
                Object rejectValue = e.getRejectedValue();
                if(rejectValue !=null){
                    jsonGenerator.writeStringField("rejectValue",rejectValue.toString());
                }
                jsonGenerator.writeEndObject();
            }catch (Exception e1){
                    e1.printStackTrace();
            }
        });

        errors.getGlobalErrors().forEach(e ->{
            try {
                jsonGenerator.writeStartObject();

                jsonGenerator.writeStringField("objectName",e.getObjectName());
                jsonGenerator.writeStringField("code",e.getCode());
                jsonGenerator.writeStringField("defaultMessage",e.getDefaultMessage());

                jsonGenerator.writeEndObject();
            }catch (Exception e1){
                e1.printStackTrace();
            }
        });
        jsonGenerator.writeEndArray();
    }
}
