import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit {
  registerFormGroup: FormGroup = new FormGroup({
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    name: new FormControl('', Validators.required),

    checkPasswordRule1: new FormControl('', Validators.required),
    checkPasswordRule2: new FormControl('', Validators.required),
  });

  constructor(private userService: UserService, private router: Router) {}

  ngOnInit(): void {}

  onSubmit(): void {
    if (this.registerFormGroup.invalid) {
      return;
    }

    let email = this.registerFormGroup.controls.email.value;
    let password = this.registerFormGroup.controls.password.value;
    let name = this.registerFormGroup.controls.name.value;

    this.userService.register(email, password, name).subscribe(
      (response) => {
        console.log(response);
        this.router.navigate(['/login']);
      },
      (error) => {
        alert(error.error.error);
      }
    );
  }
  onChange(value: string): void {
    console.log(value);
    this.checkRulePassword(value);
  }

  checkRulePassword(p: string): void {
    const rule1 = this.registerFormGroup.controls.checkPasswordRule1;
    const rule2 = this.registerFormGroup.controls.checkPasswordRule2;
    var strRegExp = /^[a-zA-Z0-9]*$/;
    var strAndRangeRegExp = /^[a-zA-Z0-9]{8,25}$/;
    if (strRegExp.test(p)) {
      rule1.setValue(true);
      if (strAndRangeRegExp.test(p)) {
        rule2.setValue(true);
      } else {
        rule2.setValue(false);
      }
    } else {
      rule1.setValue(false);
    }
    if (p === '') {
      rule1.setValue(false);
    }
  }
}
