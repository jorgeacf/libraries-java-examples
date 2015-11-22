package com.jorgefigueiredo;

public interface IShipperRepository {

    void create(Shipper shipper);
    Shipper read(Integer shipperId);
    boolean update(Shipper shipper);
    boolean delete(Integer shipperId);

}
