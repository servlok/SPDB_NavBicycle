package com.navbicycle.resource;

import com.google.common.collect.Lists;
import com.navbicycle.domain.*;
import com.navbicycle.service.RoutePointsFinder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;


@RestController
@RequestMapping("route")
public class RouteResource {

    private static String SQL_EXCEPTION_MESSAGE = "Problem with database connection Reason: ";
    private static String SERVER_EXCEPTION_MESSAGE = "Problem with illegal server state ";
    private RoutePointsFinder routePointsFinder = new RoutePointsFinder();

    @RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public ResponseEntity<?> get(@RequestParam double startLat, @RequestParam double startLng,
                                 @RequestParam double endLat, @RequestParam double endLng) {
        Point startingPoint = new Point(startLat, startLng);
        Point endingPoint = new Point(endLat, endLng);

        RoutePoints routePoints;
        try {
            routePoints = routePointsFinder.findRouteBetweenPoint(startingPoint, endingPoint);
        } catch (SQLException e) {
            return new ResponseEntity<>(new ApiError(500, SQL_EXCEPTION_MESSAGE + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IllegalStateException ex) {
            return new ResponseEntity<>(new ApiError(500, SERVER_EXCEPTION_MESSAGE + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        BeginEndRoute routeToFind = new BeginEndRoute(
                startingPoint,
                endingPoint
        );

        return new ResponseEntity<>(new Route(routeToFind, routePoints), HttpStatus.OK);

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

}
