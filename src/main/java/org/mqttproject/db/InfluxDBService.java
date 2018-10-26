package org.mqttproject.db;

import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

@Service
public class InfluxDBService
{
	@Autowired
	private InfluxDBTemplate<Point> influxDBTemplate;

	public InfluxDBTemplate<Point> getTemplate()
	{
		return influxDBTemplate;
	}
}
