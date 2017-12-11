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

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: '', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'register', component: RegisterComponent},
  {path: 'dummyJson', component: DummyComponent},
  {path: 'settings', redirectTo:"settings/profile", pathMatch:"full"},
  {path: 'settings', component: SettingsComponent, children: [
    {path: 'profile', component: ProfileComponent},
    {path: 'service-provider-settings', component: ServiceprovidersComponent}
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

