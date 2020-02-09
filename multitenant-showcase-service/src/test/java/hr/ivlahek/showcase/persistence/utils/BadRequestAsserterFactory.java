package hr.ivlahek.showcase.persistence.utils;

import hr.ivlahek.showcase.dto.organization.OrganizationEndPoints;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class BadRequestAsserterFactory {

    public BadRequestAsserter createForOrganizationResource(TestRestTemplate restTemplate) {
        BadRequestAsserter badRequestAsserter = new BadRequestAsserter(restTemplate);
        badRequestAsserter.endPoint = OrganizationEndPoints.ORGANIZATION_RESOURCE;
        return badRequestAsserter;
    }

    public BadRequestAsserter createForOrganizationByIdResource(TestRestTemplate restTemplate) {
        BadRequestAsserter badRequestAsserter = new BadRequestAsserter(restTemplate);
        badRequestAsserter.endPoint = OrganizationEndPoints.ORGANIZATION_RESOURCE_ID;
        return badRequestAsserter;
    }
}
