import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


const BASIC_URL = 'http://localhost:9002/api/users/';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  register(signupRequest: any): Observable<any> {
    return this.http.post(BASIC_URL + 'register', signupRequest);
  }

  login(email: string, password: string): Observable<any> {
    const params = new HttpParams()
      .set('email', email)
      .set('password', password);

    return this.http.post(BASIC_URL + 'login', {}, { params });
  }

  logout(email: string): Observable<any> {
    const params = new HttpParams().set('email', email);
    return this.http.post(BASIC_URL + 'logout', {}, { params });
  }
}
