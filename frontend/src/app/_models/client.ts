export class Client {
}

export class ServiceProviderCredentials {
  id: string;
  clientID: number;
  appuserID: number;
  serviceProviderType: string;
  serviceProvider: string;
  url: string;
  port: number;
  username: string;
  password: string;
  status: string;
  credentialsMap: any = {}; // store the map, key value pairs
}

export enum ServiceProviderType {
  "Email Service Provider" = "Email Service Provider",
  "SMS Service Provider" = "SMS Service Provider",
  "Notification Service Provider" = "Notification Service Provider"
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
export class AccountSettings {
  url: string[];
  timezone: string;
}
export class SendersEmail {
  email: string[];
}
