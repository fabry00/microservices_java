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

  task : Task;

  constructor(private _logger:Logger){
    this._logger.debug('TaskComponent constructor');
  }

  ngOnInit() {
    this.task = new Task();
    this.task.name = "Test";
    this.task.id = 2;
  }

}
