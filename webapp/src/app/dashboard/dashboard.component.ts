import { Component, OnInit } from '@angular/core';

import {Logger} from "angular2-logger/core";
import { TaskComponent } from '../task';
import { SystemStatusComponent } from '../system-status';

@Component({
  moduleId: module.id,
  selector: 'app-dashboard',
  templateUrl: 'dashboard.component.html',
  styleUrls: ['dashboard.component.css'],
  directives: [TaskComponent, SystemStatusComponent] 
  
})
export class DashboardComponent implements OnInit {

  constructor(private _logger:Logger){
    this._logger.debug('DashboardComponent constructor');
  }

  ngOnInit() {
  }

}
