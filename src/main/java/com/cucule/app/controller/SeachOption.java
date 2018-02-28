package com.cucule.app.controller;

import java.util.Map;
import java.util.Set;

/**
 * Created by user on 2017/05/19.
 */
public class SeachOption {
    private Set<String> currencyList;
    private Map<String, Set<String>> currencyCountryListMap;
    private Map<String, Set<Long>> countryExpenseIdListMap;

    public Set<String> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(Set<String> currencyList) {
        this.currencyList = currencyList;
    }

    public Map<String, Set<String>> getCurrencyCountryListMap() {
        return currencyCountryListMap;
    }

    public void setCurrencyCountryListMap(Map<String, Set<String>> currencyCountryListMap) {
        this.currencyCountryListMap = currencyCountryListMap;
    }

    public Map<String, Set<Long>> getCountryExpenseIdListMap() {
        return countryExpenseIdListMap;
    }

    public void setCountryExpenseIdListMap(Map<String, Set<Long>> countryExpenseIdListMap) {
        this.countryExpenseIdListMap = countryExpenseIdListMap;
    }
}
