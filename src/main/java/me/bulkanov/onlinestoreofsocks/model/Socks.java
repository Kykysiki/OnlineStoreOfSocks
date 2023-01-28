package me.bulkanov.onlinestoreofsocks.model;

import java.util.Objects;

public class Socks {
    private final SocksColor socksColor;
    private final SocksSize socksSize;
    private final int compositionOfSocks;

    public Socks(SocksColor socksColor, SocksSize socksSize, int compositionOfSocks) {
        this.socksColor = socksColor;
        this.socksSize = socksSize;
        this.compositionOfSocks = compositionOfSocks;
    }

    public SocksColor getSocksColor() {
        return socksColor;
    }

    public SocksSize getSocksSize() {
        return socksSize;
    }

    public int getCompositionOfSocks() {
        return compositionOfSocks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socks socks = (Socks) o;
        return compositionOfSocks == socks.compositionOfSocks && socksColor == socks.socksColor && socksSize == socks.socksSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(socksColor, socksSize, compositionOfSocks);
    }
}
