package com.example;

import org.apache.catalina.connector.Response;
import org.apache.catalina.connector.ResponseFacade;

import java.io.Serializable;

/**
 * Created by amarendra on 19/04/17.
 */
public class HttpServletResSerial extends ResponseFacade implements Serializable{

    public HttpServletResSerial(Response response) {
        super(response);
    }

}
