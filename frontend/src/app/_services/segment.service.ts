import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RegisteredEvent, RegisteredEventProperties, Segment} from "../_models/segment";
import {AppSettings} from "../_settings/app-settings";
import {tap} from "rxjs/operators";
import {Observable} from "rxjs/Observable";

@Injectable()
export class SegmentService {
    segments: Segment[] = [];

  constructor(private httpClient: HttpClient) {
    this.segments.push(this.createNewSegment());
    this.segments.push(this.createNewSegment());
    this.segments.push(this.createNewSegment());
    this.segments.push(this.createNewSegment());
    this.segments.push(this.createNewSegment());
    this.segments.push(this.createNewSegment());
    this.segments.push(this.createNewSegment());
  }
  private createNewSegment(): Segment {
    var textArray = [
      'Behaviour',
      'Live'
    ];
    var randomNumber = Math.floor(Math.random()*textArray.length);

    var segment = new Segment();
    segment.id = Math.floor(Math.random() * 200000) + 1;
    segment.name = "Segment # "+segment.id;
    segment.type = textArray[randomNumber];
    segment.creationDate = "2017-01-01";
    return segment;
  }

  getEvents(): Observable<RegisteredEvent[]> {
    return this.httpClient.get<RegisteredEvent[]>(AppSettings.API_ENDPOINT_CLIENT_SEGMENT_METADATA)
      .pipe(
        tap(next => {})
      );
  }

  getSampleEvents(): RegisteredEvent[] {
    var registeredEvents = [];
    var property1 = this.createRegisteredEventProperty("Event Property");
    var property2 = this.createRegisteredEventProperty("First Time");
    var property3 = this.createRegisteredEventProperty("Time of the day");
    var property4 = this.createRegisteredEventProperty("Day of the week");
    var property5 = this.createRegisteredEventProperty("Day of the month");
    var property6 = this.createRegisteredEventProperty("UTM Source");
    var property7 = this.createRegisteredEventProperty("Session Referrer");
    var registeredEvent = this.createRegisteredEvent("Added to Cart", [property1, property2, property3, property4, property5]);
    var registeredEvent2 = this.createRegisteredEvent("UTM Visited", [property1, property2, property3, property4, property5, property6, property7]);
    registeredEvents.push(registeredEvent);
    registeredEvents.push(registeredEvent2);
    return registeredEvents;
  }

  private createRegisteredEvent(name: string, properties: RegisteredEventProperties[]): RegisteredEvent {
    var registeredEvent = new RegisteredEvent();
    registeredEvent.name = name;
    registeredEvent.properties = properties;
    return registeredEvent;
  }

  private createRegisteredEventProperty(name: string): RegisteredEventProperties {
    var reProp = new RegisteredEventProperties();
    reProp.name = name;
    return reProp
  }

  globalFilters = {
    "UserProperties": [{
      "propertyName": "User Property Name",
      "propertyType": "date/string/number",
      "operator": "",
      "values": [],
      "valueUnit": "w/m/d/y"
    },
      {
        "propertyName": "User Property Name",
        "propertyType": "date/string/number",
        "operator": "",
        "values": [],
        "valueUnit": "w/m/d/y"
      }
    ],
    "Demographics": [{
      "age": "18-25",
      "gender": "M"
    }],
    "Geography": [{
      "country": {
        "id": 10,
        "name": "India"
      },
      "state": {
        "id": 20,
        "name": "Haryana"
      },
      "city": {
        "id": 25,
        "name": "Gurugram"
      }
    }],
    "Technographics": [{
      "Browser": ["Chrome", "Firefox", "Internet Explorer", "Mobile Application", "Opera", "Others", "Safari", "Sea Monkey", "UC Browser"],
      "Device": ["Desktop", "Mobile", "Tablet", "TV"],
      "OS": ["Android", "Blackberry", "Ios", "Linux", "Mac", "Others", "Windows"]
    }],
    "Reachability": [{
      "hasDeviceToken": ["Yes", "No"],
      "hasEmailAddress": ["Yes", "No"],
      "hasPhoneNumber": ["Yes", "No"],
      "unsubscribedPush": ["Yes", "No"],
      "unsubscribedEmail": ["Yes", "No"],
      "unsubscribedSMS": ["Yes", "No"]
    }],
    "AppFields": [{
      "App Version": {
        "Operator": ["Equals", "Contains", "Does not Contain", "not equals"],
        "Versions": [0, 1, 2, 3, 4]
      },
      "Make": {
        "Operator": ["Equals", "Contains", "Does not Contain", "not equals"],
        "Versions": ["Apple", "Samsung", "Motorola", "Sony", "HTC", "Xiaomi"]
      },
      "Models": {
        "Operator": ["Equals", "Contains", "Does not Contain", "not equals"],
        "Versions": ["Iphone 6", "Iphone 8", "Iphone 7", "Moto g3"]
      },
      "OS Version": {
        "Operator": ["Equals", "Contains", "Does not Contain", "not equals"],
        "Versions": ["4.0.1", "4.4.1", "9.2", "9.1", "6.0.1"]
      },
      "SDK Version": {
        "Operator": ["Equals", "Contains", "Does not Contain", "not equals"],
        "Versions": [9001, 9002, 9003, 9004]
      }
    }]
  };

  schedule = {
    "schedule":[
      {
        "name":"One Time",
        "Options":[
          {
            "name":"Now/Later",
            "type":"Date"
          }
        ]
      },
      {
        "name":"On Multiple Dates",
        "Type":"Date Time[]"
      },
      {
        "name":"Recurring",
        "options":[
          {
            "name":"Campaign Start",
            "type":"Date"
          },
          {
            "name":"Campaign End",
            "options":[
              {
                "name":"Never End"
              },
              {
                "name":"Select Date",
                "type":"Date"
              },
              {
                "name":"After",
                "type":"Number"
              }
            ]
          }
        ]
      }
    ]
  };
}
