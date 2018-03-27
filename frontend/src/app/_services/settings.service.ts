import {Injectable} from '@angular/core';
import {ServiceProviderCredentials} from "../_models/client";
import {Campaign} from "../_models/campaign";
import {Observable} from "rxjs/Observable";
import {AppSettings} from "../_settings/app-settings";
import {HttpClient} from "@angular/common/http";
import {catchError, tap} from "rxjs/operators";

@Injectable()
export class SettingsService {

  serviceProvidersList: ServiceProviderCredentials[] = [];

  constructor(private httpClient: HttpClient) {
  }

  saveServiceProviderCredentialEmail(serviceProviderCredentials: ServiceProviderCredentials): Observable<any> {
    return this.httpClient.post(AppSettings.API_ENDPOINT_CLIENT_SETTING_EMAIL_SERVICE_PROVIDER_SAVE, serviceProviderCredentials);
  }

  saveServiceProviderCredentialsSms(serviceProviderCredentials: ServiceProviderCredentials): Observable<any> {
    return this.httpClient.post(AppSettings.API_ENDPOINT_CLIENT_SETTING_SMS_SERVICE_PROVIDER_SAVE, serviceProviderCredentials);
  }

  getServiceProvidersList(): Observable<ServiceProviderCredentials[]> {
    return this.httpClient.get<ServiceProviderCredentials[]>(AppSettings.API_ENDPOINT_CLIENT_SETTING_ALL_SERVICE_PROVIDERS);
  }


  readonly serviceProviders: any = {
    "Email Service Provider": {
      "name": "EmailServiceProvider",
      "displayName": "Email Service Provider",
      "providers": {
        "SMTP": {
          "name": "SMTP",
          "displayName": "SMTP",
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
        },
        "AWS - Simple Email Service (API)": {
          "name": "AWS - Simple Email Service (API)",
          "displayName": "AWS - Simple Email Service (API)",
          "fields": [
            {
              "fieldName": "AWS_REGION",
              "fieldDisplayName": "AWS Region",
              "required": true,
              "fieldType": "string",
            },
            {
              "fieldName": "AWS_ACCESS_KEY_ID",
              "fieldDisplayName": "AWS Access Key ID",
              "required": true,
              "fieldType": "string",
            },
            {
              "fieldName": "AWS_SECRET_ACCESS_KEY",
              "fieldDisplayName": "AWS Secret Access Key",
              "required": true,
              "fieldType": "string",
            }
          ]
        },
        "AWS - Simple Email Service (SMTP)": {
          "name": "AWS - Simple Email Service (SMTP)",
          "displayName": "AWS - Simple Email Service (SMTP)",
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
      }
    },
    "SMS Service Provider": {
      "name": "SMSServiceProvider",
      "displayName": "SMS Service Provider",
      "providers": {
        "AWS - Simple Email Service": {
          "name": "AWS - Simple Email Service",
          "displayName": "AWS - Simple Email Service",
          "fields": [
            {
              "fieldName": "AWS_REGION",
              "fieldDisplayName": "AWS Region",
              "required": true,
              "fieldType": "string",
            },
            {
              "fieldName": "AWS_ACCESS_KEY_ID",
              "fieldDisplayName": "AWS Access Key ID",
              "required": true,
              "fieldType": "string",
            },
            {
              "fieldName": "AWS_SECRET_ACCESS_KEY",
              "fieldDisplayName": "AWS Secret Access Key",
              "required": true,
              "fieldType": "string",
            }
          ]
        }
      }
    },
    "Notification Service Provider": {
      "name": "NotificationServiceProvider",
      "displayName": "Notification Service Provider",
      "providers": {
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
        },
        "Google - GCM": {
          "name": "Google - GCM"
        }
      }
    }
  }
  ;

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
}
