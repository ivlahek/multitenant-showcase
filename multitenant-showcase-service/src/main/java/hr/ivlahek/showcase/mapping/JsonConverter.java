package hr.ivlahek.showcase.mapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.ivlahek.showcase.exception.InternalServerErrorException;
import hr.ivlahek.showcase.exception.messages.ExceptionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class JsonConverter {

    private ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(JsonConverter.class);

    @PostConstruct
    public void init() {
        objectMapper = new ObjectMapper();
    }

    public String write(Object publishToKafka) {
        try {
            return objectMapper.writeValueAsString(publishToKafka);
        } catch (JsonProcessingException e) {
            logger.error("Error while writing object to json string!", e);
            throw new InternalServerErrorException(ExceptionMessage.INTERNAL_ERROR);
        }
    }

    public <T> T readValue(String jsonObject, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonObject, clazz);
        } catch (IOException e) {
            logger.error("Error while transforming json string to object!", e);
            throw new InternalServerErrorException(ExceptionMessage.INTERNAL_ERROR);
        }
    }
}
