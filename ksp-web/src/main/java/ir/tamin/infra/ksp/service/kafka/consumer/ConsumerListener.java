package ir.tamin.infra.ksp.service.kafka.consumer;

import ir.tamin.framework.domain.event.AfterEdit;
import ir.tamin.framework.domain.event.ResourceContext;

import javax.ejb.AfterBegin;
import java.util.Observer;
import javax.enterprise.event.Observes;

public class ConsumerListener {

    public void cancelChangeListAfterEdit( ) throws Exception {

    }
}
