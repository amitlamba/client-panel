package com.und.web.controller

import com.und.model.jpa.Cities
import com.und.model.jpa.Countries
import com.und.model.jpa.States
import com.und.service.LocationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController("location")
@RequestMapping("/location")
class LocationController {

    @Autowired
    private lateinit var locationService: LocationService

    @GetMapping(value = ["/countries"])
    fun getCountriesList(): List<Countries> {
        return locationService.getCountriesList()
    }

    @GetMapping(value = ["/states/{countryId}"])
    fun getStatesByCountryId(@PathVariable countryId: Int): List<States> {
        return locationService.getStatesByCountryId(countryId)
    }

    @GetMapping(value = ["/cities/{stateId}"])
    fun getCitiesByStateId(@PathVariable stateId: Int): List<Cities> {
        return locationService.getCitiesByStateId(stateId)
    }
}