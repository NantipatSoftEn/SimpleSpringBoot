import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { UserService } from './user.service';

describe('UserService', () => {
  let service: UserService;
  let email: string = 'Test@gmail.com';
  let passward: string = 'Armlovemint1150';
  let name: string = 'TestMan';
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule],
    });
    service = TestBed.inject(UserService);
  });

  it('register should return  email and name', () => {
    service.register(email, passward, name).subscribe((response) => {
      expect(response.email).toBe(email);
      expect(response.name).toBe(name);
    });
  });

  it('login should return token length 223 ', () => {
    service.login(email, passward).subscribe((response) => {
      expect(response.token.length).toBe(223);
    });
  });
});
