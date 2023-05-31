package com.ufcity.semantic;

import com.ufcity.semantic.semantic.SemanticImpl;
import ufcitycore.config.ConfigInterface;

import static ufcitycore.mqtt.ConnectionData.*;
import static ufcitycore.mqtt.ConnectionData.setCloudPort;

public class Config implements ConfigInterface {
    @Override
    public void configDataBase(String host, String port, String username, String password) {

    }

    @Override
    public void configFogMqttBroker(String host, String port) {
        setFogHost(host);
        setFogPort(port);
    }

    @Override
    public void configCloudMqttBroker(String host, String port) {
        setCloudHost(host);
        setCloudPort(port);
    }

    @Override
    public void configSemantic(String host, String port, String username, String password) {
        System.out.println(">> Connecting semantic server! Semantic server address: "+host+":"+port);
        Main.semantic = new SemanticImpl(host, port, username, password);
    }

    @Override
    public void configGroupData(String method, long time, long size) {

    }

    @Override
    public void configAggregateData(String method) {

    }

    @Override
    public void configMissingData(String method) {

    }

    @Override
    public void configRemoveOutlier(String method, double threshold, double lowerPercentile, double upperPercentile) {

    }
}
