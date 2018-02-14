import {Component, OnInit} from '@angular/core';
import {City, Country, State} from "../../../_models/segment";
import {SegmentService} from "../../../_services/segment.service";

@Component({
  selector: 'app-geography-filter',
  templateUrl: './geography-filter.component.html',
  styleUrls: ['./geography-filter.component.css']
})
export class GeographyFilterComponent implements OnInit {

  countries: Country[];
  states: State[];
  cities: City[];
  selectedCountry: Country;
  selectedState: State;
  selectedCity: City;
  geography: boolean;

  countriesSelectList: any[] = [];

  _parentComponentsArr: any[];
  _ref: any;

  removeObject(){
    this.removeFromParentArr();
    this._ref.destroy();
  }

  removeFromParentArr() {
    // Find the component
    const componentIndex = this._parentComponentsArr.indexOf(this._ref);

    if (componentIndex !== -1) {
      // Remove component from both view and array
      this._parentComponentsArr.splice(componentIndex, 1);
    }
  }

  constructor(private segmentService: SegmentService) {
    this.getCountries();
    this.countriesSelectList = ["Afghanistan","India"];
  }

  ngOnInit() {
  }

  getCountries() {
    this.segmentService.getCountries().subscribe(
      countries => {
        this.countries = countries;
        this.countries.forEach((country) => {this.countriesSelectList.push({id: country.id, text: country.name})});
        // console.log(JSON.stringify(this.countriesSelectList));
      }
    );
  }

  getStates(countryId: number) {
    this.segmentService.getStates(countryId).subscribe(
      states => {
        this.states = states;
      }
    );
  }

  getCities(stateId: number) {
    this.segmentService.getStates(stateId).subscribe(
      cities => {
        this.cities = cities;
      }
    );
  }

  onCountrySelect(country: any) {
    console.log(country);
  }
}
