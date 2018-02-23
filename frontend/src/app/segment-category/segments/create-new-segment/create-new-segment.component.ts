import { Component, OnInit } from '@angular/core';
import {SegmentService} from "../../../_services/segment.service";
import {DidEvents, Geography, GlobalFilter, Segment} from "../../../_models/segment";

@Component({
  selector: 'app-create-new-segment',
  templateUrl: './create-new-segment.component.html',
  styleUrls: ['./create-new-segment.component.css']
})
export class CreateNewSegmentComponent implements OnInit {

  newSegment: Segment;

  constructor(private segmentService: SegmentService) {
    this.newSegment = new Segment();
    this.newSegment.didEvents = new DidEvents();
    this.newSegment.didNotEvents = new DidEvents();
    this.newSegment.globalFilters = [];
    this.newSegment.geographyFilters = new Array<Geography>();
  }

  ngOnInit() {
  }

}
