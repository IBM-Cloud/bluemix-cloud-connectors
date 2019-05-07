package org.terrence.testapp.repositories;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.terrence.testapp.domain.Status;

@Component
public class StatusRepository extends CouchDbRepositorySupport<Status> {

    @Autowired
    public StatusRepository(CouchDbConnector connector) {
        super(Status.class, connector);
        initStandardDesignDocument();
    }

}