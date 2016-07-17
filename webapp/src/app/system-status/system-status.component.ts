import { Component, OnInit } from '@angular/core';
import { SubSystemStatus } from './../services/system-status/subsystemstatus';
import { Status } from './../services/system-status/status';
import { SystemstatusService } from './../services/system-status/systemstatus.service'
import {Logger} from "angular2-logger/core";

@Component({
  moduleId: module.id,
  selector: 'app-system-status',
  templateUrl: 'system-status.component.html',
  styleUrls: ['system-status.component.css']
})
export class SystemStatusComponent implements OnInit {

  constructor(private statusService: SystemstatusService,
    private _logger: Logger) {
    this._logger.debug('SystemStatusComponent constructor');
  }

  ngOnInit() {
    this.getStatus();
  }

  getStatus() {
    this.statusService.getStatus().then(/*heroes => this.heroes = heroes*/);
  }
}
