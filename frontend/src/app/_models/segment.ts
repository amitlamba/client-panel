
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
  description: string;
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
  name: string;
  type: PropertyType;
  filterType: PropertyFilterType;
  operator: string;
  values: string[];
  valueUnit: string;
}

export enum PropertyType {
  string="string",
  number="number",
  date="date"
}

export enum PropertyFilterType {
  eventproperty="eventproperty",
  genericproperty="genericproperty",
  UTM="UTM"
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

export enum DateOperator {
  Before="Before",
  After="After",
  On="On",
  Between="Between",
  InThePast="InThePast",
  WasExactly="WasExactly",
  Today="Today",
  InTheFuture="InTheFuture",
  WillBeExactly="WillBeExactly"
}

export enum NumberOperator {
  Equals="Equals",
  Between="Between",
  GreaterThan="GreaterThan",
  LessThan="LessThan",
  NotEquals="NotEquals",
  Exists="Exists",
  DoesNotExist="DoesNotExist"
}

export enum StringOperator {
  Equals="Equals",
  NotEquals="NotEquals",
  Contains="Contains",
  DoesNotContain="DoesNotContain",
  Exists="Exists",
  DoesNotExist="DoesNotExist"
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

export class RegisteredEvent {
  name: string;
  properties: RegisteredEventProperties[];
}

export class RegisteredEventProperties {
  name: string;
  dataType: string;
  regex: string;
  options: any[];
}

export class GlobalFilterItem {
  value: string;
  displayName: string;
  type: string;
}
