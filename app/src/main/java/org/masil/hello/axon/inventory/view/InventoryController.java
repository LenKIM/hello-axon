package org.masil.hello.axon.inventory.view;

import lombok.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.masil.hello.axon.inventory.coreapi.Commands;
import org.masil.hello.axon.inventory.query.FindAllInventoryQuery;
import org.masil.hello.axon.inventory.query.FindInventoryQuery;
import org.masil.hello.axon.inventory.query.InventoryView;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping
    public void init() {
        commandGateway.send(new Commands.CreateInventoryCommand(UUID.randomUUID()));
    }

    enum ModifyType {
        INC, DESC
    }

    @PutMapping("/{inventoryId}")
    public void modifyInventory(@PathVariable UUID inventoryId, @RequestBody InventoryModify modify) {
        if (modify.type == ModifyType.INC) {
            commandGateway.send(new Commands.IncreaseInventoryCommand(inventoryId, modify.quantity));
        } else {
            commandGateway.send(new Commands.DecreaseInventoryCommand(inventoryId, modify.quantity));
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    private static class InventoryModify {
        private ModifyType type;
        private int quantity;
    }

    @GetMapping("/{inventoryId}")
    public CompletableFuture<InventoryView> find(@PathVariable UUID inventoryId) {
        return queryGateway.query(new FindInventoryQuery(inventoryId), ResponseTypes.instanceOf(InventoryView.class));
    }

    @GetMapping()
    public CompletableFuture<List<InventoryView>> findAll() {
        return queryGateway.query(new FindAllInventoryQuery(), ResponseTypes.multipleInstancesOf(InventoryView.class));
    }
}
