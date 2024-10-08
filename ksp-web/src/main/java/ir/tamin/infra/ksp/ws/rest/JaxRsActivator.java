/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ir.tamin.infra.ksp.ws.rest;

//import ir.tamin.framework.logging.rest.feature.FrameworkLoggingFeature;
import ir.tamin.framework.ws.rest.DynamicRESTServiceImpl;
import ir.tamin.framework.ws.rest.GeneralJaxRsActivator;
import ir.tamin.framework.ws.rest.filter.ArabicLetterInterceptor;
import ir.tamin.framework.ws.rest.filter.CorsFilter;
import ir.tamin.framework.ws.rest.filter.DynamicMatchingFilter;
import ir.tamin.framework.ws.rest.security.jwt.AnonymousAccessFeature;
import ir.tamin.framework.ws.rest.security.jwt.JwtDynamicFeature;
import ir.tamin.framework.ws.rest.security.jwt.JwtDynamicFeatureV2;
//import ir.tamin.framework.ws.rest.security.role.RoleMappingService;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;
import org.glassfish.jersey.server.wadl.WadlFeature;

import javax.ws.rs.ApplicationPath;


@ApplicationPath("/api")
public class JaxRsActivator extends GeneralJaxRsActivator {

    public JaxRsActivator() {
        packages("ir.tamin.infra.ksp");
        register(JspMvcFeature.class);
        register(MultiPartFeature.class);
    }
}
