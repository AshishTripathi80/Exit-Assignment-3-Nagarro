import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASIC_URL = 'http://localhost:9002/api/products/';
const REVIEW_URL = 'http://localhost:9002/api/reviews/'; 

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private http: HttpClient) {}

  addProduct(productDto: any): Observable<any> {
    return this.http.post(BASIC_URL, productDto);
  }

  getProductById(productId: number): Observable<any> {
    return this.http.get(BASIC_URL + productId);
  }

  getSearchedProduct(): Observable<any> {
    return this.http.get(BASIC_URL + 'search');
  }

  getProductByName(name: string): Observable<any> {
    return this.http.get<any>(BASIC_URL + `name/${name}`);
  }

  postReview(reviewDto: any): Observable<any> {
    return this.http.post(REVIEW_URL, reviewDto);
  }

  getReviewsByProductId(productId: number): Observable<any> {
    return this.http.get(REVIEW_URL + productId);
  }
}
