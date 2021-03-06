package org.masil.hello.axon.inventory.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.masil.hello.axon.inventory.coreapi.Commands;
import org.masil.hello.axon.inventory.coreapi.Events;

import java.util.UUID;

import static org.masil.hello.axon.inventory.coreapi.Commands.*;

@Aggregate
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @AggregateIdentifier
    private UUID inventoryId;

    private int quantity;

    @CommandHandler
    public Inventory(CreateInventoryCommand command) {
        UUID inventoryId = UUID.randomUUID();
        AggregateLifecycle.apply(new Events.CreateInventoryEvent(inventoryId));
    }

    @EventSourcingHandler
    public void on(Events.CreateInventoryEvent event){
        inventoryId = event.getInventoryId();
    }

    @CommandHandler
    public void handle(IncreaseInventoryCommand command){

        AggregateLifecycle.apply(new Events.InventoryIncreaseEvent(
                inventoryId, command.getQuantity()
        ));
    }

    @EventSourcingHandler
    public void on(Events.InventoryIncreaseEvent event){
        this.inventoryId = event.getInventoryId();
    }


    @CommandHandler
    public void handle(DecreaseInventoryCommand command){
        AggregateLifecycle.apply(new Events.InventoryDecreaseEvent(
                inventoryId, command.getQuantity()
        ));
    }

    @EventSourcingHandler
    public void on(Events.InventoryDecreaseEvent event) {
        this.quantity =  event.getQuantity();
    }
}
