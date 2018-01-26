package com.und.service

import com.und.model.Cities
import com.und.model.Countries
import com.und.model.States
import com.und.repository.CitiesRepository
import com.und.repository.CountriesRepository
import com.und.repository.StatesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LocationService {
    @Autowired
    private lateinit var countriesRepository: CountriesRepository
    @Autowired
    private lateinit var statesRepository: StatesRepository
    @Autowired
    private lateinit var citiesRepository: CitiesRepository

    fun getCountriesList(): List<Countries> {
        return countriesRepository.findAll()
    }

    fun getStatesByCountryId(countryId: Int): List<States> {
        return statesRepository.findByCountryId(countryId)
    }

    fun getCitiesByStateId(stateId: Int): List<Cities> {
        return citiesRepository.findByStateId(stateId)
    }
}