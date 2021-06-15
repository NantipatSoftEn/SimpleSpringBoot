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
    checkPasswordRule3: new FormControl('', Validators.required),
  });

  fieldTextType = false;
  pathImages = 'eye.svg';
  constructor(private userService: UserService, private router: Router) {}

  ngOnInit(): void {}

  onSubmit(): void {
    this.router.navigate(['/loading']);
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
        this.router.navigate(['/register']);
      }
    );
  }

  onChange(value: string): void {
    this.checkRulePassword(value);
  }

  toggleFieldTextType() {
    this.fieldTextType = !this.fieldTextType;
    if (this.fieldTextType) {
      this.pathImages = 'eye.svg';
    } else {
      this.pathImages = 'private.svg';
    }
  }

  checkRulePassword(p: string): void {
    const rule1 = this.registerFormGroup.controls.checkPasswordRule1;
    const rule2 = this.registerFormGroup.controls.checkPasswordRule2;
    const rule3 = this.registerFormGroup.controls.checkPasswordRule3;

    const strRegExp = /^[a-zA-Z0-9]*$/;
    const strAndRangeRegExp = /^[a-zA-Z0-9]{8,25}$/;
    const strCapitalRegExp = /[A-Z]/;

    this.IsRuleCorrect(p, strRegExp, rule1);
    this.IsRuleCorrect(p, strAndRangeRegExp, rule2);
    this.IsRuleCorrect(p, strCapitalRegExp, rule3);

    if (p === '') {
      rule1.setValue(false);
    }
  }

  IsRuleCorrect(password: string, RegExp: RegExp, rule: any): boolean {
    if (RegExp.test(password)) {
      rule.setValue(true);
      return true;
    } else {
      rule.setValue(false);
      return false;
    }
  }
}
