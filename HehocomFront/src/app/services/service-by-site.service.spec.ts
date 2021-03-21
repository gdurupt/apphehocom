import { TestBed } from '@angular/core/testing';

import { ServiceBySiteService } from './service-by-site.service';

describe('ServiceBySiteService', () => {
  let service: ServiceBySiteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceBySiteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
