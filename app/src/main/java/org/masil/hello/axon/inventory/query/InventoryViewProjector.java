package org.masil.hello.axon.inventory.query;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.masil.hello.axon.inventory.coreapi.Events;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Getter
@Component
public class InventoryViewProjector {

    private final InventoryViewRepository repository;

    @EventHandler
    public void on(Events.CreateInventoryEvent event) {
        InventoryView view = new InventoryView(event.getInventoryId(), 0);
        repository.save(view);
    }

    @EventHandler
    public void on(Events.InventoryIncreaseEvent event) {
        InventoryView inventoryView = repository.findById(event.getInventoryId()).orElseThrow(IllegalArgumentException::new);
        inventoryView.setQuantity(inventoryView.getQuantity() + event.getQuantity());
    }

    @EventHandler
    public void on(Events.InventoryDecreaseEvent event) {
        InventoryView inventoryView = repository.findById(event.getInventoryId()).orElseThrow(IllegalArgumentException::new);
        inventoryView.setQuantity(inventoryView.getQuantity() - event.getQuantity());
    }

    @QueryHandler
    public InventoryView handle(FindInventoryQuery query) {
        return repository.findById(query.getInventoryId()).orElse(null);
    }

    @QueryHandler
    public List<InventoryView> handle(FindAllInventoryQuery query) {
        return repository.findAll();
    }

}
