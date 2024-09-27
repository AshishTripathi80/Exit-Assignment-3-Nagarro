import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-post-review',
  templateUrl: './post-review.component.html',
  styleUrl: './post-review.component.scss',
})
export class PostReviewComponent {
  productId: number = this.activatedRoute.snapshot.params['productId'];
  reviewForm!: FormGroup;
  selectedFile: File | null;
  imagePreview: string | ArrayBuffer | null;

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private productService: ProductService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit() {
    this.reviewForm = this.fb.group({
      rating: [null, [Validators.required]],
      description: [null, [Validators.required]],
    });
  }

  submitForm() {
    const formData: FormData = new FormData();
    formData.append('productId', this.productId.toString());
    formData.append('rating', this.reviewForm.get('rating')!.value);
    formData.append('description', this.reviewForm.get('description')!.value);

    this.productService.postReview(formData).subscribe((res) => {
      if (res.id != null) {
        this.snackBar.open('Review Posted Successfully!', 'Close', {
          duration: 5000,
        });
        this.router.navigateByUrl('/dashboard');
      } else {
        this.snackBar.open('Something went wrong', 'ERROR', {
          duration: 5000,
        });
      }
    });
  }
}
