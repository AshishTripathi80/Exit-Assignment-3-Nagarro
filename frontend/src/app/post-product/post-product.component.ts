import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-post-product',
  templateUrl: './post-product.component.html',
  styleUrl: './post-product.component.scss', 
})
export class PostProductComponent {
  productForm!: FormGroup; 
  selectedFile: File | null = null;
  imagePreview: String | ArrayBuffer | null = null;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private productService: ProductService,
  ) {}

  ngOnInit(): void {
    this.productForm = this.fb.group({
      name: [null, [Validators.required]],
      price: [null, [Validators.required]],
      description: [null, [Validators.required]],
      brand: [null, [Validators.required]],
    });
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0] || null;
    this.previewImage();
  }

  previewImage() {
    if (this.selectedFile) {
      const reader = new FileReader();
      reader.onloadend = () => {
        this.imagePreview = reader.result;
      };
      reader.readAsDataURL(this.selectedFile);
    }
  }

  addProduct(): void {
    if (this.productForm.valid) {
      const formData = new FormData();
      if (this.selectedFile) {
        formData.append('img', this.selectedFile);
      }

      formData.append('name', this.productForm.get('name')!.value); 
      formData.append('price', this.productForm.get('price')!.value);
      formData.append('description', this.productForm.get('description')!.value);
      formData.append('brand', this.productForm.get('brand')!.value);

      this.productService.addProduct(formData).subscribe((res) => {
        if (res.id != null) {
          this.snackBar.open('Product Added Successfully', 'Close', {
            duration: 5000,
          });
          this.router.navigateByUrl('/dashboard');
        } else {
          this.snackBar.open(res.message, 'Error', { duration: 5000 });
        }
      });
    } else {
      for (const i in this.productForm.controls) {
        if (this.productForm.controls.hasOwnProperty(i)) {
          this.productForm.controls[i].markAsDirty();
          this.productForm.controls[i].updateValueAndValidity();
        }
      }
    }
  }
}
