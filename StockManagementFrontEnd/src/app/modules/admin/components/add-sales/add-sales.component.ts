import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { AdminService } from '../../services/admin.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

export interface Sales {
  position: number;
  productId: string;
  productCost: number;
  quantity: number;
  date: string;
  price: number;
}

@Component({
  selector: 'app-add-sales',
  templateUrl: './add-sales.component.html',
  styleUrls: ['./add-sales.component.css']
})
export class AddSalesComponent implements OnInit {
  purchaseIds: string[] = [];
  productQty: number[] = [];
  totalSaleAmount: number = 0;
  totalSaleQty: number = 0;
  form: FormGroup;
  dataSource = new MatTableDataSource<Sales>();
  displayedColumns: string[] = ['position', 'productId', 'productCost', 'quantity', 'date', 'price'];
  saleCounter: number = 1;
  constructor(
    private fb: FormBuilder,
    private service: AdminService,
    private router: Router,
    private toast: ToastrService
  ) {

    this.form = this.fb.group({
      purchaseId: [''],
      productCategory: [''],
      productId: [''],
      productName: [''],
      productCost: [''],
      quantity: [''],
      date: [new Date()], // Set the default date to the current date
      price: [''],
      customer: [''],
      contact: [''],
      email: [''],
      address: [''],
    });
  }

  saleData = {
    products: [] as { productId: string }[],
    productQuantity: [] as { productQty: number }[],
    totalSaleQuantity: this.totalSaleQty,
    totalSaleAmount: this.totalSaleAmount,
    customer: {
      customerName: "",
      customerEmail: "",
      customerContact: "",
      customerAddress: "",
    }
  }

  suppliers = [
    {
      supplierId: "",
      supplierName: "",
      supplierEmail: "",
      supplierContact: "",
      supplierAddress: ""
    }
  ]

  products = []

  loadedProducts = [
    {
      productId: "",
      productName: "",
      productHSNNo: "",
      productDescription: "",
      productCategory: "",
      productCost: "",
      productDate: "",
    }
  ]

  product = {
    productId: 0,
    productName: "",
    productHSNNo: "",
    productDescription: "",
    productCategory: "",
    productCost: "",
    productDate: "",
  }

  loadedSignleProduct = {
    productId: 0,
    productName: "",
    productHSNNo: "",
    productDescription: "",
    productCategory: "",
    productCost: 0.0,
    productDate: "",
  }

  purchase = {
    purchaseId: 0
  }

  ngOnInit() {
    this.service.getSuppliers().subscribe(res => {
      if (res !== null) {
        this.suppliers = res;
      }
    });
    this.form.get('quantity')?.valueChanges.subscribe(() => this.calculateTotalPrice());

    this.service.getAllProductsCategory().subscribe(res => {
      if (res !== null) {
        this.products = res;
      }
    });
    this.service.getLastPurchase().subscribe(res => {
      if (res !== null) {
        this.purchase = res;
      }
    });

  }

  @ViewChild('productUnitPriceInput')
  productCost!: ElementRef;
  @ViewChild('productId')
  productId!: ElementRef;
  @ViewChild('productDate')
  productDate!: ElementRef;
  addSales() {
    const newPurchase: Sales = {
      position: this.saleCounter++,
      productId: this.productId.nativeElement.value,
      productCost: parseFloat(this.productCost.nativeElement.value),
      quantity: parseInt(this.form.get('quantity')?.value, 10),
      date: this.formatDate(this.productDate.nativeElement.value),
      price: parseFloat(this.form.get('price')?.value)
    };

    this.dataSource.data = [...this.dataSource.data, newPurchase];
    this.purchaseIds.push(newPurchase.productId); // Add purchaseId to the 1D array
    this.productQty.push(newPurchase.quantity);

    this.resetForm();
    this.calculateTotalPrice(); // Update total price
  }

  resetForm() {
    this.form.reset({
      date: new Date() // Reset date to current date
    });
  }

  getTotalSaleCost() {
    return this.dataSource.data.reduce((acc, sale) => acc + sale.price, 0);
  }

  getTotalSaleQty() {
    return this.dataSource.data.reduce((acc, sale) => acc + sale.quantity, 0);
  }

  formatDate(date: string): string {
    if (!date) return '';
    const d = new Date(date);
    let month = '' + (d.getMonth() + 1);
    let day = '' + d.getDate();
    const year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
  }



  @ViewChild('productQuantity')
  productQuantity!: ElementRef;
  calculateTotalPrice() {
    const unitPrice = parseFloat(this.productCost.nativeElement.value) || 0;
    const quantity = parseFloat(this.productQuantity.nativeElement.value) || 0;
    const totalPrice = unitPrice * quantity;

    this.form.get('price')?.setValue(totalPrice.toFixed(2), { emitEvent: false });
  }


  SingleCustomer = {
    customerName: "",
    customerEmail: "",
    customerContact: "",
    customerAddress: "",
  }



  loadProductByCategory() {
    const productCategory = (this.form.get('productCategory')?.value);
    this.service.getProductByProductCategory(productCategory).subscribe(res => {
      this.loadedProducts = res;
    });
  }

  stock = {
    stockId: 0,
    stockDate: "",
    stockPurchaseQuantity: "",
    stockSaleQuantity: "",
    remainingQuantity: 0,
    stockTotalAmount: "",
  }


  @ViewChild('productUnitPriceInput')
  productUnitPriceInput!: ElementRef;

  loadProductById() {

    const productId = this.form.get('productName')?.value;

    this.service.getStockByProductId(productId).subscribe(res => {
      if (res !== null) {
        this.stock = res;
        if(this.stock.remainingQuantity==0){
          this.toast.error("Product stock is empty. Please add stock","Stock Empty");
        }
      }
      else{
        this.toast.error("Product stock is empty. Please add stock","Stock Empty");
      }
    })

    this.service.getSignleProductByProductId(productId).subscribe(res => {
      if (res !== null) {
        this.loadedSignleProduct = res;
        this.productUnitPriceInput.nativeElement.value = this.loadedSignleProduct.productCost;
      }

    }, err => {
      console.log(err);
      alert(err);
    });

  }




  submitForm() {
    this.SingleCustomer.customerName = this.form.get("customer")?.value
    this.SingleCustomer.customerEmail = this.form.get("email")?.value
    this.SingleCustomer.customerContact = this.form.get("contact")?.value
    this.SingleCustomer.customerAddress = this.form.get("address")?.value

    this.saleData.products = this.purchaseIds.map(id => ({ productId: id }));
    this.saleData.productQuantity = this.productQty.map(qty => ({ productQty: qty }))
    this.saleData.totalSaleQuantity = this.getTotalSaleQty();
    this.saleData.totalSaleAmount = this.getTotalSaleCost();
    this.saleData.customer = this.SingleCustomer

    console.log(JSON.stringify(this.saleData));

    // Uncomment the below code to integrate with your service
    this.service.saleProduct(this.saleData).subscribe(res => {
      if (res !== null) {
        this.dataSource.data = [];
        this.router.navigate(['/admin-dashboard/show-sales']);
        this.toast.success("New purchase added to your inventory.", "Purchase Created.")
      }
    }, erro => {
      this.toast.info("New purchase has been initiated is not working please try.", "Purchase Failed.")
    });

  }



}
