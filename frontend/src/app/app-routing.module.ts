import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {HomeComponent} from "./home/home.component";
import {AuthGuard} from "./_guards/auth.guard";
import {ProfileComponent} from "./settings/profile/profile.component";
import {RegisterComponent} from "./register/register.component";
import {DummyComponent} from "./dummy/dummy.component";
import {SettingsComponent} from "./settings/settings.component";
import {ServiceprovidersComponent} from "./settings/serviceproviders/serviceproviders.component";
import {TemplatesComponent} from "./templates/templates.component";
import {EmailTemplatesComponent} from "./templates/email-templates/email-templates.component";
import {SmsTemplatesComponent} from "./templates/sms-templates/sms-templates.component";
import {SegmentCategoryComponent} from "./segment-category/segment-category.component";
import {FindUsersComponent} from "./segment-category/find-users/find-users.component";
import {SegmentsComponent} from "./segment-category/segments/segments.component";
import {ResetPwdComponent} from "./login/reset-pwd/reset-pwd.component";
import {TestComponent} from "./test/test.component";
import {CreateNewSegmentComponent} from "./segment-category/segments/create-new-segment/create-new-segment.component";
import {PageNotFoundComponent} from "./shared/page-not-found/page-not-found.component";
import {CampaignsComponent} from "./campaigns/campaigns.component";
import {SetupCampaignComponent} from "./campaigns/setup-campaign/setup-campaign.component";
import {CampaignsListComponent} from "./campaigns/campaigns-list/campaigns-list.component";
import {AccountSettingsComponent} from "./settings/account-settings/account-settings.component";
import {EmailListComponent} from "./settings/email-list/email-list.component";
import {LandingPageUndComponent} from "./landing-page-und/landing-page-und.component";
import {AboutUsComponent} from "./about-us/about-us.component";
import {TermsOfServiceComponent} from "./terms-of-service/terms-of-service.component";
import {ContactUsComponent} from "./contact-us/contact-us.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'test', component: TestComponent},
  {path: '', component: LandingPageUndComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'resetpwd/:code', component: ResetPwdComponent},
  {path: 'dummyJson', component: DummyComponent},
  {path: 'dashboard', component: HomeComponent, canActivate:[AuthGuard]},
  {path: 'settings', redirectTo: "settings/profile", pathMatch: "full", canActivate: [AuthGuard]},
  {
    path: 'settings', component: SettingsComponent, canActivate: [AuthGuard], children: [
      {path: 'profile', component: ProfileComponent},
      {path: 'service-provider-settings', component: ServiceprovidersComponent},
      {path: 'account-settings', component: AccountSettingsComponent},
      {path: 'email-list', component: EmailListComponent}
    ]
  },
  {path: 'segment', redirectTo: "segment/segments", canActivate: [AuthGuard], pathMatch: "full"},
  {
    path: 'segment', component: SegmentCategoryComponent, canActivate: [AuthGuard], children: [
      {path: 'find-users', component: FindUsersComponent},
      {path: 'create-new-segment', component: CreateNewSegmentComponent},
      {path: 'segments', component: SegmentsComponent}
    ]
  },
  {path: 'templates', redirectTo: "templates/email", canActivate: [AuthGuard], pathMatch: "full"},
  {
    path: 'templates', component: TemplatesComponent, canActivate: [AuthGuard], children: [
      {path: 'email', component: EmailTemplatesComponent},
      {path: 'sms', component: SmsTemplatesComponent}
    ]
  },
  {path: 'campaigns', component: CampaignsListComponent, canActivate: [AuthGuard], pathMatch: "full"},
  {
    path: 'campaigns', component: CampaignsComponent, canActivate: [AuthGuard], children: [
      {path: 'sms', component: SetupCampaignComponent},
      {path: 'email', component: SetupCampaignComponent}
    ]
  },
  {path:'aboutus', component: AboutUsComponent},
  {path:'terms-of-service', component: TermsOfServiceComponent},
  {path:'contact-us', component: ContactUsComponent},
  {path: '**', component: PageNotFoundComponent},
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}

