package org.masil.hello.axon.inventory.coreapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class Queries {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class findInventoryQuery{

        UUID inventoryId;

    }

    @Getter
    @NoArgsConstructor
    public static class findAllInventoryQuery{

    }
}
