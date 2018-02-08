import {Component, Input, OnInit} from '@angular/core';
import {SegmentService} from "../../_services/segment.service";

@Component({
  selector: 'app-number-comparator',
  templateUrl: './number-comparator.component.html',
  styleUrls: ['./number-comparator.component.css']
})
export class NumberComparatorComponent implements OnInit {

  @Input() options: string[];
  numberComparatorMetadata: any;
  numberComparatorOperators: string[];
  private singleFieldRequiredComparators: string[] = ["Equals", "NotEquals", "GreaterThan", "LessThan"];
  private doubleFieldRequiredComparators: string[] = ["Between"];
  private noFieldRequiredComparators: string[] = ["Exists", "DoesNotExist"];
  field1Required: boolean = false;
  field2Required: boolean = false;

  constructor(public segmentService: SegmentService) {
    this.numberComparatorMetadata = this.segmentService.numberComparatorMetadata;
    this.numberComparatorOperators = Object.keys(this.segmentService.numberComparatorMetadata);
  }

  ngOnInit() {
  }

  dropdownChanged(comparator: string) {
    if (this.doubleFieldRequiredComparators.includes(comparator)) {
      this.field1Required = this.field2Required = true;
    } else if (this.singleFieldRequiredComparators.includes(comparator)) {
      this.field2Required = false;
      this.field1Required = true;
    } else {
      this.field2Required = this.field1Required = false;
    }
  }
}
