package hr.ivlahek.showcase.persistence.utils;

import hr.ivlahek.showcase.dto.error.ErrorMessage;
import hr.ivlahek.showcase.exception.messages.ExceptionLogable;
import org.assertj.core.api.Assertions;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

public class BadRequestAsserter {
    String endPoint;
    TestRestTemplate restTemplate;
    ErrorMessage genericErrorMesssage;

    public BadRequestAsserter(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BadRequestAsserter executeGet(int id) {
        this.genericErrorMesssage = this.restTemplate.exchange(this.endPoint, HttpMethod.GET, (HttpEntity) null, ErrorMessage.class, new Object[]{id}).getBody();
        return this;
    }

    public BadRequestAsserter executePost(Object request, Object... args) {
        this.genericErrorMesssage = this.restTemplate.postForEntity(this.endPoint, request, ErrorMessage.class, args).getBody();
        return this;
    }

    public BadRequestAsserter executePost(Object request) {
        this.genericErrorMesssage = this.restTemplate.postForEntity(this.endPoint, request, ErrorMessage.class, new Object[0]).getBody();
        return this;
    }

    public BadRequestAsserter executePostWithArgs(Object... args) {
        this.genericErrorMesssage = this.restTemplate.postForEntity(this.endPoint, (Object) null, ErrorMessage.class, args).getBody();
        return this;
    }

    public void assertWithMessage(ExceptionLogable exceptionMessage) {
        Assertions.assertThat((String) this.genericErrorMesssage.getMessages().get(0)).isEqualTo(exceptionMessage.getMessage());
    }

    public void assertWithCode(ExceptionLogable exceptionMessage) {
        Assertions.assertThat(this.genericErrorMesssage.getCode()).isEqualTo(exceptionMessage.getErrorCode());
    }

    public void assertWithMessage(String... expectedMessage) {
        Assertions.assertThat(this.genericErrorMesssage.getMessages()).contains(expectedMessage);
    }

    public BadRequestAsserter withEndPoint(final String endPoint) {
        this.endPoint = endPoint;
        return this;
    }
}
