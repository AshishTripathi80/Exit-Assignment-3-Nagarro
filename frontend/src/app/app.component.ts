import { Component } from '@angular/core';
import { UserStorageService } from './services/storage/user-storage.service';
import { NavigationEnd, Router } from '@angular/router';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'Product-Catalog'; 

  isLoggedIn: boolean = false; 
  userName: string | null = '';

  constructor(
    private userStorageService: UserStorageService,
    private router: Router,
    private authService: AuthService,
  ) {}

  ngOnInit(): void {
    // Set initial login status and username if logged in
    this.updateLoginStatus();

    // Listen to router events to update login status after route changes
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.updateLoginStatus();
      }
    });
  }

  updateLoginStatus(): void {
    this.isLoggedIn = this.userStorageService.isLoggedIn();
    if (this.isLoggedIn) {
      const user = this.userStorageService.getUser();
      this.userName = user ? user.name : null;
    } else {
      this.userName = null;
    }
  }

  logout(): void {
    const email = this.userStorageService.getUserEmail(); // Assuming you have this method to get the user's email

    this.authService.logout(email!).subscribe(
      (response) => {
        console.log('Logout successful:', response);
        this.userStorageService.logout(); // Clear the user data from storage
        this.router.navigateByUrl('login');
      },
      (error) => {
        console.error('Logout failed:', error);
      }
    );
  }
}
