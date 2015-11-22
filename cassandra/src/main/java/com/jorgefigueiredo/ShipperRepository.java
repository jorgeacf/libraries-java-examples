package com.jorgefigueiredo;

import com.datastax.driver.core.*;
import com.datastax.driver.core.exceptions.QueryValidationException;

public class ShipperRepository extends CassandraRepository implements IShipperRepository {

    public ShipperRepository(String clusterEndPoint, String keystore) {
        super(clusterEndPoint, keystore);
    }

    public void create(Shipper shipper) {

        if(shipper == null) { throw new IllegalArgumentException(); }

        Session session = super.getSession();

        String query = "INSERT INTO Shippers (shipperid, companyname, phone) VALUES (?, ?, ?)";

        PreparedStatement ps = session.prepare(query);

        BoundStatement bs = ps.bind(shipper.getShipperId(), shipper.getCompanyName(), shipper.getPhone());

        try {
            session.execute(bs);
        }
        catch (QueryValidationException ex) {
            // LOG
        }
    }

    public Shipper read(Integer shipperId) {

        if(shipperId == null) { throw new IllegalArgumentException(); }

        Session session = super.getSession();

        String query = "SELECT shipperid, companyname, phone FROM Shippers";

        ResultSet rs = session.execute(query);

        for (Row row : rs) {

        }

        return null;
    }

    public boolean update(Shipper shipper) {
        return false;
    }

    public boolean delete(Integer shipperId) {
        return false;
    }
}
