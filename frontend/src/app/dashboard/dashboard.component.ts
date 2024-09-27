import { Component } from '@angular/core';
import { StateService } from '../services/state.service';
import { ProductService } from '../services/product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss',
})
export class DashboardComponent {
  totalUsers: number = 0;
  onlineUsers: number = 0;
  totalProducts: number = 0;

  searchTerm: string = '';
  products: any[] = [];

  constructor(private stateService: StateService,
    private productService: ProductService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.fetchStateData();
  }

  // Method to fetch the state data
  fetchStateData(): void {
    this.stateService.getState().subscribe(
      (data) => {
        this.totalUsers = data.totalUsers;
        this.onlineUsers = data.onlineUsers;
        this.totalProducts = data.totalProducts;
      },
      (error) => {
        console.error('Error fetching state data', error);
      }
    );
  }

  onSearch(): void {
    if (this.searchTerm) {
      this.productService.getProductByName(this.searchTerm).subscribe(
        (response) => {
          this.products = response;
          this.router.navigate(['/product'], {
            state: { products: this.products },
          });
        },
        (error) => {
          console.error('Error fetching products:', error);
        }
      );
    }
  }
}
