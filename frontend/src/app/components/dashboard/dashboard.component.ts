import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

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
    private activateRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.token = this.activateRoute.snapshot.paramMap.get(`token`);
    if (this.token === null) {
      this.router.navigate([`/login`]);
      return;
    }
    this.userService.getAllUsers(this.token);
  }
}
