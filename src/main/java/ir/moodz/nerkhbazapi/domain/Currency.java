package ir.moodz.nerkhbazapi.domain;

import java.time.Instant;

public record Currency(String symbol, String price, Instant createAt) {}
