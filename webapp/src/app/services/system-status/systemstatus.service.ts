import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import {Logger} from "angular2-logger/core";

// Used before simulate HTTP
import { Status } from './status';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class SystemstatusService {

  private statusUrl = 'http://localhost:9080/api/v1/system-status';  // URL to web api
  constructor(private http: Http, private _logger:Logger) {
    this._logger.debug('SystemstatusService constructor');
   }

  getStatus(): Promise<Status> {
    this._logger.debug('getStatus start');
    /**
     * We're still returning a Promise but we're creating it differently.
     * The Angular http.get returns an RxJS Observable. Observables are a powerful 
     * way to manage asynchronous data flows. We'll learn about Observables later.
     * 
     */
    return this.http.get(this.statusUrl)
      // For now we get back on familiar ground by immediately converting that Observable to a Promise using the toPromise operator.
      // There are scores of operators like toPromise that extend Observable with useful capabilities.
      // If we want those capabilities, we have to add the operators ourselves. That's as easy as importing them from the RxJS
      .toPromise()
      // In the promise's then callback we call the json method of the http Response to extract the data within the response.
      // That response JSON has a single data property. 
      // The data property holds the array of heroes that the caller really wants. 
      // So we grab that array and return it as the resolved Promise value.
      // This particular in-memory web API example happens to return an object with a data property. Your API might return something else.
      // Adjust the code to match your web API.
      .then(response => response.json())
      // This is a critical step! We must anticipate HTTP failures as they happen frequently for reasons beyond our control.
      .catch(this.handleError);
  }


  private handleError(error: any) {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}
