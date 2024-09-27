import { Injectable } from '@angular/core';


const USER = 'product-catlog-user';

@Injectable({
  providedIn: 'root',
})
export class UserStorageService {
  constructor() {}

  // Save the user information (e.g., on login)
  public saveUser(user: any): void {
    localStorage.setItem(USER, JSON.stringify(user));
  }

  // Get the logged-in user information
  public getUser(): any {
    const user = localStorage.getItem(USER);
    if (user) {
      return JSON.parse(user);
    }
    return null;
  }

  // Get the logged-in user's email
  public getUserEmail(): string | null {
    const user = this.getUser();
    if (user && user.email) {
      return user.email;
    }
    return null;
  }
  
  // Check if the user is logged in
  public isLoggedIn(): boolean {
    return this.getUser() !== null;
  }

  // Remove the user information (e.g., on logout)
  public logout(): void {
    localStorage.removeItem(USER);
  }
}
