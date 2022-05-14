import { TestBed } from '@angular/core/testing';

import { EmployeeService } from './user.service';

describe('UserService', () => {
  let service: EmployeeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
