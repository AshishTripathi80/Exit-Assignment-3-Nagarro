import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrl: './product.component.scss',
})
export class ProductComponent {
  products: any[] = [];

  constructor(private router: Router) {
    const navigation = this.router.getCurrentNavigation();
    this.products = navigation?.extras?.state?.['products'] || [];
  }

  ngOnInit(): void {
    this.processProductImages();
  }

  processProductImages(): void {
    // Assuming products already have 'byteImg', we process it here
    this.products.forEach(
      (product: { processedImg: string; byteImg: string }) => {
        product.processedImg = 'data:image/jpeg;base64,' + product.byteImg;
      }
    );
  }
}
