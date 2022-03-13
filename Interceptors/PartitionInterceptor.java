
package ca.on.health.smilecdr.interceptor;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.interceptor.model.RequestPartitionId;
import ca.uhn.fhir.jpa.api.dao.DaoRegistry;
import ca.uhn.fhir.rest.server.servlet.ServletRequestDetails;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Interceptor
public class PartitionInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(PartitionInterceptor.class);

    @PostConstruct
    protected void init() {
        logger.info("PARTITION INTERCEPTOR");

        try {
        } catch (Exception e) {
            String message = "critical error:partition interceptor error";
            logger.error(message, e);
        }
    }
    
    @Override
    public DaoRegistry getDaoRegistry() {
        throw new UnsupportedOperationException("not required yet");
    }
    
    @Hook(Pointcut.STORAGE_PARTITION_IDENTIFY_CREATE)
    public RequestPartitionId PartitionIdentifyCreate(ServletRequestDetails theRequestDetails) {
        String tenantId = theRequestDetails.getTenantId();
        logger.info("PartitionInterceptor create - setting partition to tennant ID: " + tenantId);
        return RequestPartitionId.fromPartitionName(tenantId);
    }

    @Hook(Pointcut.STORAGE_PARTITION_IDENTIFY_READ)
    public RequestPartitionId readPartition(ServletRequestDetails theRequestDetails) {
        logger.info("PartitionInterceptor - reading them all");
        return RequestPartitionId.allPartitions();
    }
}
