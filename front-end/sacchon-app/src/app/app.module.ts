import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { ContentComponent } from './content/content.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { ChiefDoctorModule } from './chief-doctor/chief-doctor.module';

@NgModule({
	declarations: [
		AppComponent,
		HeaderComponent,
		FooterComponent,
		ContentComponent,
		LoginComponent,
		SignupComponent
	],
	imports: [
		BrowserModule,
		AppRoutingModule,
		ChiefDoctorModule
	],
	providers: [],
	bootstrap: [AppComponent]
})
export class AppModule { }
