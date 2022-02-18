package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

/**
 * Interface for the CompensationService class
 */
public interface CompensationService {
    Compensation create(Compensation employee);
    Compensation read(String id);
}
