export class Client {
}

export class ServiceProviderCredentials {
  id: string;
  clientID: number;
  appuserID: number;
  serviceProviderType: ServiceProviderType;
  serviceProvider: ServiceProvider;
  url: string;
  port: number;
  username: string;
  password: string;
}

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

export class RegistrationRequest {
  email: string;
  password: string;
  name: string;
  country: string;
  address: string;
  phone: string;
  firstName: string;
  lastName: string;
}

export class UserProfileRequest {
  firstname: string;
  lastname: string;
  address: string;
  phone: string;
  eventUserToken: string;
}
