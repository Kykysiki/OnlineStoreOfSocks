package me.bulkanov.onlinestoreofsocks.controller;

import me.bulkanov.onlinestoreofsocks.dto.SocksRequest;
import me.bulkanov.onlinestoreofsocks.model.SocksColor;
import me.bulkanov.onlinestoreofsocks.model.SocksSize;
import me.bulkanov.onlinestoreofsocks.service.SocksService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/socks")
public class SocksController {
    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping
    public void addSocks(@RequestBody SocksRequest socksRequest) {
        socksService.addSocks(socksRequest);
    }

    @PutMapping
    public void removeSocks(@RequestBody SocksRequest socksRequest) {
        socksService.removeSocks(socksRequest);
    }

    @GetMapping
    public int getSocksQuantity(@RequestParam(required = false, name = "color") SocksColor socksColor,
                                @RequestParam(required = false, name = "size") SocksSize socksSize,
                                @RequestParam(required = false, name = "cottonMin") Integer cottonMin,
                                @RequestParam(required = false, name = "cottonMax") Integer cottonMax) {
        return socksService.getSocksQuantity(socksColor, socksSize, cottonMin, cottonMax);
    }
}
