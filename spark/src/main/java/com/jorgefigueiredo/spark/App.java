package com.jorgefigueiredo.spark;

import com.jorgefigueiredo.spark.entity.User;
import com.jorgefigueiredo.spark.repository.IUserRepository;
import com.jorgefigueiredo.spark.repository.UserRepository;
import com.thoughtworks.xstream.XStream;

import static spark.Spark.get;
import static spark.Spark.post;

public class App 
{
    public static void main( String[] args )
    {


    	get("/x", (req, res) -> {

            res.body("GET");

            res.status(200);

            return "--GET--";
        });

        post("/x", (req, res) -> {

            res.body("POST");

            res.status(200);

            res.type("application/xml");

            IUserRepository userRepository = new UserRepository();

            XStream xStream = new XStream();
            xStream.alias("user", User.class);
            xStream.alias("users", User[].class);
            xStream.aliasField("uuid", User.class, "id");

            String xml = xStream.toXML(userRepository.getAll());



            return xml;
        });
    }
}
