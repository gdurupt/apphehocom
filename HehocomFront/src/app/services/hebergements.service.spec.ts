import { TestBed } from '@angular/core/testing';

import { HebergementsService } from './hebergements.service';

describe('HebergementsService', () => {
  let service: HebergementsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HebergementsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
