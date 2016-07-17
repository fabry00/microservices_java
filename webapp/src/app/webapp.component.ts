import { Component } from '@angular/core';
import {Logger} from "angular2-logger/core";
import { DashboardComponent } from './dashboard';
import { TaskComponent } from './task';
import { SystemstatusService } from './services/system-status/systemstatus.service'


@Component({
  moduleId: module.id,
  selector: 'webapp-app',
  templateUrl: 'webapp.component.html',
  styleUrls: ['webapp.component.css'],
  directives: [DashboardComponent, TaskComponent],
  providers: [
    /**
     * We'd like to re-use the SystemstatusService to populate the component's heroes array.
     * Recall earlier in the chapter that we removed the SystemstatusService from the providers array of the HeroesComponent and added it to the providers array of the top level AppComponent.
     * That move created a singleton SystemstatusService instance, available to all components of the application. Angular will inject HeroService and we'll use it here in the DashboardComponent.
     */
    SystemstatusService
  ]
})
export class WebappAppComponent {
  title = 'webapp works!';

  constructor(private _logger:Logger){
    this._logger.debug('WebappAppComponent constructor');
  }
}
