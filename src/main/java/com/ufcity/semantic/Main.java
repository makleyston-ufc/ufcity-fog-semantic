package com.ufcity.semantic;

import com.google.gson.Gson;
import com.ufcity.semantic.semantic.Semantic;
import org.eclipse.paho.client.mqttv3.MqttException;
import ufcitycore.models.Device;
import ufcitycore.models.Resource;
import ufcitycore.mqtt.ConnectionConfig;
import ufcitycore.mqtt.Subscribe;

import java.io.IOException;

import static ufcitycore.mqtt.ConnectionData.*;

public class Main {

    static Semantic semantic;
    static Gson gson = new Gson();
    static final String version = "0.1";

    public static void main(String[] args) throws IOException, MqttException {
        if (args.length == 0)
            if (Menu.check(Menu.ReaderConfig()) != 0) return;

        /*  Initializing the MQTT Broker for edge communication. */
        System.out.println("### MQTT Broker ###");
        ConnectionConfig connectionConfigSubEdge = new ConnectionConfig(INNER_HOST, INNER_PORT);
        connectionConfigSubEdge.setTopics(getEdgeSubscribeTopics());
        Subscribe subscribeEdge = new Subscribe(connectionConfigSubEdge);
        subscribeEdge.subscribe((topic, message) -> {
//            System.out.println("## Received message from Edge Computing: ");
//            System.out.println("## Topic: "+topic+", Message: "+message);
            String[] topicSep = topic.split("/");
            String firstLevelTopic = topicSep[0];
            switch (firstLevelTopic) {
                case EDGE_DEVICE_SUBSCRIBE ->
                    /* device/[uuid_device] -> json */
                        newDevice(topicSep[1], message);
                case EDGE_REGISTERED_RESOURCES_SUBSCRIBE ->
                    /* Topic: registered_resource/[uuid_device] -> json */
                        registeredResource(topicSep[1], message);
                case EDGE_REMOVED_RESOURCES_SUBSCRIBE ->
                    /* Topic: removed_resource/[uuid_device] */
                        removedResource(topicSep[1], message);
                case EDGE_RESOURCES_DATA_SUBSCRIBE ->
                    /* Topic: resource_data/[uuid_device]/[uuid_resource] */
                        receivedResourceData(topicSep[1], message);
            }
        });
    }

    private static void receivedResourceData(String uuid_device, String message) {
        System.out.println(">> Received resource data.");
        Resource resource = gson.fromJson(message, Resource.class);
        /* Semantic annotation and save entity in FusekiJena */
        semantic.createSemantic(resource);
        //Remove if exist
        semantic.removeResource(uuid_device, resource);
        semantic.saveResource(uuid_device, resource);
    }

    private static void removedResource(String uuid_device, String uuid_resource) {
        System.out.println(">> Removing the Resource on Fuseki server.");
        /* Semantic annotation and save entity in FusekiJena */
        semantic.removeResourceByUUID(uuid_device, uuid_resource);
    }

    private static void registeredResource(String uuid_device, String message) {
        System.out.println(">> Registering the Resource on Fuseki server.");
        Resource resource = gson.fromJson(message, Resource.class);
        /* Semantic annotation and save entity in FusekiJena */
        semantic.createSemantic(resource);
        //Remove if exist
        semantic.removeResource(uuid_device, resource);
        semantic.saveResource(uuid_device, resource);
    }

    private static void newDevice(String uuid_device, String message) {
        System.out.println(">> Registering the Device on Fuseki server.");
        Device device = gson.fromJson(message, Device.class);
        /* Semantic annotation and save entity in FusekiJena */
        semantic.createSemantic(device);
        //Remove if exist
        semantic.removeDevice(device);
        semantic.saveDevice(device);
    }
}