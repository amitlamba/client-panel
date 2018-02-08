import {Component, OnInit} from '@angular/core';
import {Segment} from "../../_models/segment";
import {SegmentService} from "../../_services/segment.service";

@Component({
  selector: 'app-segments',
  templateUrl: './segments.component.html',
  styleUrls: ['./segments.component.css']
})
export class SegmentsComponent implements OnInit {
  segments:Segment[]=[];

  constructor(public segmentService:SegmentService) {
  }

  ngOnInit() {
    this.segments=this.segmentService.segments;
  }


}
