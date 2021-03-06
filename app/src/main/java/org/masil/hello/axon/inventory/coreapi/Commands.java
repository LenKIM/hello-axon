package org.masil.hello.axon.inventory.coreapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

public class Commands {

    @Getter
    @AllArgsConstructor
    public static class CreateInventoryCommand {

        @TargetAggregateIdentifier
        private final UUID inventoryId;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IncreaseInventoryCommand {

        @TargetAggregateIdentifier
        private UUID id;
        private int quantity;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DecreaseInventoryCommand {

        @TargetAggregateIdentifier
        private UUID id;
        private int quantity;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteInventoryCommand {

        @TargetAggregateIdentifier
        private UUID id;

    }


}
