import { Component, OnInit } from '@angular/core';
import { provideNativeDateAdapter } from '@angular/material/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';
@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrl: './admin-home.component.css',
  providers: [provideNativeDateAdapter()]
})
export class AdminHomeComponent implements OnInit {
  selected!: Date | null;
  constructor(private router: Router, private route: ActivatedRoute, private service: AdminService){}
  isComponentReload: Boolean = false;

  totalOverallSale = 0;
  totalSaleAmount = 0.0;

  totalOverallPurchase = 0;
  totalPurchaseAmount = 0.0;

  totalOverallStock = 0;
  totalStockAmount = 0.0;

  ngOnInit(): void {
    this.service.getTotalOverallSale().subscribe({
      next: (res) => {
        this.totalOverallSale = res;
      }
    });

    this.service.getTotalSaleAmount().subscribe({
      next: (res) => {
        this.totalSaleAmount = res;
      }
    });

    this.service.getTotalPurchaseQty().subscribe({
      next: (res) => {
        this.totalOverallPurchase = res;
      }
    });

    this.service.getTotalPurchaseAmount().subscribe({
      next: (res) => {
        this.totalPurchaseAmount = res;
      }
    });

    this.service.getTotalStockQty().subscribe({
      next: (res) => {
        this.totalOverallStock = res;
      }
    });

    this.service.getTotalStockAmount().subscribe({
      next: (res) => {
        this.totalStockAmount = res;
      }
    });


  }



  reloadPage() {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate(['./'], {
      relativeTo: this.route
    })
  }
}
