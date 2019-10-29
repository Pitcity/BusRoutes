package com.itovp.BusStop.Services;

public interface RouteService {

	boolean isDirectConnectionAvailable(Integer departureStationId, Integer arrivalStationId);
}
