package me.zeld.emperia.reign.service;

import me.zeld.emperia.reign.util.type.ResourceType;

public class Offer {
    public static int ID = 0;

    private int offerId;
    private ResourceType offerResource;
    private ResourceType requestResource;


    public Offer(ResourceType offerResource, ResourceType requestResource) {

        this.offerId = ID++;

        this.offerResource = offerResource;
        this.requestResource = requestResource;
    }
}
