import { RouterModule, Routes } from '@angular/router';

import { ActivateAccountComponent } from './components/activate-account/activate-account.component';
import { AuthGuardService } from './services/auth-guard.service';
import { ChatComponent } from './components/chat/chat.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { LoadingComponent } from './loading/loading.component';
import { LoginComponent } from './components/login/login.component';
import { NgModule } from '@angular/core';
import { RegisterComponent } from './components/register/register.component';

const routes: Routes = [
  {
    path: `login`,
    component: LoginComponent,
  },
  {
    path: `register`,
    component: RegisterComponent,
  },
  {
    path: `activate/:token`,
    component: ActivateAccountComponent,
  },
  {
    path: `dashboard`,
    component: DashboardComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: `chat`,
    component: ChatComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: `loading`,
    component: LoadingComponent,
  },
  {
    path: ``,
    component: DashboardComponent,
    canActivate: [AuthGuardService],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
