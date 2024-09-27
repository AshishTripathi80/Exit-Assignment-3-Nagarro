import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrl: './review.component.scss',
})
export class ReviewComponent {
  productId!: number;
  reviews: any[] = [];
  productName: string = '';

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService
  ) {}

  ngOnInit(): void {
    this.productId = Number(this.route.snapshot.paramMap.get('id'));
    this.getReviews(this.productId);
     this.getProductDetails(this.productId); 
  }

  getReviews(productId: number): void {
    this.productService.getReviewsByProductId(productId).subscribe(
      (reviews) => {
        this.reviews = reviews;
      },
      (error) => {
        console.error('Error fetching reviews:', error);
      }
    );
  }

  getProductDetails(productId: number): void {
    this.productService.getProductById(productId).subscribe(
      (product) => {
        this.productName = product.name; // Set the product name
      },
      (error) => {
        console.error('Error fetching product details:', error);
      }
    );
  }
}
