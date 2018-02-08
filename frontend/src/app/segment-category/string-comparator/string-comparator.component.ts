import {Component, Input, OnInit} from '@angular/core';
import {SegmentService} from "../../_services/segment.service";

@Component({
  selector: 'app-string-comparator',
  templateUrl: './string-comparator.component.html',
  styleUrls: ['./string-comparator.component.css']
})
export class StringComparatorComponent implements OnInit {

  @Input() options: string[];
  stringComparatorMetadata: any;
  stringComparatorOperators: string[];
  private singleFieldRequiredComparators: string[] = ["Equals","NotEquals","Contains","DoesNotContain"];
  private doubleFieldRequiredComparators: string[] = [];
  private noFieldRequiredComparators: string[] = ["Exists","DoesNotExist"];
  fieldRequired: boolean = false;

  constructor(public segmentService: SegmentService) {
    this.stringComparatorMetadata = this.segmentService.stringComparatorMetadata;
    this.stringComparatorOperators = Object.keys(this.segmentService.stringComparatorMetadata);
  }

  ngOnInit() {
  }

  dropdownChanged(comparator: string) {
    if(this.singleFieldRequiredComparators.includes(comparator))
      this.fieldRequired = true;
    else
      this.fieldRequired = false;
  }
}
