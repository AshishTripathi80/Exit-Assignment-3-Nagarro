import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const STATE_URL = 'http://localhost:9002/api/state/';

@Injectable({
  providedIn: 'root',
})
export class StateService {
  constructor(private http: HttpClient) {}

  // Method to fetch the state data from the backend
  getState(): Observable<any> {
    return this.http.get(STATE_URL+'getState');
  }
}
