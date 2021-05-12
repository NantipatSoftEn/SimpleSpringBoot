import { RouterModule, Routes } from '@angular/router';

import { ChatComponent } from './components/chat/chat.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
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
    path: `dashboard`,
    component: DashboardComponent,
  },
  {
    path: `chat`,
    component: ChatComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
