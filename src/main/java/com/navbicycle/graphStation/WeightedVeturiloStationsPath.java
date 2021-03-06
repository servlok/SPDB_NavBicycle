package com.navbicycle.graphStation;

import com.navbicycle.domain.VeturiloStation;
import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * Created by servlok on 18.01.16.
 */
public class WeightedVeturiloStationsPath extends DefaultWeightedEdge {

    private double additionalCost;

    public double getAdditionalCost() {
        return additionalCost;
    }

    public void setAdditionalCost(double additionalCost) {
        this.additionalCost = additionalCost;
    }

    @Override
    public VeturiloStation getSource() {
        return (VeturiloStation) super.getSource();
    }

    @Override
    public VeturiloStation getTarget() {
        return (VeturiloStation) super.getTarget();
    }

    @Override
    public double getWeight() {
        return super.getWeight();
    }

    public double getCost() {
        return this.getWeight() - additionalCost;
    }
}
