/**
 * This software is the confidential and proprietary information
 * of Ontario Ministry of Health ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into
 * with Ontario Ministry of Health.
 *
 * Copyright (c) 2021 Ontario Ministry of Health. All Rights Reserved.
 */
package ca.on.health.smilecdr.interceptor;

import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.jpa.api.dao.DaoRegistry;
import ca.uhn.fhir.rest.server.interceptor.StaticCapabilityStatementInterceptor;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This interceptor overrides the built-in automatic CpaabilityStatement
 * generator in Smile CDR and instead uses a static resource when a client
 * invokes the "capabilities" operation (i.e. /metadata)
 *
 * @author Joel Pettis (joel.pettis@ontario.ca)
 * @copyright (C) Ontario Ministry of Health 2021.
 */
@Interceptor
public class CapabilityStatementInterceptor
        extends StaticCapabilityStatementInterceptor
        implements SadieInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(CapabilityStatementInterceptor.class);

    @PostConstruct
    protected void init() {
        logger.info("CAPABILITY STATEMENT INTERCEPTOR");

        try {
            setCapabilityStatementResource("static-capabilitystatement.json");
        } catch (Exception e) {
            String message = "critical error:capability statement interceptor error";
            logger.error(message, e);
        }
    }
    
    @Override
    public DaoRegistry getDaoRegistry() {
        throw new UnsupportedOperationException("not required yet");
    }    
}
