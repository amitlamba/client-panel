import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/index';
import { HomeComponent } from './home/index';
import { AuthGuard } from './_guards/index';
import { RegisterComponent } from './register/index';
import { ProfileComponent } from './profile/index';
import { DummyComponent } from './dummy/dummy.component';

const appRoutes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: '', component: HomeComponent, canActivate: [AuthGuard]},
    { path: 'profile', component: ProfileComponent},
    {path: 'register', component: RegisterComponent },
    {path: 'dummyJson', component: DummyComponent },

];

export const routing = RouterModule.forRoot(appRoutes);