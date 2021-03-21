import { TestBed } from '@angular/core/testing';

import { UserbysiteService } from './userbysite.service';

describe('UserbysiteService', () => {
  let service: UserbysiteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserbysiteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
