import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrls: ['./activate-account.component.scss'],
})
export class ActivateAccountComponent implements OnInit {
  private token: any;
  isSuccess = false;

  isErrorTokenExpired = false;
  isErrorAccountAlreadyActivated = false;

  errorMessage = '';

  constructor(
    private activateRoute: ActivatedRoute,
    private router: Router,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.token = this.activateRoute.snapshot.paramMap.get(`token`);
    if (this.token === null) {
      this.router.navigate([`/login`]);
      return;
    }
    this.activateAccount();
  }

  private activateAccount() {
    this.userService.activateAccount(<string>this.token).subscribe(
      () => {
        // success
        this.isSuccess = true;
      },
      (error) => {
        // failture
        console.log(error);
        let code = error.error.error;

        // this.translateService.get(code, {}).subscribe((res: string) => {
        //   this.errorMessage = res;
        // });

        if (code === 'user.activate.already') {
          this.isSuccess = false;
          this.isErrorAccountAlreadyActivated = true;
        } else if (code === 'user.activate.token.expire') {
          this.isSuccess = false;

          this.isErrorTokenExpired = true;
        } else {
          this.isSuccess = false;
        }
      }
    );
  }

  resendActivationEmail() {
    this.userService.resendActivationEmail(this.token as string).subscribe(
      () => {
        // success
      },
      (error) => {
        console.log(error);
      }
    );
  }

  navigateToLoginPage() {
    this.router.navigate(['/login']);
  }
}
