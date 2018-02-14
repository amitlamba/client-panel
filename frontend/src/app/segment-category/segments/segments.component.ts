import {Component, OnInit} from '@angular/core';
import {Segment} from "../../_models/segment";
import {Router} from "@angular/router";

@Component({
  selector: 'app-segments',
  templateUrl: './segments.component.html',
  styleUrls: ['./segments.component.css']
})
export class SegmentsComponent implements OnInit {

  segments: Segment[] = [];

  constructor(private router: Router) {
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
