package com.itovp.BusStop.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DirectionResponse {

	@JsonProperty(value = "dep_sid")
	private Integer departureStationId;

	@JsonProperty(value = "arr_sid")
	private Integer arrivalStationId;

	@JsonProperty(value = "direct_bus_route")
	private boolean isDirectRouteExists;

	public void setDepartureStationId(Integer departureStationId) {
		this.departureStationId = departureStationId;
	}

	public void setArrivalStationId(Integer arrivalStationId) {
		this.arrivalStationId = arrivalStationId;
	}

	public void setDirectRouteExists(boolean directRouteExists) {
		isDirectRouteExists = directRouteExists;
	}
}
