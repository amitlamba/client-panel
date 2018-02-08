import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
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
import {AppRoutingModule} from './app-routing.module';
import {SettingsComponent} from './settings/settings.component';
import {MessagesComponent} from './messages/messages.component';
import {MessageService} from "./_services/message.service";
import {TemplatesComponent} from './templates/templates.component';
import {SmsTemplatesComponent} from './templates/sms-templates/sms-templates.component';
import {EmailTemplatesComponent} from './templates/email-templates/email-templates.component';
import {CreateEmailTemplateFormComponent} from './templates/email-templates/create-email-template-form/create-email-template-form.component';
import {TemplatesService} from "./_services/templates.service";
import { ForgotPasswordComponent } from './login/forgot-password/forgot-password.component';
import {CreateSmsTemplateFormComponent} from './templates/sms-templates/create-sms-template-form/create-sms-template-form.component';
import {UndEditorComponent} from './und-editor/und-editor.component';
import {SimpleTinyComponent} from './_helpers/simple-tiny/simple-tiny.component';
import {CkEditorComponent} from './_helpers/ck-editor/ck-editor.component';
import {MentionModule} from "./_helpers/mention/mention.module";
import { SegmentCategoryComponent } from './segment-category/segment-category.component';
import { FindUsersComponent } from './segment-category/find-users/find-users.component';
import { SegmentsComponent } from './segment-category/segments/segments.component';
import { DidEventsComponent } from './segment-category/did-events/did-events.component';
import { ResetPwdComponent } from './login/reset-pwd/reset-pwd.component';
import {Daterangepicker} from "ng2-daterangepicker";
import { DaterangepickerConfig } from 'ng2-daterangepicker';
import { MyDateRangePickerModule } from 'mydaterangepicker';
import { DidEventComponent } from './segment-category/did-events/did-event/did-event.component';
import { FilterComponent } from './segment-category/did-events/did-event/filter/filter.component';

import { PageNotFoundComponent } from './shared/page-not-found/page-not-found.component';
import { TestComponent } from './test/test.component';
import { ExpComponent } from './test/exp/exp.component';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {AngularMultiSelectModule} from './multiselect/multiselect.component';

import { CampaignsComponent } from './campaigns/campaigns.component';
import {SegmentService} from "./_services/segment.service";
import { GlobalFiltersComponent } from './segment-category/global-properties/global-filters.component';
import { SetupCampaignComponent } from './campaigns/setup-campaign/setup-campaign.component';
import { CampaignsListComponent } from './campaigns/campaigns-list/campaigns-list.component';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    InterceptorModule,
    AppRoutingModule,
    MentionModule,
    Daterangepicker,
    MyDateRangePickerModule,
    BrowserAnimationsModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    AngularMultiSelectModule
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
    ForgotPasswordComponent,
    UndEditorComponent,
    SimpleTinyComponent,
    CkEditorComponent,
    SegmentCategoryComponent,
    FindUsersComponent,
    SegmentsComponent,
    DidEventsComponent,
    ResetPwdComponent,
    DidEventComponent,
    FilterComponent,
    PageNotFoundComponent,
    TestComponent,
    ExpComponent,
    CampaignsComponent,
    GlobalFiltersComponent,
    SetupCampaignComponent,
    CampaignsListComponent
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
    SegmentService,
  ],
  entryComponents: [DidEventComponent,ExpComponent],
  bootstrap: [AppComponent]
})

export class AppModule {
}
