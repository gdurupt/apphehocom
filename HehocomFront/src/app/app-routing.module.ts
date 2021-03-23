import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ContactComponent } from './contact/contact.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { GestionUserComponent } from './gestion-user/gestion-user.component';
import { HebergementComponent } from './hebergement/hebergement.component';
import { LoginComponent } from './login/login.component';
import { ServiceComponent } from './service/service.component';
import { SiteComponent } from './site/site.component';

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'service', component: ServiceComponent},
  {path: 'contact', component: ContactComponent},
  {path: 'site/:name', component: SiteComponent},
  {path: 'hebergement', component: HebergementComponent},
  {path: 'gestionUser', component: GestionUserComponent},
  {path: '**', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
