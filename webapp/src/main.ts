import { bootstrap } from '@angular/platform-browser-dynamic';
import { enableProdMode } from '@angular/core';
import { WebappAppComponent, environment } from './app/';

import { Logger, Options, Level } from "angular2-logger/core";

let level = Level.DEBUG;
if (environment.production) {
  enableProdMode();
  level = Level.WARN
}

bootstrap(WebappAppComponent, [ 
  { provide :Options, useValue: <Options>{ level: level }   },
  Logger,
]);
