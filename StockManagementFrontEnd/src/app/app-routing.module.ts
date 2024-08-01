import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignUpComponent } from './auth/components/sign-up/sign-up.component';
import { SignInComponent } from './auth/components/sign-in/sign-in.component';
import { AdminDashboardComponent } from './modules/admin/components/admin-dashboard/admin-dashboard.component';
import { AdminHomeComponent } from './modules/admin/components/admin-home/admin-home.component';
import { AddProductComponent } from './modules/admin/components/add-product/add-product.component';
import { AdminSidebarComponent } from './modules/admin/components/admin-sidebar/admin-sidebar.component';
import { AddStockComponent } from './modules/admin/components/add-stock/add-stock.component';
import { AddSalesComponent } from './modules/admin/components/add-sales/add-sales.component';
import { AddCustomerComponent } from './modules/admin/components/add-customer/add-customer.component';
import { ShowDataComponent } from './modules/admin/components/show-data/show-data.component';
import { ShowPurchasesComponent } from './modules/admin/components/show-purchases/show-purchases.component';
import { ShowSalesComponent } from './modules/admin/components/show-sales/show-sales.component';
import { ShowCustomerComponent } from './modules/admin/components/show-customer/show-customer.component';
import { UserDashboardComponent } from './modules/user/components/user-dashboard/user-dashboard.component';
import { AuthGuard } from './auth/guards/auth.guard';

const routes: Routes = [
  {
    path: '',
    component: SignInComponent
  },
  {
    path: 'registration',
    component: SignUpComponent
  },
  {
    path: 'admin-dashboard',
    canActivate: [AuthGuard],
    component: AdminDashboardComponent,
    children: [
      {
        path: '',
        component: AdminHomeComponent
      },
      {
        path: 'add-product',
        component: AddProductComponent
      },
      {
        path: 'add-stock',
        component: AddStockComponent
      },
      {
        path: 'add-sales',
        component: AddSalesComponent
      },
      {
        path: 'add-customer',
        component: AddCustomerComponent
      },
      {
        path: 'show-data',
        component: ShowDataComponent
      },
      {
        path: 'show-purchases',
        component: ShowPurchasesComponent
      },
      {
        path: 'show-sales',
        component: ShowSalesComponent
      },
      {
        path: 'show-customer',
        component: ShowCustomerComponent
      },

    ]
  },
  {
    path: "user-dashboard",
    component: UserDashboardComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
