import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ChartsModule } from 'ng2-charts';
import { DataTablesModule } from 'angular-datatables';
import { RouterModule } from '@angular/router';
import { DoctorAdviceComponent } from './doctor-advice.component';
import { ProfileComponent } from './profile/profile.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { PatientComponent } from './patient/patient.component';
import { PatientListComponent } from './patient/patient-list/patient-list.component';
import { ConsultComponent } from './consult/consult.component';
import { ConsultEditComponent } from './consult/consult-edit/consult-edit.component';
import { AddConsultComponent } from './consult/add-consult/add-consult.component';



@NgModule({
	declarations: [
		DoctorAdviceComponent,
		ProfileComponent,
		EditProfileComponent,
		PatientComponent,
		PatientListComponent,
		ConsultComponent,
		ConsultEditComponent,
		AddConsultComponent,
	],
	imports: [
		CommonModule,
		DataTablesModule,
		ChartsModule,
		HttpClientModule,
		RouterModule.forChild([
			{ path: 'doctoradvice', component: DoctorAdviceComponent },
			{ path: 'doctoradvice/profile', component: ProfileComponent },
			{ path: 'doctoradvice/profile/edit', component: EditProfileComponent },
			{ path: 'doctoradvice/patients', component: PatientComponent },
			{ path: 'doctoradvice/patient/:id', component: PatientListComponent },
			{ path: 'doctoradvice/consult/:id', component: ConsultComponent },
			{ path: 'doctoradvice/consult/:id/add', component: AddConsultComponent },
			{ path: 'doctoradvice/consult/:id/edit', component: ConsultEditComponent },
		])
	],
	exports: [
		RouterModule
	]
})
export class DoctorAdviceModule { }
