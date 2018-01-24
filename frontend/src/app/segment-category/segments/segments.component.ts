import {Component, OnInit} from '@angular/core';
import {Segment} from "../../_models/segment";

@Component({
  selector: 'app-segments',
  templateUrl: './segments.component.html',
  styleUrls: ['./segments.component.css']
})
export class SegmentsComponent implements OnInit {

  segments: Segment[] = [];

  constructor() {
    this.segments.push(this.createNewSegment());
    this.segments.push(this.createNewSegment());
    this.segments.push(this.createNewSegment());
    this.segments.push(this.createNewSegment());
    this.segments.push(this.createNewSegment());
    this.segments.push(this.createNewSegment());
    this.segments.push(this.createNewSegment());
  }

  ngOnInit() {
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

}
