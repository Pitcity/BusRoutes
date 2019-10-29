package com.itovp.BusStop.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itovp.BusStop.Entity.DirectionResponse;
import com.itovp.BusStop.Services.RouteService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
class BasicController {

	private RouteService routeService;

	BasicController(RouteService routeService) {
		this.routeService = routeService;
	}

	@Cacheable("dr")
	@GetMapping(path="/direct")
	public String checkRouteAvailability(@RequestParam(name = "dep_sid") Integer departureStationId, @RequestParam(name = "arr_sid") Integer arrivalStationId) throws JsonProcessingException {
		DirectionResponse dr = new DirectionResponse();
		dr.setDepartureStationId(departureStationId);
		dr.setArrivalStationId(arrivalStationId);
		dr.setDirectRouteExists(routeService.isDirectConnectionAvailable(departureStationId, arrivalStationId));
	return new ObjectMapper().writeValueAsString(dr);
	}

}
