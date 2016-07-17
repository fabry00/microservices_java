import {SubSystemStatus} from './subsystemstatus' 

export class Status {
  name: string;
  description: string;
  status : string;

  subSystemsStatus : SubSystemStatus[]; 
}
