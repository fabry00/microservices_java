import {
  beforeEachProviders,
  describe,
  expect,
  it,
  inject
} from '@angular/core/testing';
import { WebappAppComponent } from '../app/webapp.component';

beforeEachProviders(() => [WebappAppComponent]);

describe('App: Webapp', () => {
  it('should create the app',
      inject([WebappAppComponent], (app: WebappAppComponent) => {
    expect(app).toBeTruthy();
  }));

  it('should have as title \'webapp works!\'',
      inject([WebappAppComponent], (app: WebappAppComponent) => {
    expect(app.title).toEqual('webapp works!');
  }));
});
