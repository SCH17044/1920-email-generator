import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {UserService} from '../service/user.service';
import {first} from 'rxjs/operators';
import {AuthenticationService} from '../service/authentication.service';
import {AlertService} from '../service/alter.service';

@Component({ templateUrl: 'register.component.html' })
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  loading = false;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
    private alertService: AlertService,
    private authenticationService: AuthenticationService
  ) {
    if (this.authenticationService.currentUserValue) {
      this.router.navigate(['/home']);
    }
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  get f() { return this.registerForm.controls; }

  onSubmit() {
    this.submitted = true; // sets the Validators in Action
    this.alertService.clear(); // reset alerts on submit

    if (this.registerForm.invalid) {
      return;
    }
    this.loading = true; // Loading symbol in button when fetching data
    this.userService.register(this.registerForm.value)
      .pipe(first())
      .subscribe(
        data => {
          this.alertService.success('Registrierung erfolgreich!', true);
          this.router.navigate(['/login']);
        },
        error => {
          this.alertService.error('Diese E-Mail Adresse ist leider schon vorhanden!');
          this.loading = false;
        });
  }
}
