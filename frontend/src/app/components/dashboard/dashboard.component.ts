import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

import { AppCookieService } from 'src/app/services/app-cookie.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit {
  private token: any;
  constructor(
    private userService: UserService,
    private router: Router,
    private activateRoute: ActivatedRoute,
    private appCookieService: AppCookieService
  ) {}
  users = null;
  ngOnInit(): void {
    this.token = this.appCookieService.getAccessToken();
    console.log(this.token);

    this.userService.getAllUsers(this.token).subscribe((data) => {
      console.log(data);
      this.users = data;
    });
    // console.log(`users`, users.subscribe());
  }
}
