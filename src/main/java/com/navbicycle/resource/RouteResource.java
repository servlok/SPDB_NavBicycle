package com.navbicycle.resource;

import com.google.common.collect.Lists;
import com.navbicycle.domain.*;
import com.navbicycle.veturilo.db.VeturiloStationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("route")
public class RouteResource {

    private static String PLACE_SQL_EXCEPTION_MESSAGE = "Problem with database connection";
    private VeturiloStationRepository veturiloStationRepository = new VeturiloStationRepository();

    @RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public ResponseEntity<?> get(@RequestParam double startLat, @RequestParam double startLng,
                                 @RequestParam double endLat, @RequestParam double endLng) {
        BeginEndRoute routeToFind = new BeginEndRoute(
                new Point(startLat, startLng),
                new Point(endLat, endLng)
        );

        return createStub(routeToFind);
    }

    private ResponseEntity<?> createStub(BeginEndRoute beginEndRoute) {
        Route route = new Route(beginEndRoute,
                new RoutePoints(
                        Lists.newArrayList(
                                new VeturiloStation.Builder()
                                        .point(beginEndRoute.getStart())
                                        .name("ul. Andersa - ul. Muranowska")
                                        .build(),
                                new VeturiloStation.Builder()
                                        .point(beginEndRoute.getStart())
                                        .name("al. Jana Pawła II - ul. Stawki")
                                        .build(),
                                new VeturiloStation.Builder()
                                        .point(beginEndRoute.getStart())
                                        .name("ul. Świętokrzyska - Sezam")
                                        .build()
                        ),
                        Lists.newArrayList(0.5, (double) 2)
                ));

        return new ResponseEntity<>(route, HttpStatus.OK);
    }

    private ApiError createServerError() {
        return new ApiError(500, PLACE_SQL_EXCEPTION_MESSAGE);
    }
}
