import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, NgForm} from '@angular/forms';
//used to create fake backend
import {MockBackend} from '@angular/http/testing';

import {AppComponent} from './app.component';

import {DummyComponent} from './dummy/dummy.component';
import {HttpClientModule} from '@angular/common/http';
import {ProfileComponent} from './settings/profile/profile.component';
import {UsersComponent} from './users/users.component';
import {AuthenticationService} from "./_services/authentication.service";
import {UserService} from "./_services/user.service";
import {AuthGuard} from "./_guards/auth.guard";
import {LoginComponent} from "./login/login.component";
import {HomeComponent} from "./home/home.component";
import {HeaderComponent} from "./header/header.component";
import {RegisterComponent} from "./register/register.component";
import {InterceptorModule} from "./_interceptors/interceptor.module";
import {FooterComponent} from './footer/footer.component';
import {ServiceprovidersComponent} from './settings/serviceproviders/serviceproviders.component';
import {LogoutHeaderComponent} from './header/logout-header.component';
import {AppRoutingModule} from './/app-routing.module';
import { SettingsComponent } from './settings/settings.component';
import { MessagesComponent } from './messages/messages.component';
import {MessageService} from "./_services/message.service";
import { TemplatesComponent } from './templates/templates.component';
import { SmsTemplatesComponent } from './templates/sms-templates/sms-templates.component';
import { EmailTemplatesComponent } from './templates/email-templates/email-templates.component';
import { CreateEmailTemplateFormComponent } from './templates/email-templates/create-email-template-form/create-email-template-form.component';
import {TemplatesService} from "./_services/templates.service";
import { CreateSmsTemplateFormComponent } from './templates/sms-templates/create-sms-template-form/create-sms-template-form.component';
import { ForgotPasswordComponent } from './login/forgot-password/forgot-password.component';


@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    InterceptorModule,
    AppRoutingModule,

  ],
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    HeaderComponent,
    RegisterComponent,
    DummyComponent,
    ProfileComponent,
    UsersComponent,
    FooterComponent,
    ServiceprovidersComponent,
    LogoutHeaderComponent,
    SettingsComponent,
    MessagesComponent,
    TemplatesComponent,
    SmsTemplatesComponent,
    EmailTemplatesComponent,
    CreateEmailTemplateFormComponent,
    CreateSmsTemplateFormComponent,
    ForgotPasswordComponent
  ],
  providers: [
    AuthGuard,
    AuthenticationService,
    UserService,
    // fakeBackendProvider,
    MockBackend,
    // BaseRequestOptions
    MessageService,
    TemplatesService,
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}
