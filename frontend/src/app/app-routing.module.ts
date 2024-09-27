import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PostProductComponent } from './post-product/post-product.component';
import { ProductComponent } from './product/product.component';
import { PostReviewComponent } from './post-review/post-review.component';
import { ReviewComponent } from './review/review.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'post-product', component: PostProductComponent },
  { path: 'product', component: ProductComponent },
  { path: 'post-review/:productId', component: PostReviewComponent },
  { path: 'view-reviews/:id', component: ReviewComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
