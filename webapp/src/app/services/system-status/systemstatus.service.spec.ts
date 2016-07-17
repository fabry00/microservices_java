import {
  beforeEachProviders,
  it,
  describe,
  expect,
  inject
} from '@angular/core/testing';
import { SystemstatusService } from './systemstatus.service';

describe('Systemstatus Service', () => {
  beforeEachProviders(() => [SystemstatusService]);

  it('should ...',
      inject([SystemstatusService], (service: SystemstatusService) => {
    expect(service).toBeTruthy();
  }));
});
