package me.bulkanov.onlinestoreofsocks.service;

import me.bulkanov.onlinestoreofsocks.dto.SocksRequest;
import me.bulkanov.onlinestoreofsocks.exception.EnoughSocksException;
import me.bulkanov.onlinestoreofsocks.exception.UnsuitableSocksException;
import me.bulkanov.onlinestoreofsocks.model.Socks;
import me.bulkanov.onlinestoreofsocks.model.SocksColor;
import me.bulkanov.onlinestoreofsocks.model.SocksSize;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class SocksService {
    private final Map<Socks, Integer> sock = new HashMap<>();

    // Регистрирует приход товара на склад.
    public void addSocks(SocksRequest socksRequest) {
        validateRequest(socksRequest);
        Socks socks = mapToSocks(socksRequest);
        if (sock.containsKey(socks)) {
            sock.put(socks, sock.get(socks) + socksRequest.getQuantity());
        } else {
            sock.put(socks, socksRequest.getQuantity());
        }
    }

    // Регистрирует отпуск носков со склада.
    public void removeSocks(SocksRequest socksRequest) {
        decreaseSocksQuanty(socksRequest);
    }

    //Общее количество носков на складе по запросу.
    public int getSocksQuantity(SocksColor socksColor, SocksSize socksSize, Integer cottonMin, Integer cottonMax) {
        int total = 0;
        for (Map.Entry<Socks, Integer> entry : sock.entrySet()) {
            if (socksColor != null && !entry.getKey().getSocksColor().equals(socksColor)) {
                continue;
            }
            if (socksSize != null && !entry.getKey().getSocksSize().equals(socksSize)) {
                continue;
            }
            if (cottonMin != null && entry.getKey().getCompositionOfSocks() < cottonMin) {
                continue;
            }
            if (cottonMax != null && entry.getKey().getCompositionOfSocks() > cottonMax) {
                continue;
            }
            total += entry.getValue();
        }
        return total;
    }

    // Проверка.
    private void validateRequest(SocksRequest socksRequest) {
        if (socksRequest.getSocksColor() == null || socksRequest.getSocksSize() == null) {
            throw new UnsuitableSocksException("Все поля должны быть заполнены.");
        }
        if (socksRequest.getCompositionOfSocks() < 0 || socksRequest.getCompositionOfSocks() > 100) {
            throw new UnsuitableSocksException("Процент содержания хлопка в составе должен быть от 0 до 100.");
        }
        if (socksRequest.getQuantity() <= 0) {
            throw new UnsuitableSocksException("Количество носков должно быть больше 0.");
        }
    }

    private Socks mapToSocks(SocksRequest socksRequest) {
        return new Socks(socksRequest.getSocksColor(), socksRequest.getSocksSize(), socksRequest.getCompositionOfSocks());
    }

    public void removeDefectiveSocks(SocksRequest socksRequest) {
        decreaseSocksQuanty(socksRequest);
    }

    private void decreaseSocksQuanty(SocksRequest socksRequest) {
        validateRequest(socksRequest);
        Socks socks = mapToSocks(socksRequest);
        int socksQuantity = sock.getOrDefault(socks, 0);
        if (socksQuantity >= socksRequest.getQuantity()) {
            sock.put(socks, socksQuantity - socksRequest.getQuantity());
        } else {
            throw new EnoughSocksException("Нет носков для отпуска со склада.");
        }
    }
}
