import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Patients } from 'src/app/interfaces/patients';

@Component({
	selector: 'sacchon-app-medi-data-repo-login',
	templateUrl: './medi-data-repo-login.component.html',
	styleUrls: ['./medi-data-repo-login.component.scss']
})
export class MediDataRepoLoginComponent implements OnInit {

	constructor(private http: HttpClient, private router: Router, private toastr: ToastrService) {	}

	patientLoginForm = new FormGroup({
		email: new FormControl(null, [Validators.required, Validators.email]),
		password: new FormControl(null, [Validators.required, Validators.minLength(8)]),
	});


	ngOnInit(): void {
	}
	
	submitLogin(): any {
		this.http.post<Patients>('http://localhost:9000/v1/login/patient', {
			email: this.patientLoginForm.get('email').value,
			password: this.patientLoginForm.get('password').value
		}).subscribe(response => {
			if (response) {
				this.toastr.success(`Welcome ${response.data.firstName}!`, 'Login successful', {
					timeOut: 2000,
					positionClass: 'toast-top-center'
				}).onHidden.toPromise().then(_ => {
					console.log(response.status == 200);
					sessionStorage.setItem('email', response.data.email);
					sessionStorage.setItem('password', response.data.password);
					sessionStorage.setItem('firstName', response.data.firstName);
					sessionStorage.setItem('lastName', response.data.lastName);
					sessionStorage.setItem('id', response.data.id);
					sessionStorage.setItem('dob', response.data.dob);
					sessionStorage.setItem('canBeExamined', response.data.canBeExamined);
					sessionStorage.setItem('lastActive', response.data.lastActive);
					sessionStorage.setItem('gender', response.data.gender);
					this.router.navigate(['medidatarepo/profile']);
				})
			} else {
				this.toastr.error('Invalid credentials', 'Login Unsuccessful')
			}
		})
	}
}