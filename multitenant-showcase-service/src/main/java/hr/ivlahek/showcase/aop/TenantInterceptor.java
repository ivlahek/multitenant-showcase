package hr.ivlahek.showcase.aop;

import hr.ivlahek.showcase.persistence.entity.Tenant;
import hr.ivlahek.showcase.persistence.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@Aspect
@Component
@RequiredArgsConstructor
public class TenantInterceptor {

    private static final Logger log = LoggerFactory.getLogger(TenantInterceptor.class);

    private final TenantRepository tenantRepository;

    @Around("@annotation(InboundRequest)")
    public Object logInboundRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("Intercepting inbound request...");

        log.debug("Extracting tenant id from method arguments!");
        String companyId = extractTenantId(joinPoint)
                .orElseThrow(RuntimeException::new);

        log.debug("Finding tenant by id!");
        Tenant tenant = tenantRepository
                .findByExternalId(companyId)
                .orElseThrow(RuntimeException::new);

        log.debug("Setting current tenant to Thread local variable!");
        TenantContext.setCurrentTenant(tenant);

        try {
            log.debug("Continuing with the execution!");
            return joinPoint.proceed();
        } finally {
            log.error(" Clearing tenant context!");
            TenantContext.clear();
        }
    }

    public Optional<String> extractTenantId(ProceedingJoinPoint joinPoint) {
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

}
