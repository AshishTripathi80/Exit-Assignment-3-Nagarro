import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ProductService } from '../services/product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrl: './product.component.scss',
})
export class ProductComponent {
  products: any[] = [];
  //searchTerm: string = '';

  constructor(
    private router:Router
  ) {
    const navigation = this.router.getCurrentNavigation();
    this.products = navigation?.extras?.state?.['products'] || [];
  }

  ngOnInit(): void {
  }

  
}
