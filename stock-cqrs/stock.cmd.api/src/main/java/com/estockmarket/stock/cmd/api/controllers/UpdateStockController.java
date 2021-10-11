package com.estockmarket.stock.cmd.api.controllers;

import com.estockmarket.stock.cmd.api.commands.UpdateStockCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1.0/market/stock/command")
public class UpdateStockController {
    private final CommandGateway commandGateway;

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateStockController.class);

    @Autowired
    public UpdateStockController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping("/update")
    public void updateStock(@Valid @RequestBody UpdateStockCommand command) {
        try {
            LOGGER.info("Stock with id: {} updated", command.getId());
            commandGateway.send(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
