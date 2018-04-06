import {EmailCampaign} from "./email";

export class Campaign {
  id: number;
  name: string;
  schedule: Schedule;
  campaignType: CampaignType;
  segmentationID: number;
  templateID: number;
  // createdOn: Date;
  // clientID: number;
  // appuserID: number;
  // frequencyType: FrequencyType;
  // campaignStatus: EmailDeliveryStatus;
  // campaignStatus: DeliveryStatus;
  // emailCampaign: EmailCampaign;
  // smsCampaign: SmsCampaign;
}

export enum DeliveryStatus {
  NOT_SCHEDULED,
  SCHEDULED,
  IN_PROCESS,
  DELIVERED
}

export enum FrequencyType {
  ONCE,
  REPETITIVE
}

export enum CampaignType {
  EMAIL="EMAIL",
  SMS="SMS",
  MOBILE_PUSH_NOTIFICATION="MOBILE_PUSH_NOTIFICATION"
}



//new
export class Schedule {
  oneTime: ScheduleOneTime;
  multipleDates: ScheduleMultipleDates;
  recurring: ScheduleRecurring;
}

export class ScheduleOneTime {
  nowOrLater: Now;
  campaignTime: CampaignTime;
}

export class ScheduleMultipleDates {
  campaignDateTimeList: CampaignTime[];
}

export class ScheduleRecurring {
  cronExpression: string;
  scheduleStartDate: string;
  scheduleEnd: ScheduleEnd;
}
//new end

export class ScheduleEnd {
  endType: ScheduleEndType;
  endsOn: string;
  occurrences: number;
}

export enum ScheduleEndType {
  "NeverEnd" = "NeverEnd",
  "EndsOnDate" = "EndsOnDate",
  "Occurrences" = "Occurrences"
}

export enum ScheduleType {
  oneTime = "oneTime",
  multipleDates = "multipleDates",
  recurring = "recurring"
}

export class CampaignTime {
  date: string;
  hours: number;
  minutes: number;
  ampm: AmPm;
}

export enum Now {
  "Now" = "Now",
  "Later" = "Later"
}

export enum AmPm {
  AM = "AM",
  PM = "PM"
}
