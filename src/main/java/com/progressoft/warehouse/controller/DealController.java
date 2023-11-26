package com.progressoft.warehouse.controller;

import com.progressoft.warehouse.annotations.LogMethod;
import com.progressoft.warehouse.dto.DealDTO;
import com.progressoft.warehouse.service.DealService;
import com.progressoft.warehouse.util.WarehouseHealper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
/**
 *
 * @author  mukhtiar.ahmed
 * version 1.0
 */
@AllArgsConstructor
@RestController
@RequestMapping("/deals")
public class DealController {



    private final DealService dealService;

    @PostConstruct
    public void checkConfiguration() {
        WarehouseHealper.checkConfigNotNull(dealService, "dealService");
    }
    /**
     * Create the Warehouse.
     *
     * @param request the DealDTO.
     * @return the DealDTO
     */
    @PostMapping("/processDeal")
    @ApiResponse(description = "Create a Deal resource.")
    @LogMethod
    public void processDeal(@Parameter(name = "Deal request object", required = true) @Valid @RequestBody DealDTO request)  {

        dealService.processDeal(request.toModel());
    }


}
