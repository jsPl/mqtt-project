package org.mqttproject;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.mqttproject.client.InfluxDBMqttCallback;
import org.mqttproject.client.Subscriber;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartSubscriber
{
	private static Logger logger = LogManager.getLogger(StartSubscriber.class);

	@EventListener(ApplicationReadyEvent.class)
	public void EventListenerExecute()
	{
		try {
			Subscriber sub = new Subscriber();
			List<String> topics = new ArrayList<>(); //"AU_2018/PLC_TwinSAFE/TcIotCommunicator/Json/Tx/Data

			topics.add("BCDS/XDK/single/20:19:AB:F4:04:55/#"); //out/stream

			sub.connect(new InfluxDBMqttCallback());
			sub.client().subscribe(topics.toArray(new String[topics.size()]));
		}
		catch (MqttException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
}
