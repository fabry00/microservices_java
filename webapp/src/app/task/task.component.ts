import { Component, OnInit } from '@angular/core';
import {Logger} from "angular2-logger/core";
import { Task } from './task'

@Component({
  moduleId: module.id,
  selector: 'app-task',
  templateUrl: 'task.component.html',
  styleUrls: ['task.component.css']
})
export class TaskComponent implements OnInit {

  tasks : Task[];

  constructor(private _logger:Logger){
    this._logger.debug('TaskComponent constructor');
  }

  ngOnInit() {
    this._logger.debug('ngOnInit');
    this.tasks = new Array(1);
    this.tasks[0] = new Task();
    this.tasks[0].name = "Test";
    this.tasks[0].id = 2;
  }

}
