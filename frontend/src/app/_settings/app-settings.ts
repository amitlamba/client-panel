export class AppSettings {
  public static API_ENDPOINT = "http://nestros.com:8080";


  public static API_ENDPOINT_CLIENT = AppSettings.API_ENDPOINT + "/client";
  // public static API_ENDPOINT_CLIENT = "http://localhost:9191"

  public static API_ENDPOINT_CLIENT_CLIENT = AppSettings.API_ENDPOINT_CLIENT + "/client";
  public static API_ENDPOINT_CLIENT_CLIENT_EMAIL = AppSettings.API_ENDPOINT_CLIENT_CLIENT + "/email";
  public static API_ENDPOINT_CLIENT_CLIENT_EMAIL_DEFAULT_TEMPLATES = AppSettings.API_ENDPOINT_CLIENT_CLIENT_EMAIL + "/default-templates";
  public static API_ENDPOINT_CLIENT_CLIENT_EMAIL_TEMPLATES = AppSettings.API_ENDPOINT_CLIENT_CLIENT_EMAIL + "/templates";
  public static API_ENDPOINT_CLIENT_CLIENT_EMAIL_SAVE_TEMPLATES = AppSettings.API_ENDPOINT_CLIENT_CLIENT_EMAIL + "/save-template";
  public static API_ENDPOINT_CLIENT_CLIENT_EMAIL_USER_EVENT_ATTRIBUTES = AppSettings.API_ENDPOINT_CLIENT_CLIENT_EMAIL + "/user-event-attributes";

  public static API_ENDPOINT_CLIENT_CLIENT_SMS = AppSettings.API_ENDPOINT_CLIENT_CLIENT + "/sms";
  public static API_ENDPOINT_CLIENT_CLIENT_SMS_DEFAULT_TEMPLATES = AppSettings.API_ENDPOINT_CLIENT_CLIENT_SMS + "/default-templates";
  public static API_ENDPOINT_CLIENT_CLIENT_SMS_TEMPLATES = AppSettings.API_ENDPOINT_CLIENT_CLIENT_SMS + "/templates";
  public static API_ENDPOINT_CLIENT_CLIENT_SMS_SAVE_TEMPLATES = AppSettings.API_ENDPOINT_CLIENT_CLIENT_SMS + "/save-template";
  public static API_ENDPOINT_CLIENT_CLIENT_SMS_USER_EVENT_ATTRIBUTES = AppSettings.API_ENDPOINT_CLIENT_CLIENT_SMS + "/user-event-attributes";

  public static API_ENDPOINT_CLIENT_CLIENT_USERS = AppSettings.API_ENDPOINT_CLIENT_CLIENT + "/users"
  public static API_ENDPOINT_CLIENT_CLIENT_USERS_GETLIST = AppSettings.API_ENDPOINT_CLIENT_CLIENT_USERS + "/get-list"
  public static API_ENDPOINT_CLIENT_CLIENT_USERS_GETUSEREVENTS = AppSettings.API_ENDPOINT_CLIENT_CLIENT_USERS + "/get-user-events"

  public static API_ENDPOINT_CLIENT_SETTING = AppSettings.API_ENDPOINT_CLIENT + "/setting";
  public static API_ENDPOINT_CLIENT_SETTING_EMAIL_SERVICE_PROVIDERS = AppSettings.API_ENDPOINT_CLIENT_SETTING + "/email-service-providers";
  public static API_ENDPOINT_CLIENT_SETTING_EMAIL_SERVICE_PROVIDER = AppSettings.API_ENDPOINT_CLIENT_SETTING + "/email-service-provider";
  public static API_ENDPOINT_CLIENT_SETTING_EMAIL_SERVICE_PROVIDER_SAVE = AppSettings.API_ENDPOINT_CLIENT_SETTING + "/email-service-provider/save";
  public static API_ENDPOINT_CLIENT_SETTING_SMS_SERVICE_PROVIDERS = AppSettings.API_ENDPOINT_CLIENT_SETTING + "/sms-service-providers";
  public static API_ENDPOINT_CLIENT_SETTING_SMS_SERVICE_PROVIDER = AppSettings.API_ENDPOINT_CLIENT_SETTING + "/sms-service-provider";
  public static API_ENDPOINT_CLIENT_SETTING_SMS_SERVICE_PROVIDER_SAVE = AppSettings.API_ENDPOINT_CLIENT_SETTING + "/sms-service-providers/save";


  public static API_ENDPOINT_AUTH = AppSettings.API_ENDPOINT + "/auth";

  public static API_ENDPOINT_AUTH_AUTH = AppSettings.API_ENDPOINT_AUTH + "/auth";
  public static API_ENDPOINT_AUTH_AUTH_VALIDATE = AppSettings.API_ENDPOINT_AUTH_AUTH + "/validate";
  public static API_ENDPOINT_AUTH_AUTH_USERDETAIL = AppSettings.API_ENDPOINT_AUTH_AUTH + "/userdetail";

  public static API_ENDPOINT_AUTH_REGISTER = AppSettings.API_ENDPOINT_AUTH + "/register";
  public static API_ENDPOINT_AUTH_REGISTER_VERIFYEMAIL = AppSettings.API_ENDPOINT_AUTH_REGISTER + "/verifyemail";
  public static API_ENDPOINT_AUTH_REGISTER_SENDVFNEMAIL = AppSettings.API_ENDPOINT_AUTH_REGISTER + "/sendvfnmail";
  public static API_ENDPOINT_AUTH_REGISTER_FORGOTPASSWORD = AppSettings.API_ENDPOINT_AUTH_REGISTER + "/forgotpassword";
  public static API_ENDPOINT_AUTH_REGISTER_RESETPASSWORD = AppSettings.API_ENDPOINT_AUTH_REGISTER + "/resetpassword";

  public static API_ENDPOINT_AUTH_SETTING = AppSettings.API_ENDPOINT_AUTH + "/setting";
  public static API_ENDPOINT_AUTH_SETTING_RESETPASSWORD = AppSettings.API_ENDPOINT_AUTH_SETTING + "/resetpassword";
  public static API_ENDPOINT_AUTH_SETTING_USERDETAILS = AppSettings.API_ENDPOINT_AUTH_SETTING + "/userDetails";
  public static API_ENDPOINT_AUTH_SETTING_UPDATEUSERDETAILS = AppSettings.API_ENDPOINT_AUTH_SETTING + "/updateUserDetails";
  public static API_ENDPOINT_AUTH_SETTING_REFRESHTOKEN = AppSettings.API_ENDPOINT_AUTH_SETTING + "/refreshToken";
}

export class UserField {
  fieldDisplayName: string;
  fieldVariableString: string;
  fieldCategory: string;
  constructor(fieldDisplayName: string, fieldVariableString: string, fieldCategory: string) {
    this.fieldDisplayName=fieldDisplayName;
    this.fieldVariableString=fieldVariableString;
    this.fieldCategory=fieldCategory;
  }
}

export class UserFields {
  public static USER_FIRST_NAME = new UserField("First Name", "${user.standardInfo.firstName}", "User Fields");
  public static USER_LAST_NAME = new UserField("Last Name", "${user.standardInfo.lastName}", "User Fields");
  public static USER_EMAIL = new UserField("Email", "${user.socialId.email}", "User Fields");
  public static USER_MOBILE_NUMBER = new UserField("Mobile Number", "${user.socialId.mobile}", "User Fields");
  public static USER_DETAIILS = [
    UserFields.USER_FIRST_NAME,
    UserFields.USER_LAST_NAME,
    UserFields.USER_EMAIL,
    UserFields.USER_MOBILE_NUMBER
  ];
}