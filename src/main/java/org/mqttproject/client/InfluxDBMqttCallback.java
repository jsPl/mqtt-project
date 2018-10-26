package org.mqttproject.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.mqttproject.BeanUtil;
import org.mqttproject.db.InfluxDBService;

public class InfluxDBMqttCallback implements MqttCallback
{
	private static Logger logger = LogManager.getLogger("mqttFileLog");

	private InfluxDBService influxDBservice;

	public InfluxDBMqttCallback()
	{
		influxDBservice = BeanUtil.getBean(InfluxDBService.class);
	}

	@Override
	public void connectionLost(Throwable cause)
	{
		logger.info("connectionLost: " + cause);
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception
	{
		logger.info("messageArrived topic: " + topic + " qos: " + message.getQos() + " payload: " + new String(message.getPayload()));

		if (influxDBservice != null) {
			logger.info("influxDB name: " + influxDBservice.getTemplate().getDatabase());
		}
		else {
			logger.info("influxDBservice is null");
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token)
	{
		logger.info("deliveryComplete token: " + token);
	}
}
