package com.estockmarket.stock.cmd.api.controllers;

import com.estockmarket.stock.cmd.api.commands.DeleteStockCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0/market/stock/command")
public class DeleteStockController {
    private final CommandGateway commandGateway;

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteStockController.class);

    @Autowired
    public DeleteStockController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStockById(@PathVariable(value = "id") String id) {
        try {
            var command = DeleteStockCommand.builder()
                    .id(id)
                    .build();
            LOGGER.info("Stock deleted id: {}", id);
            commandGateway.send(command);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("Some thing went wrong to delete Stock id: {}", id);
        }
    }
}
