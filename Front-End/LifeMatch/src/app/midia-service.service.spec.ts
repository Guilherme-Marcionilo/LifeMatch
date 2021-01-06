import { TestBed } from '@angular/core/testing';

import { MidiaServiceService } from './midia-service.service';

describe('MidiaServiceService', () => {
  let service: MidiaServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MidiaServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
