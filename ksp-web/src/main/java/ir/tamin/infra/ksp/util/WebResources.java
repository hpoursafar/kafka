package ir.tamin.infra.ksp.util;

import ir.tamin.framework.cdi.CDIBeanFactory;
import ir.tamin.framework.cdi.util.*;
import ir.tamin.framework.core.persistence.ProcedureManager;
import ir.tamin.framework.core.util.Bundle;
import ir.tamin.framework.rest.httpclient.HttpClientJmxConfig;
import ir.tamin.framework.ws.rest.repository.DBFunctionRepository;
import ir.tamin.framework.ws.rest.repository.DBFunctionRepositoryImpl;
import ir.tamin.framework.ws.rest.repository.EntityRepository;
import ir.tamin.framework.ws.rest.repository.EntityRepositoryImpl;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 *
 * @author s_tayari
 *
 */
public class WebResources {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @Resource(lookup = "datasources/CentralizedPensionDS")
    private DataSource dataSource;

    @Produces
    public DataSource getDataSource() {
        return dataSource;
    }

    @Produces
    public EntityManager produceEntityManager() {
        return em;
    }

    @Inject
    @ConfigFile
    private Bundle webProperties;

    @Produces
    @WebProperties
    @ApplicationScoped
    public Bundle produceConfigFile() {
        return webProperties;
    }

    @Inject
    private CDIBeanFactory cdiBeanFactory;

    @Produces
    public ProcedureManager produceProcedureManager() {
        return new ProcedureManager(dataSource);
    }

    @Produces
    @ConfigFile
    @DevelopmentConfig
    @ApplicationScoped
    public Bundle produceDevAppProps() {
        return new Bundle(ResourceBundle.getBundle("dev-web"));
    }

    @Produces
    @ConfigFile
    @SnapshotConfig
    @ApplicationScoped
    public Bundle produceSnapshotProps() {
        return new Bundle(ResourceBundle.getBundle("snapshot-web"));
    }

    @Produces
    @ConfigFile
    @StageConfig
    @ApplicationScoped
    public Bundle produceStageProps() {
        return new Bundle(ResourceBundle.getBundle("stage-web"));
    }

    @Produces
    @ConfigFile
    @TestConfig
    @ApplicationScoped
    public Bundle produceTestProps() {
        return new Bundle(ResourceBundle.getBundle("test-web"));
    }

    @Produces
    @ConfigFile
    @ProductionConfig
    @ApplicationScoped
    public Bundle produceProdAppProps() {
        return new Bundle(ResourceBundle.getBundle("prod-web"));
    }

    @Produces
    @MessageBundle
    @Named("WebMessages")
    @ApplicationScoped
    public Bundle produceMessageBundle() {
        return new Bundle(

                "messages/WebMessages", new Locale(
                webProperties.getProperty("application.locale")));
    }

    @Produces
    @ApplicationScoped
    @Named("EntityRepository")
    public EntityRepository produceEntityRepository() {
        EntityRepository entityRepository = new EntityRepositoryImpl("models", em, cdiBeanFactory);
        return entityRepository;
    }

    @Produces
    @ApplicationScoped
    @Named("FunctionRepository")
    public DBFunctionRepository produceFunctionRepository() {
        DBFunctionRepository dbFunctionRepository = new DBFunctionRepositoryImpl("functions", webProperties.getProperty("package.root") + "." + webProperties.getProperty("package.functions"), dataSource, cdiBeanFactory);
        return dbFunctionRepository;
    }

    @Produces
    public Logger produceLogger() {
        return Logger.getAnonymousLogger();
    }

    @Produces
    @ApplicationScoped
    public HttpClientJmxConfig produceJmxConfig() {
        return new HttpClientJmxConfig(webProperties.getProperty("package.root"));
    }
}
