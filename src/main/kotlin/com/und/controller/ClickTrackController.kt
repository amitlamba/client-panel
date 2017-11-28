package com.und.controller

import com.und.eventapi.model.ClickTrackEvent
import com.und.service.ClickTrackService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Controller
@RequestMapping("/click")
class ClickTrackController {

    @Autowired
    private lateinit var clickTrackService: ClickTrackService

    @RequestMapping(value = "/track", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun trackClick(@RequestParam url: String, @RequestParam event: String, @RequestParam clientID: String,
                   @RequestParam undUid: String, @RequestParam emailUid: String,
                 request: HttpServletRequest, response: HttpServletResponse) {
        clickTrackService.toKafka(ClickTrackEvent(name = event, clientId = clientID, emailUid = emailUid,
                url = url)) //TODO: Add undUid in EventUser
        response.status = HttpServletResponse.SC_MOVED_TEMPORARILY
        response.setHeader("Location", url)
        response.setHeader("Connection", "close")
    }

    @RequestMapping(value = "/create-link", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun createTrackLink(@RequestParam url: String, @RequestParam source: String, @RequestParam event: String, @RequestParam clientID: String): String {
        return "http://localhost:8080/click/track?url=${url}&event=${event}&clientID=${clientID}&undUid=1&emailUid=1"
    }
}