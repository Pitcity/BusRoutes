package com.itovp.BusStop.Services.Impl;

import com.itovp.BusStop.Exeptions.IncorrectFileFormatException;
import com.itovp.BusStop.Services.RouteService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RouteServiceImpl implements RouteService {

	@Value("${ROUTE_FILE_PATH}")
	private String routeFilePath;
	private static final String regexCheckPattern = "^\\d{1,}( [\\d]{1,}){0,} station1( [\\d]{1,}){0,} station2( [\\d]{1,}){0,}$";
	private static final String KEY_FOR_STATION1 = "station1";
	private static final String KEY_FOR_STATION2 = "station2";

	@Override
	public boolean isDirectConnectionAvailable(Integer departureStationId, Integer arrivalStationId) {
		Path filePath = Paths.get(routeFilePath);
		try (Scanner scanner = new Scanner(filePath)) {
			while (scanner.hasNextLine()) {
				String schema = scanner.nextLine();
				if(checkIfRouteMatches(schema, departureStationId.toString(), arrivalStationId.toString())) {
					return true;
				}
			}
		} catch (IOException e) {
			throw new IncorrectFileFormatException();
			//handlers if needed, writing to logs, etc
		}
		return false;
	}

	private boolean checkIfRouteMatches(String schema, String departureStationId, String arrivalStationId) {
		Pattern patternDirect = Pattern.compile(regexCheckPattern.replace(KEY_FOR_STATION1, departureStationId).replace(KEY_FOR_STATION2, arrivalStationId));
		Pattern patternBack = Pattern.compile(regexCheckPattern.replace(KEY_FOR_STATION1, arrivalStationId).replace(KEY_FOR_STATION2, departureStationId));
		Matcher matcherDirect = patternDirect.matcher(schema);
		Matcher matcherBack = patternBack.matcher(schema);
		return matcherDirect.matches() || matcherBack.matches();
	}



	// ideally i'd use pattern on whole file like that, but need more time on investigation
	//				boolean one = scanner.hasNext(Pattern.compile(regexCheckPattern.replace(KEY_FOR_STATION1, departureStationId.toString()).replace(KEY_FOR_STATION2, arrivalStationId.toString())));
	//				boolean two =  scanner.hasNext(Pattern.compile(regexCheckPattern.replace(KEY_FOR_STATION1, arrivalStationId.toString()).replace(KEY_FOR_STATION2, departureStationId.toString())));
	//				if(one || two){
	//					return true;
	//				}

}
