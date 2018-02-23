import {Component, OnInit} from '@angular/core';
import {Segment} from "../../_models/segment";
import {Router} from "@angular/router";
import {SegmentService} from "../../_services/segment.service";

@Component({
  selector: 'app-segments',
  templateUrl: './segments.component.html',
  styleUrls: ['./segments.component.css']
})
export class SegmentsComponent implements OnInit {

  segments: Segment[] = [];

  constructor(private router: Router, public segmentService:SegmentService) {
    this.segments.push(this.createNewSegment());
    this.segments.push(this.createNewSegment());
    this.segments.push(this.createNewSegment());
    this.segments.push(this.createNewSegment());
    this.segments.push(this.createNewSegment());
    this.segments.push(this.createNewSegment());
    this.segments.push(this.createNewSegment());
  }

  ngOnInit() {
    this.segments=this.segmentService.segments;
  }

  private createNewSegment(): Segment {
    let textArray = [
      'Behaviour',
      'Live'
    ];
    let randomNumber = Math.floor(Math.random()*textArray.length);

    var segment = new Segment();
    segment.id = Math.floor(Math.random() * 200000) + 1;
    segment.name = "Segment # "+segment.id;
    segment.type = textArray[randomNumber];
    segment.creationDate = "2017-01-01";
    return segment;
  }

  onCreateNew() {
    this.router.navigate(["segment","create-new-segment"]);
  }
}
