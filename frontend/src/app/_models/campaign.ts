import {EmailCampaign} from "./email";

export class Campaign {
  id: number;
  name:string;
  clientID: number;
  appuserID: number;
  campaignType: CampaignType;
  segmentationID: number;
  frequencyType: FrequencyType;
  schedule: string;
  createdOn: Date;
  // campaignStatus: EmailDeliveryStatus;
  campaignStatus: DeliveryStatus;

  emailCampaign: EmailCampaign;
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
  EMAIL,
  SMS,
  MOBILE_PUSH_NOTIFICATION
}



export class Schedule {
  scheduleType: string;
  startTime: Now;
  campaignTimeList: CampaignTime[];
}
export class CampaignTime {
  date: string;
  hours: number;
  minutes: number;
  ampm: AmPm;
}
export enum Now{
  "Now"="Now",
  "Later"="Later"
}
export enum AmPm {
  AM="AM",
  PM="PM"
}
