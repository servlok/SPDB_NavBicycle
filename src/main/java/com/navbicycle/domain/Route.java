package com.navbicycle.domain;

public class Route extends BeginEndRoute {
    private RoutePoints routePoints;


    public Route(BeginEndRoute beginEndRoute, RoutePoints routePoints) {
        super(beginEndRoute.getStart(), beginEndRoute.getEnd());
        this.routePoints = routePoints;
    }

    public Route(Point start, Point end, RoutePoints routePoints) {
        super(start, end);
        this.routePoints = routePoints;
    }

    public RoutePoints getRoutePoints() {
        return routePoints;
    }

}
