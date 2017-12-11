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
import {AppRoutingModule} from './/app-routing.module';
import { SettingsComponent } from './settings/settings.component';


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
    SettingsComponent
  ],
  providers: [
    AuthGuard,
    AuthenticationService,
    UserService,
    // fakeBackendProvider,
    MockBackend,
    // BaseRequestOptions
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}
