import { Component } from '@angular/core';
import {Logger} from "angular2-logger/core";
import { DashboardComponent } from './dashboard';
import { TaskComponent } from './task';

@Component({
  moduleId: module.id,
  selector: 'webapp-app',
  templateUrl: 'webapp.component.html',
  styleUrls: ['webapp.component.css'],
  directives: [DashboardComponent, TaskComponent] 
})
export class WebappAppComponent {
  title = 'webapp works!';

  constructor(private _logger:Logger){
    this._logger.debug('WebappAppComponent constructor');
  }
}
