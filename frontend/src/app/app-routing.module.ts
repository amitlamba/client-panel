import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
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

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: '', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'register', component: RegisterComponent},
  {path: 'dummyJson', component: DummyComponent},
  {path: 'settings', redirectTo:"settings/profile", pathMatch:"full", canActivate: [AuthGuard]},
  {path: 'settings', component: SettingsComponent, canActivate: [AuthGuard], children: [
    {path: 'profile', component: ProfileComponent},
    {path: 'service-provider-settings', component: ServiceprovidersComponent}
  ]},
  {path: 'segment', redirectTo:"segment/segments", canActivate: [AuthGuard], pathMatch:"full"},
  {path: 'segment', component: SegmentCategoryComponent, canActivate: [AuthGuard], children: [
    {path: 'find-users', component: FindUsersComponent},
    {path: 'segments', component: SegmentsComponent}
  ]},
  {path: 'templates', redirectTo:"templates/email", canActivate: [AuthGuard], pathMatch:"full"},
  {path: 'templates', component: TemplatesComponent, canActivate: [AuthGuard], children: [
    {path: 'email', component: EmailTemplatesComponent},
    {path: 'sms', component: SmsTemplatesComponent}
  ]},
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

