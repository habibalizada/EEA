package com.estockmarket.stock.cmd.api.controllers;

import com.estockmarket.stock.cmd.api.commands.CreateStockCommand;
import com.estockmarket.stock.cmd.api.dto.CreateStockResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1.0/market/stock")
public class CreateStockController {
    private final CommandGateway commandGateway;

    @Autowired
    public CreateStockController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/add/{companycode}")
    public ResponseEntity<CreateStockResponse> addStock(@Valid @RequestBody CreateStockCommand command, @PathVariable String companycode) {
        var id = UUID.randomUUID().toString();
        command.setId(id);
        command.setCompanyCode(companycode);
        try {
            commandGateway.send(command);
            return new ResponseEntity<>(new CreateStockResponse(companycode, "successfully added stock!"), HttpStatus.CREATED);

        } catch (Exception e) {
            var safeErrorMessage = "Error while processing request to add a stock for company code - " + companycode;
            System.out.println(e.toString());

            return new ResponseEntity<>(new CreateStockResponse(companycode, safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
