package hr.ivlahek.showcase.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Aspect
@Component
public class RepositoryInterceptor {

    @Autowired
    private EntityManager entityManager;

    private Logger logger = LoggerFactory.getLogger(RepositoryInterceptor.class);

    @Around("execution(* org.springframework.data.jpa.repository.JpaRepository.*(..))")
    public Object inWebLayer(ProceedingJoinPoint joinPoint) throws Throwable {
        if (entityManager.isJoinedToTransaction()) {
            Session session = entityManager.unwrap(Session.class);
            session.enableFilter("userAccountFilter").setParameter("organizationId", TenantContext.getCurrentOrganization().getId());
            session.enableFilter("mobileApplicationFilter").setParameter("organizationId", TenantContext.getCurrentOrganization().getId());
        }
        return joinPoint.proceed();
    }

}
