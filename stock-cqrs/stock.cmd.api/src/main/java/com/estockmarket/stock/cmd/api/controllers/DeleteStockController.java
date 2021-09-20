package com.estockmarket.stock.cmd.api.controllers;

import com.estockmarket.stock.cmd.api.commands.DeleteStockCommand;
import com.estockmarket.stock.core.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0/market/stock/command")
public class DeleteStockController {
    private final CommandGateway commandGateway;

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
            commandGateway.send(command);
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing request to delete stock with id - " + id;
            System.out.println(e.toString());
        }
    }
}
