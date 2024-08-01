import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { SignUpComponent } from './auth/components/sign-up/sign-up.component';
import { SignInComponent } from './auth/components/sign-in/sign-in.component';
import { AdminDashboardComponent } from './modules/admin/components/admin-dashboard/admin-dashboard.component';
import { AdminHomeComponent } from './modules/admin/components/admin-home/admin-home.component';
import { AdminNavbarComponent } from './modules/admin/components/admin-navbar/admin-navbar.component';
import { AdminSidebarComponent } from './modules/admin/components/admin-sidebar/admin-sidebar.component';
import { AddProductComponent } from './modules/admin/components/add-product/add-product.component';
import { AddStockComponent } from './modules/admin/components/add-stock/add-stock.component';
import { AddSalesComponent } from './modules/admin/components/add-sales/add-sales.component';
import { AddCustomerComponent } from './modules/admin/components/add-customer/add-customer.component';
import { ShowDataComponent } from './modules/admin/components/show-data/show-data.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

// Angular Material Modules
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatNativeDateModule } from '@angular/material/core';
import { MatTableModule } from '@angular/material/table';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { ShowPurchasesComponent } from './modules/admin/components/show-purchases/show-purchases.component';
import { ShowSalesComponent } from './modules/admin/components/show-sales/show-sales.component';
import { ShowCustomerComponent } from './modules/admin/components/show-customer/show-customer.component';
import { ToastrModule } from 'ngx-toastr'
import { HttpClientModule } from '@angular/common/http';
import { UserDashboardComponent } from './modules/user/components/user-dashboard/user-dashboard.component';
import { AuthGuard } from './auth/guards/auth.guard';

import {MatTooltipModule} from '@angular/material/tooltip';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatListModule} from '@angular/material/list';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatDialogModule} from '@angular/material/dialog';
import { authInterceptor } from './auth/services/auth.interceptor';


@NgModule({
  declarations: [
    AppComponent,
    SignUpComponent,
    SignInComponent,
    AdminDashboardComponent,
    AdminHomeComponent,
    AdminNavbarComponent,
    AdminSidebarComponent,
    AddProductComponent,
    AddStockComponent,
    AddSalesComponent,
    AddCustomerComponent,
    ShowDataComponent,
    ShowPurchasesComponent,
    ShowSalesComponent,
    ShowCustomerComponent,
    UserDashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    CommonModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatNativeDateModule,
    MatTableModule,
    MatSelectModule,
    MatButtonModule,
    HttpClientModule,
    MatTooltipModule,
    MatExpansionModule,
    MatListModule,
    MatPaginatorModule,
    MatDialogModule,
    ToastrModule.forRoot()
  ],
  providers: [
    provideClientHydration(),
    provideAnimationsAsync(),
    AuthGuard,
    authInterceptor
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
