/**
 * V3ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.crescendo.smartschool.services.Webservices.V3;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class V3ServiceLocator extends org.apache.axis.client.Service implements V3Service {

    private PropertiesConfiguration conf = new PropertiesConfiguration("config.properties");
    private final String BASEURL = conf.getProperty("URLSS").toString();

    public V3ServiceLocator() throws ConfigurationException {
    }


    public V3ServiceLocator(org.apache.axis.EngineConfiguration config) throws ConfigurationException {
        super(config);
    }

    public V3ServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException, ConfigurationException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for V3Port
    private String V3Port_address = BASEURL;

    public String getV3PortAddress() {
        return V3Port_address;
    }

    // The WSDD service name defaults to the port name.
    private String V3PortWSDDServiceName = "V3Port";

    public String getV3PortWSDDServiceName() {
        return V3PortWSDDServiceName;
    }

    public void setV3PortWSDDServiceName(String name) {
        V3PortWSDDServiceName = name;
    }

    public V3Port getV3Port() throws javax.xml.rpc.ServiceException, ConfigurationException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(V3Port_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getV3Port(endpoint);
    }

    public V3Port getV3Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException, ConfigurationException {
        try {
            V3BindingStub _stub = new V3BindingStub(portAddress, this);
            _stub.setPortName(getV3PortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setV3PortEndpointAddress(String address) {
        V3Port_address = address;
    }

    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (V3Port.class.isAssignableFrom(serviceEndpointInterface)) {
                V3BindingStub _stub = new V3BindingStub(new java.net.URL(V3Port_address), this);
                _stub.setPortName(getV3PortWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("V3Port".equals(inputPortName)) {
            try {
                return getV3Port();
            } catch (ConfigurationException e) {
                e.printStackTrace();
                return getPort(serviceEndpointInterface);
            }
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("BASEURL", "V3Service");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("BASEURL", "V3Port"));
        }
        return ports.iterator();
    }

    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {

        if ("V3Port".equals(portName)) {
            setV3PortEndpointAddress(address);
        }
        else
        { // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
