import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {SegmentService} from "../../../_services/segment.service";
import {DidEvents, Segment} from "../../../_models/segment";

@Component({
  selector: 'app-users-by-behaviour',
  templateUrl: './users-by-behaviour.component.html',
  styleUrls: ['./users-by-behaviour.component.css']
})
export class UsersByBehaviourComponent implements OnInit {

  localSegment: Segment;
  @Input() get segment(): Segment {
    return this.localSegment;
  }
  set segment(segment: Segment) {
    this.localSegment = this.segment;
    this.segmentChange.emit(this.localSegment);
  }
  @Output() segmentChange = new EventEmitter();

  constructor(public segmentService: SegmentService) {
    this.localSegment = segmentService.editSegment;
  }

  ngOnInit() {
  }

  find() {
    console.log(JSON.stringify(this.segment));
  }
}
