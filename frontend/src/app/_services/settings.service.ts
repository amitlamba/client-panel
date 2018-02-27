import {Injectable} from '@angular/core';

@Injectable()
export class SettingsService {
  /*
  export enum ServiceProvider {
    AWS_SES,
    AWS_SNS,
    GOOGLE_FCM
  }

  export enum ServiceProviderType {
    EMAIL_SERVICE_PROVIDER,
    SMS_SERVICE_PROVIDER,
    NOTIFICATIONS_SERVICE_PROVIDER
  }
   */
  readonly serviceProviders: any = {
    "Email Service Provider": {
      "AWS - Simple Email Service": {
        "name": "AWS - Simple Email Service",
        "displayName": "AWS - Simple Email Service",
        "fields": [
          {
            "fieldName": "url",
            "fieldDisplayName": "URL",
            "required": true,
            "fieldType": "string",
          },
          {
            "fieldName": "port",
            "fieldDisplayName": "Port",
            "required": true,
            "fieldType": "number",
          },
          {
            "fieldName": "username",
            "fieldDisplayName": "Username",
            "required": true,
            "fieldType": "string",
          },
          {
            "fieldName": "password",
            "fieldDisplayName": "Password",
            "required": true,
            "fieldType": "string",
          }
        ]
      }
    },
    "SMS Service Provider": {
      "AWS - Simple Notification Service": {
        "name": "AWS - Simple Email Service",
        "displayName": "AWS - Simple Email Service",
        "fields": [
          {
            "fieldName": "url",
            "fieldDisplayName": "URL",
            "required": true,
            "fieldType": "string",
          },
          {
            "fieldName": "port",
            "fieldDisplayName": "Port",
            "required": true,
            "fieldType": "number",
          },
          {
            "fieldName": "username",
            "fieldDisplayName": "Username",
            "required": true,
            "fieldType": "string",
          },
          {
            "fieldName": "password",
            "fieldDisplayName": "Password",
            "required": true,
            "fieldType": "string",
          }
        ]
      }
    },
    "Notification Service Provider": {
      "Google - FCM": {
        "name": "Google - FCM",
        "displayName": "Google - FCM",
        "fields": [
          {
            "fieldName": "apiKey",
            "fieldDisplayName": "API Key",
            "required": true,
            "fieldType": "string"
          },
          {
            "fieldName": "senderId",
            "fieldDisplayName": "Sender ID",
            "required": true,
            "fieldType": "string"
          }
        ]
      }, "Google - GCM": {
        "name": "Google - GCM"
      }
    }
  };

  constructor() {
  }

}
