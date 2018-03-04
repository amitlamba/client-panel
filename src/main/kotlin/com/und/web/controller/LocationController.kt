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

    @RequestMapping(value = "/countries", method = arrayOf(RequestMethod.GET))
    fun getCountriesList(): List<Countries> {
        return locationService.getCountriesList()
    }

    @RequestMapping(value = "/states/{countryId}", method = arrayOf(RequestMethod.GET))
    fun getStatesByCountryId(@PathVariable countryId: Int): List<States> {
        return locationService.getStatesByCountryId(countryId)
    }

    @RequestMapping(value = "/cities/{stateId}", method = arrayOf(RequestMethod.GET))
    fun getCitiesByStateId(@PathVariable stateId: Int): List<Cities> {
        return locationService.getCitiesByStateId(stateId)
    }
}