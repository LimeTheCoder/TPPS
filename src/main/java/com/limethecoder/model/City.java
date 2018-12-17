package com.limethecoder.model;

import lombok.Getter;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Getter
public class City {
    private static final int INITIAL_COINS = 1_000_000;
    private static final int DEFAULT_DENOMINATOR = 1000;

    private Position position;
    private Country country;

    private Map<Country,Integer> currentBalance;
    private Map<Country,Integer> incomingBalance;
    private Map<Country,Integer> amountToPay;

    public City(Position position, Country country) {
        this.position = position;
        this.country = country;

        currentBalance = new HashMap<>();
        incomingBalance = new HashMap<>();
        amountToPay = new HashMap<>();

        currentBalance.put(country, INITIAL_COINS);
    }

    public void acceptCoins(Country country, int amount){
        incomingBalance.put(country, incomingBalance.getOrDefault(country, NumberUtils.INTEGER_ZERO) + amount);
    }

    public void fillBalances() {
        for(Country key : currentBalance.keySet()){
            currentBalance.put(key, currentBalance.get(key) + incomingBalance.getOrDefault(key, NumberUtils.INTEGER_ZERO));
        }
    }

    public void clearIncoming() {
        incomingBalance.clear();
    }

    public boolean isComplete(Collection<Country> countries) {
        return countries.stream().allMatch(c -> currentBalance.getOrDefault(c, NumberUtils.INTEGER_ZERO) != 0);
    }

    public int withdrawalCoins(Country country){
        currentBalance.put(country,currentBalance.getOrDefault(country, NumberUtils.INTEGER_ZERO) - amountToPay.getOrDefault(country, NumberUtils.INTEGER_ZERO));
        return amountToPay.getOrDefault(country, NumberUtils.INTEGER_ZERO);
    }

    public void setAmountsToPay() {
        for(Country key : currentBalance.keySet()){
            amountToPay.put(key, currentBalance.get(key) / DEFAULT_DENOMINATOR);
        }
    }
}
