import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpModule } from '@angular/http';
 
 //used to create fake backend
import { fakeBackendProvider } from './_helpers/index';
import { MockBackend, MockConnection } from '@angular/http/testing';
import { BaseRequestOptions } from '@angular/http';
 
import { AppComponent }  from './app.component';
import { routing }        from './app.routing';
 
import { AuthGuard } from './_guards/index';
import { AuthenticationService, UserService } from './_services/index';
import { LoginComponent } from './login/index';
import { HomeComponent } from './home/index';
import { HeaderComponent } from './header/index';
import { RegisterComponent } from './register/index';
import { DummyComponent } from './dummy/dummy.component';
import { HttpClientModule } from '@angular/common/http';
import { ProfileComponent } from './profile/profile.component';



 
@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        routing,
        HttpClientModule,
    ],
    declarations: [
        AppComponent,
        LoginComponent,
        HomeComponent,
        HeaderComponent,
        RegisterComponent,
        DummyComponent,
        ProfileComponent
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
 
export class AppModule { }