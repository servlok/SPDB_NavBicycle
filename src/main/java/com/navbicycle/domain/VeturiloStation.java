package com.navbicycle.domain;

public class VeturiloStation extends Point {
    private final String name;
    private final int uid;

    private VeturiloStation(Builder builder) {
        super(builder.lat, builder.lng);
        this.name = builder.name;
        this.uid = builder.uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VeturiloStation that = (VeturiloStation) o;

        return uid == that.uid;

    }

    @Override
    public int hashCode() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public int getUid() {
        return uid;
    }

    @Override
    public String toString() {
        return "VeturiloStation{" +
                "lat=" + this.getLat() +
                ", lng=" + this.getLat() +
                ", name='" + name + '\'' +
                ", uid=" + uid +
                '}';
    }

    public static class Builder {
        double lat;
        double lng;
        String name;
        int uid;

        public Builder point(Point point) {
            this.lat = point.getLat();
            this.lng = point.getLng();
            return this;
        }

        public Builder lat(double lat) {
            this.lat = lat;
            return this;
        }

        public Builder lng(double lng) {
            this.lng = lng;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder uid(int uid) {
            this.uid = uid;
            return this;
        }

        public VeturiloStation build() {
            if (name == null)
                throw new IllegalStateException();

            return new VeturiloStation(this);
        }
    }
}
