import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {LogInComponent} from './components/log-in/log-in.component';
import { KontaktiComponent } from './components/kontakti/kontakti.component';
import { RouterModule, Routes } from "@angular/router";
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { ViewComponent } from './components/view/view.component';

const appRoutes: Routes = [
  {path: '', component:LogInComponent},
  {path: 'about', component:KontaktiComponent},
  {path: 'registration', component:SignUpComponent},
  {path: 'view', component:ViewComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    LogInComponent,
    KontaktiComponent,
    SignUpComponent,
    ViewComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
