package com.jorgefigueiredo;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class App 
{
    public static void main( String[] args ) {


        Cluster cluster = null;
        Session session = null;

        try {

            cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
            session = cluster.connect("Northwind");

            ResultSet results = session.execute("SELECT * FROM Shippers;");

            for (Row row : results) {

                System.out.println(String.format("%d-%s", row.getInt("shipperid"), row.getString("companyname")));

            }
        }
        finally {
            if (cluster != null) {
                cluster.close();
            }
        }

    }
}
