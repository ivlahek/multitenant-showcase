package hr.ivlahek.showcase.aop;

import hr.ivlahek.showcase.persistence.entity.Organization;
import hr.ivlahek.showcase.persistence.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;

import static java.lang.Integer.MAX_VALUE;
import static java.util.Optional.empty;
import static java.util.Optional.of;

@Aspect
@Component
@RequiredArgsConstructor
public class TenantInterceptor implements Ordered {

    private static final Logger log = LoggerFactory.getLogger(TenantInterceptor.class);

    private final OrganizationRepository organizationRepository;

    @Around("@annotation(InboundRequest)")
    public Object logInboundRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("Intercepting inbound request...");

        String companyId = extractCompanyId(joinPoint)
                .orElseThrow(RuntimeException::new);

        Organization organization = organizationRepository
                .findByExternalId(companyId)
                .orElseThrow(RuntimeException::new);

        TenantContext.setCurrentTenant(organization);

        Object proceed = joinPoint.proceed();

        TenantContext.clear();

        return proceed;
    }

    public Optional<String> extractCompanyId(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        Method method = ((MethodSignature) signature).getMethod();
        Annotation[][] annotationMatrix = method.getParameterAnnotations();
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            Annotation[] annotations = annotationMatrix[i];
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(TenantId.class)) {
                    return of((String) joinPoint.getArgs()[i]);
                }
            }
        }
        return empty();
    }

    @Override
    public int getOrder() {
        return MAX_VALUE;
    }

}
