import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {SegmentService} from "../../_services/segment.service";
import {StringOperator} from "../../_models/segment";

@Component({
  selector: 'app-string-comparator',
  templateUrl: './string-comparator.component.html',
  styleUrls: ['./string-comparator.component.css']
})
export class StringComparatorComponent implements OnInit {

  @Input() options: string[];
  stringComparatorMetadata: any;
  @Input() stringComparatorOperators: string[];
  private singleFieldRequiredComparators: string[] = ["Equals","NotEquals","Contains","DoesNotContain"];
  private doubleFieldRequiredComparators: string[] = [];
  private noFieldRequiredComparators: string[] = ["Exists","DoesNotExist"];
  fieldRequired: boolean = false;

  private localOperator: StringOperator;
  @Input() get operator(): StringOperator {
    return this.localOperator;
  }
  set operator(operator: StringOperator) {
    this.localOperator = operator;
    this.operatorChange.emit(this.localOperator);
  }
  @Output() operatorChange = new EventEmitter();

  private localValues: any[] = [];
  @Input() get values(): any[] {
    return this.localValues;
  }
  set values(values: any[]) {
    this.localValues = values;
    this.valuesChange.emit(this.localValues);
  }
  @Output() valuesChange = new EventEmitter();

  private localValueUnit: string;
  @Input() get valueUnit(): string {
    return this.localValueUnit;
  }
  set valueUnit(valueUnit: string) {
    this.localValueUnit = valueUnit;
    this.valueUnitChange.emit(this.localValueUnit);
  }
  @Output() valueUnitChange = new EventEmitter();

  constructor(public segmentService: SegmentService) {
    this.stringComparatorMetadata = this.segmentService.stringComparatorMetadata;
  }

  ngOnInit() {
    if(!this.stringComparatorOperators)
      this.stringComparatorOperators = Object.keys(this.segmentService.stringComparatorMetadata);
  }

  dropdownChanged(comparator: string) {
    this.operator = StringOperator[comparator];
    if(this.singleFieldRequiredComparators.includes(comparator)) {
      this.fieldRequired = true;
    }
    else
      this.fieldRequired = false;
  }
}
