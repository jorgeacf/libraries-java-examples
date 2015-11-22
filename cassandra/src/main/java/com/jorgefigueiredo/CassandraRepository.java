package com.jorgefigueiredo;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class CassandraRepository {

    private final String clusterEndPoint;
    private final Cluster cluster;

    private final String keystore;
    private final Session session;

    public CassandraRepository(String clusterEndPoint, String keystore) {

        this.clusterEndPoint = clusterEndPoint;
        this.cluster = Cluster.builder().addContactPoint(clusterEndPoint).build();

        this.keystore = keystore;
        this.session = this.cluster.connect(this.keystore);
    }

    protected Session getSession() {
        return session;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

        if(cluster != null && !cluster.isClosed()) {
            cluster.close();
        }

    }
}
