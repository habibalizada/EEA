package com.estockmarket.stock.cmd.api.controllers;

import com.estockmarket.stock.cmd.api.commands.UpdateStockCommand;
import com.estockmarket.stock.core.dto.BaseResponse;
import com.estockmarket.stock.core.models.Stock;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1.0/market/stock")
public class UpdateStockController {
    private final CommandGateway commandGateway;

    @Autowired
    public UpdateStockController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping("/update")
    public ResponseEntity<BaseResponse> updateStock(@Valid @RequestBody UpdateStockCommand command) {
        try {
            commandGateway.send(command);
            return new ResponseEntity<>(new BaseResponse("Stock successfully updated!"), HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing request to update stock id - " + command.getId();
            System.out.println(e.toString());

            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
