package me.bulkanov.onlinestoreofsocks.dto;

import me.bulkanov.onlinestoreofsocks.model.SocksColor;
import me.bulkanov.onlinestoreofsocks.model.SocksSize;

public class SocksRequest {
    private SocksColor socksColor;
    private SocksSize socksSize;
    private int compositionOfSocks;
    private int quantity;

    public SocksColor getSocksColor() {
        return socksColor;
    }

    public void setSocksColor(SocksColor socksColor) {
        this.socksColor = socksColor;
    }

    public SocksSize getSocksSize() {
        return socksSize;
    }

    public void setSocksSize(SocksSize socksSize) {
        this.socksSize = socksSize;
    }

    public int getCompositionOfSocks() {
        return compositionOfSocks;
    }

    public void setCompositionOfSocks(int compositionOfSocks) {
        this.compositionOfSocks = compositionOfSocks;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
