package org.masil.hello.axon.inventory.coreapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

public class Events {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateInventoryEvent
    {
        private UUID inventoryId;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindInventoryEvent
    {
        private UUID inventoryId;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InventoryIncreaseEvent
    {

        private UUID inventoryId;
        private int quantity;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InventoryDecreaseEvent
    {

        private UUID inventoryId;
        private int quantity;

    }

    @Getter
    @NoArgsConstructor
    public static class InventoryDeleteEvent
    {

        private UUID inventoryId;

    }


}
