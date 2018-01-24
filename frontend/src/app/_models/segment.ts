
export class Segment {
  id: number;
  name: string;
  type: string;
  creationDate: string;
  conversionEvent: string;
  didEvents: DidEvents;
  didNotEvents: DidEvents;
}

export class DidEvents {
  joinCondition: JoinCondition;
  events: Event[];
}

export class JoinCondition {
  anyOfCount: number;
  conditionType: string; // AllOf / AnyOf
}

export class Event {
  name: string;
  dateFilter: DateFilter;
  propertyFilter: PropertyFilter;
  whereFilter: WhereFilter;
}

export class DateFilter {
  operator: DateOperator;
  values: string[];
  valueUnit: string;
}

export class PropertyFilter {
  propertyName: string;
  propertyType: string;
  operator: DateOperator;
  values: string[];
  valueUnit: string;
}

export class WhereFilter {
  count: WhereCount;
  sumOfValuesOf: PropertySumOf;
}

export class WhereCount {
  operator: NumberOperator;
  values: number[]
}

export class PropertySumOf {
  propertyName: string;
  operator: NumberOperator;
  values: number[]
}

export class DateOperator {

}

export class NumberOperator {

}

export class GlobalFilters {
  userProperties: UserProperty[];
  demograpgics: Demography[];
  geography: Geography[];
  technographics: Technography[];
  rechability: Reachability;
  appFields: AppFields;
}

export class UserProperty {
  propertyName: string;
  propertyType: string;
  operator: any;
  values: any[];
  valueUnit: string;
}

export class Demography {
  age: string;
  gender: string;
}

export class Geography {
  country: Country;
  state: State;
  city: City;
}

export class Country {
  id: number;
  name: string;
}

export class State {
  id: number;
  name: string;
}

export class City {
  id: number;
  name: string;
}

export class Technography {
  browser: Browser[];
  device: Device[];
  OS: OS[];
}

export enum Browser {
  Chrome = "Chrome",
  Firefox = "Firefox",
  InternetExplorer = "Internet Explorer",
  MobileApplication = "Mobile Application",
  Opera = "Opera",
  Others = "Others",
  Safari = "Safari"
}

export enum Device {
  Desktop = "Desktop",
  Mobile = "Mobile",
  Tablet = "Tablet"
}

export enum OS {
  Android = "Android",
  iOS = "iOS",
  Linux = "Linux",
  MACOS = "MACOS",
  Windows = "Windows",
  Others = "Others"
}

export class Reachability {
  HasDeviceToken: boolean;
  HasEmailAddress: boolean;
  HasPhoneNumber: boolean;
  UnsubscribedPush: boolean;
  UnsubscribedEmail: boolean;
  UnsubscribedSMS: boolean;
}

export class AppFields {
  OSVersion: AppFieldOperator;
  AppVersion: AppFieldOperator;
  Make: AppFieldOperator;
  Model: AppFieldOperator;
  SDKVersion: AppFieldOperator;
}

export class AppFieldOperator {

}
