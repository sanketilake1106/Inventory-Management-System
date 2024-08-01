import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { AdminService } from '../../services/admin.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

interface Purchase {
  position: number;
  productId: string;
  productCost: number;
  quantity: number;
  date: string;
  price: number;
}

@Component({
  selector: 'app-add-stock',
  templateUrl: './add-stock.component.html',
  styleUrls: ['./add-stock.component.css']
})
export class AddStockComponent implements OnInit {
  purchaseIds: string[] = [];
  productQty: number[] = [];
  totalPrchaseAmount: number = 0;
  totalPrchaseQty: number = 0;
  form: FormGroup;
  purchaseDataSource = new MatTableDataSource<Purchase>();
  purchaseDisplayedColumns: string[] = ['position', 'productId', 'productCost', 'quantity', 'date', 'price'];
  purchaseCounter: number = 1;
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

  purchaseData = {
    products: [] as { productId: string }[],
    productQuantity: [] as {productQty: number }[],
    totalPurchaseQuantity: this.totalPrchaseQty,
    totalPurchaseAmount: this.totalPrchaseAmount,
    supplier: {
      supplierId: ""
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

  isSupplierEmpty:Boolean = true;

  ngOnInit() {
    this.service.getSuppliers().subscribe(res => {
      if (res !== null) {
        this.suppliers = res;
      }
      else{
        this.isSupplierEmpty = false;
      }
    },err=>{
      this.isSupplierEmpty = false;
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
  addPurchase() {
    const newPurchase: Purchase = {
      position: this.purchaseCounter++,
      productId: this.productId.nativeElement.value,
      productCost: parseFloat(this.productCost.nativeElement.value),
      quantity: parseInt(this.form.get('quantity')?.value, 10),
      date: this.formatDate(this.productDate.nativeElement.value),
      price: parseFloat(this.form.get('price')?.value)
    };

    this.purchaseDataSource.data = [...this.purchaseDataSource.data, newPurchase];
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

  getTotalPurchaseCost() {
    return this.purchaseDataSource.data.reduce((acc, purchase) => acc + purchase.price, 0);
  }

  getTotalPurchaseQty() {
    return this.purchaseDataSource.data.reduce((acc, purchase) => acc + purchase.quantity, 0);
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



  sup2 = [{
    supplierId: "",
    supplierName: "",
    supplierEmail: "",
    supplierContact: "",
    supplierAddress: ""
  }]

  supplier = {
    supplierId: "",
    supplierName: "",
    supplierEmail: "",
    supplierContact: "",
    supplierAddress: ""
  }

  supplier2 = {
    supplierId: "",
    supplierName: "",
    supplierEmail: "",
    supplierContact: "",
    supplierAddress: ""
  }

  getSuppliersByName() {
    const supplierName = this.form.get('customer')?.value;
    this.supplier.supplierName = supplierName;
    // console.log(this.supplier);
    this.service.getSuppliersByName(this.supplier).subscribe(res => {
      this.sup2 = res;
    });
    this.form.get('email')?.setValue("");
    this.form.get('address')?.setValue("");
  }

  getSuppliersByContact() {
    const supplierContact = this.form.get('contact')?.value;
    this.supplier.supplierContact = supplierContact;
    // console.log(this.supplier);
    this.service.getSuppliersByContact(this.supplier).subscribe(res => {
      this.supplier2 = res;
    });
  }

  loadProductByCategory() {
    const productCategory = this.form.get('productCategory')?.value;
    this.service.getProductByProductCategory(productCategory).subscribe(res => {
      this.loadedProducts = res;
    });
  }


  @ViewChild('productUnitPriceInput')
  productUnitPriceInput!: ElementRef;

  loadProductById() {

    const productId = this.form.get('productName')?.value;

    this.service.getSignleProductByProductId(productId).subscribe(res => {
      this.loadedSignleProduct = res;
      this.productUnitPriceInput.nativeElement.value = this.loadedSignleProduct.productCost;

    }, err => {
      console.log(err);
      alert(err);
    });

  }

  productByName = {
    productName: ""
  }

  productResponse = {
    productId: "",
    productHSNNo: "",
    productName: "",
    productDescription: "",
    productCategory: "",
    productCost: "",
    productDate: "",
  }



  submitForm() {
    this.purchaseData.products = this.purchaseIds.map(id => ({ productId: id }));
    this.purchaseData.productQuantity = this.productQty.map(qty => ({ productQty: qty}))
    this.purchaseData.totalPurchaseQuantity = this.getTotalPurchaseQty();
    this.purchaseData.totalPurchaseAmount = this.getTotalPurchaseCost();
    this.purchaseData.supplier.supplierId = this.supplier2.supplierId;

    // console.log(JSON.stringify(this.getTotalPurchaseCost()));

    // Uncomment the below code to integrate with your service
    this.service.addPurchase(this.purchaseData).subscribe(res => {
      if (res !== null) {
        this.purchaseDataSource.data = [];
        this.router.navigate(['/admin-dashboard/show-purchases']);
        this.toast.success("New purchase added to your inventory.","Purchase Created.")
      }
    },erro=>{
      this.toast.info("New purchase has been initiated is not working please try.","Purchase Failed.")
    });

  }



}
