import {EmailCampaign} from "./email";

export class Campaign {
  id: number;
  clientID: number;
  appuserID: number;
  campaignType: CampaignType;
  segmentationID: number;
  frequencyType: FrequencyType;
  schedule: string;
  campaignStatus: EmailDeliveryStatus;
  emailCampaign: EmailCampaign;
}

export enum EmailDeliveryStatus {
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
