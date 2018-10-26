package org.mqttproject.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
public class Subscriber
{
	private static Logger logger = LogManager.getLogger(Subscriber.class);

	private @Getter MqttClient client;
	private MqttConnectOptions connectOptions;
	private String brokerURI = "tcp://broker.mqttdashboard.com:1883";
	private String clientId;

	public Subscriber() throws MqttException
	{
		MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence("tmp");

		clientId = MqttClient.generateClientId();
		client = new MqttClient(brokerURI, clientId, dataStore);

		logger.info("Created MqttClient: " + client.getClientId());
	}

	public Subscriber connect(MqttCallback callback) throws MqttSecurityException, MqttException
	{
		logger.info("Connecting to: " + client.getServerURI() + " using callback " + callback.getClass().getName());
		client.setCallback(callback);
		client.connect();

		return this;
	}
}
